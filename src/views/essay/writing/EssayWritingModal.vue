<template>
  <div>
    <a-modal
      v-model:visible="visible"
      :title="title"
      @ok="handleModal"
      :bodyStyle="{ margin: '1px', padding: '0 25px' }"
    >
      <a-form :model="data" :rules="rules" ref="modalFormRef">
        <div>
          <span style="vertical-align: top">封面&摘要: &nbsp;</span>
          <div style="display: inline-block">
            <a-radio-group v-model:value="hasCover" name="radioGroup">
              <a-radio :value="0">无封面</a-radio>
              <a-radio :value="1">单图</a-radio>
            </a-radio-group>
            <div style="margin-top: 10px; display: flex">
              <div
                style="display: inline-block; width: auto; vertical-align: top"
                v-if="hasCover === 1"
              >
                <a-form-item :rules="coverRule" name="cover">
                  <a-upload
                    v-model:file-list="fileList"
                    list-type="picture-card"
                    @preview="handlePreview"
                    :beforeUpload="beforeUploadFile"
                  >
                    <div v-if="fileList.length < 1">
                      <loading-outlined v-if="loading" />
                      <plus-outlined v-else />
                      <div style="margin-top: 8px">Upload</div>
                    </div>
                  </a-upload>
                </a-form-item>
              </div>
              <a-form-item name="digest">
                <a-textarea
                  style="display: inline-block"
                  v-model:value="data.digest"
                  placeholder="摘要(必填)"
                  show-count
                  :maxlength="100"
                  :autoSize="{ minRows: 4, maxRows: 6 }"
                />
              </a-form-item>
            </div>
          </div>
          <a-modal
            :visible="previewVisible"
            :title="previewTitle"
            :footer="null"
            @cancel="handleCancel"
            @change="handleChange"
          >
            <img alt="example" style="width: 100%" :src="previewImage" />
          </a-modal>
        </div>
        <div>
          <span>文章标签:&nbsp;</span>
          <a-tag closable @close="tagOnClose(item)" v-for="item in selectItems" :key="item.label">
            {{ item.label }}
          </a-tag>
          <a-cascader
            v-model:value="data.tags"
            multiple
            max-tag-count="responsive"
            :options="options"
            placeholder="+添加文章标签"
            @change="onChange"
          >
            <a-button href="#">+添加文章标签</a-button>
          </a-cascader>
        </div>
        <div>
          <span>发布形式:&nbsp;</span>
          <a-radio-group v-model:value="data.form">
            <a-radio :value="0">全部可见</a-radio>
            <a-radio :value="1">仅我可见</a-radio>
          </a-radio-group>
        </div>
        <div>
          <span>内容等级:&nbsp;</span>
          <a-radio-group v-model:value="data.contentLevel">
            <a-radio :value="0">初级</a-radio>
            <a-radio :value="1">中级</a-radio>
            <a-radio :value="2">高级</a-radio>
          </a-radio-group>
        </div>
      </a-form>
    </a-modal>
  </div>
</template>

<script lang="ts" setup>
  import { reactive, ref } from 'vue';
  import { PlusOutlined, LoadingOutlined } from '@ant-design/icons-vue';
  import { CascaderProps, FormInstance, message, UploadChangeParam } from 'ant-design-vue';
  import { SelectItem } from '/@/views/essay/writing/types/MarkdownEditorConfig';
  import { ModalData } from '/@/views/essay/writing/types/EssayWritingModalType';
  import { UploadFile } from 'ant-design-vue/lib/upload/interface';
  import { dataURLtoBlob } from '/@/utils/file/base64Conver';
  import { uploadApi } from '/@/api/upload';
  import { Rule } from 'ant-design-vue/lib/form';
  import { isEmpty } from '/@/utils/is';

  const modalFormRef = ref<FormInstance>();
  async function handleModal() {
    modalFormRef.value?.validateFields().then(async () => {
      if (!isEmpty(coverFileInfo.fileBuffer)) {
        const blob = dataURLtoBlob(coverFileInfo.fileBuffer);
        const uploadFile: File = new File([blob], coverFileInfo.filename, {
          type: coverFileInfo.fileType,
        });
        data.cover = (
          await uploadApi(
            {
              file: uploadFile,
            },
            () => {},
          )
        ).data.result;
      }
      emits('publicEssay', data);
    });
  }
  async function digestValidator(_rule: Rule, value: number) {
    if (!value) {
      return Promise.reject('Please input the digest');
    } else if (value.toString().length < 10) {
      return Promise.reject('The number of digest must be greater than 10');
    } else {
      return Promise.resolve();
    }
  }
  const coverRule: Rule[] = [
    {
      validator: async () => {
        if (isEmpty(coverFileInfo.fileBuffer)) {
          return Promise.reject('Please upload the cover');
        } else {
          return Promise.resolve();
        }
      },
      trigger: 'blur',
    },
  ];

  const rules: Recordable<Rule[]> = {
    digest: [{ required: true, validator: digestValidator, trigger: 'blur' }],
  };
  async function beforeUploadFile(file: File) {
    if (!file) {
      return;
    }
    let fileReader: FileReader = new FileReader();
    fileReader.readAsDataURL(file);
    fileReader.onloadend = (e) => {
      coverFileInfo.filename = file.name;
      coverFileInfo.fileType = file.type;
      coverFileInfo.fileBuffer = e.target?.result ?? '';
    };
    fileReader.onerror = () => {
      message.error('upload error');
    };
    return false;
  }

  const hasCover = ref<number>(0);
  const loading = ref<boolean>(false);
  const previewVisible = ref(false);
  const previewImage = ref('');
  const previewTitle = ref('');
  const handleCancel = () => {
    previewVisible.value = false;
    previewTitle.value = '';
  };
  const handlePreview = async (previewFile: UploadFile) => {
    if (previewFile) {
      previewFile.preview = coverFileInfo.fileBuffer as string;
      previewImage.value = (previewFile.url || previewFile.preview) ?? '';
      previewVisible.value = true;
      previewTitle.value =
        (previewFile.name || previewFile.url?.substring(previewFile.url?.lastIndexOf('/') + 1)) ??
        '';
    }
  };
  const fileList = ref([]);
  const selectItems = ref<SelectItem[]>([]);
  const coverFileInfo = reactive<{
    fileBuffer: ArrayBuffer | string;
    filename: string;
    fileType: string;
  }>({ fileBuffer: '', filename: '', fileType: '' });

  const data = reactive<ModalData>({
    contentLevel: 0,
    tags: [],
    digest: '',
    form: 0,
    cover: '',
  });
  const title = ref<string>('发布文章');
  const visible = ref<boolean>(false);
  const tags = ref<string[]>([]);
  const emits = defineEmits({ publicEssay: null });
  const options: CascaderProps['options'] = [
    {
      label: 'Bamboo',
      value: 'bamboo',
      children: [
        {
          label: 'Little',
          value: 'little',
          children: [
            {
              label: 'Toy Fish',
              value: 'fish',
            },
            {
              label: 'Toy Cards',
              value: 'cards',
            },
          ],
        },
      ],
    },
  ];
  const onChange: CascaderProps['onChange'] = (_value, selectedOptions) => {
    let filterOptions: SelectItem[] = [];
    for (let selectOption of selectedOptions) {
      filterOptions = [...filterOptions, selectOption[selectOption.length - 1]];
    }
    selectItems.value = filterOptions;
  };
  const handleChange = (info: UploadChangeParam) => {
    if (info.file.status === 'uploading') {
      loading.value = true;
      return;
    }
    if (info.file.status === 'done') {
      loading.value = false;
    }
    if (info.file.status === 'error') {
      loading.value = false;
      message.error('upload error');
    }
  };
  function tagOnClose(item) {
    selectItems.value.splice(
      selectItems.value.findIndex((selectItem) => item == selectItem),
      1,
    );
    for (let index in tags.value) {
      if (tags.value[index].includes(item.value)) {
        tags.value.splice(Number.parseInt(index), 1);
      }
    }
  }
  function open() {
    visible.value = true;
  }
  function close() {
    visible.value = false;
  }
  defineExpose({
    open,
    close,
  });
</script>

<style scoped lang="less">
  .ant-modal-body {
    & > * {
      margin-top: 16px;
    }
  }
</style>
