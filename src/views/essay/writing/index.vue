<template>
  <div style="margin: 0 10px">
    <a-form :model="formModel" ref="formRef">
      <a-row style="margin-top: 5px" ref="headRef">
        <a-col :span="22">
          <a-input :class="prefixCls" v-model:value="formModel.title">
            <template #addonBefore>
              <div>
                <span>标题:</span>
              </div>
            </template>
          </a-input>
        </a-col>
        <a-col :span="2" style="padding: 0 0 0 10px">
          <a-button type="primary" style="border-radius: 20px; width: 100%" @click="openModal"
            >上传
          </a-button>
        </a-col>
      </a-row>
    </a-form>
    <div style="padding-top: 10px" ref="markdownContainerRef">
      <MarkDown
        ref="markDownRef"
        v-model:value="formModel.content"
        placeholder="Please enter text"
        :fillHeightConfig="{ containerRef: getMarkdownContainerRef }"
        :changeStyleSetting="true"
        :upload="uploadConfig"
      />
    </div>
    <essay-writing-modal ref="modalRef" @public-essay="publicEssay" />
  </div>
</template>
<script lang="ts" setup>
  import { computed, onMounted, reactive, ref } from 'vue';
  import { MarkDown, MarkDownActionType } from '/@/components/Markdown';
  import { useDesign } from '/@/hooks/web/useDesign';
  import { FormInstance, message } from 'ant-design-vue';
  import { MarkdownEssay } from '/@/views/essay/writing/types/MarkdownEditorConfig';
  import EssayWritingModal from '/@/views/essay/writing/EssayWritingModal.vue';
  import { EssayWritingModalType } from '/@/views/essay/writing/types/EssayWritingModalType';
  import { addArticleApi } from '/@/api/article';
  import { uploadApi } from '/@/api/upload';
  const uploadConfig: IUpload = {
    handler(files: File[]): string | Promise<string> | Promise<null> | null {
      return uploadApi(
        {
          file: files[0],
        },
        () => {},
      ).then((value) => {
        markDownRef.value?.getVditor().insertValue('![]' + '(' + value.data.result + ')');
        return value.data.result;
      }) as Promise<string>;
    },
    multiple: false,
  };
  const formRef = ref<FormInstance>();
  const headRef = ref(null);
  const markDownRef = ref<Nullable<MarkDownActionType>>(null);
  const modalRef = ref<Nullable<EssayWritingModalType>>(null);
  const markdownContainerRef = ref(null);

  const formModel = reactive<MarkdownEssay>({
    content: '',
    title: '',
  });
  const getMarkdownContainerRef = computed(() => markdownContainerRef);
  const { prefixCls } = useDesign('markdown');

  function openModal() {
    if (formModel.content.length < 20) {
      message.error('The content must be more than 20 words');
      throw new Error('The content must be more than 20 words');
    }
    if (formModel.title.length < 2) {
      message.error('The title must be more than 2 words');
      throw new Error('The title must be more than 2 words');
    }
    modalRef.value?.open();
  }
  function reset() {
    formModel.content = '';
    formModel.title = '';
  }
  function closeModal() {
    modalRef.value?.close();
  }

  async function publicEssay(data) {
    addArticleApi({
      ...data,
      ...formModel,
    }).then(() => {
      reset();
      closeModal();
    });
  }

  onMounted(() => {
    // markDownRef.value?.initAndMountEditor();
  });
</script>

<style lang="less">
  //noinspection LessUnresolvedVariable
  @prefix-cls: ~'@{namespace}-markdown';

  .@{prefix-cls} {
    .ant-input-group-addon {
      border-radius: 20px 0 0 20px;
      width: 80px;
      height: 10px;
    }

    .ant-input {
      border-radius: 0 20px 20px 0;
    }
  }
</style>
