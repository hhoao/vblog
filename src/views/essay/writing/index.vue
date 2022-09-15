<template>
  <div style="margin: 0 10px">
    <Form :model="formModel" ref="formRef" @finish="handleFinish" :rules="rules">
      <Row style="margin-top: 5px" ref="headRef">
        <Col :span="22">
          <a-input :class="prefixCls" v-model:value="formModel.title">
            <template #addonBefore>
              <div>
                <span>标题:</span>
              </div>
            </template>
          </a-input>
        </Col>
        <Col :span="2" style="padding: 0 0 0 10px">
          <a-button type="primary" style="border-radius: 20px; width: 100%" html-type="submit"
            >上传</a-button
          >
        </Col>
      </Row>
    </Form>
    <div style="padding-top: 10px" ref="markdownContainerRef">
      <MarkDown
        ref="markDownRef"
        v-model:value="formModel.content"
        placeholder="Please enter text"
        value="value"
        :fillHeightConfig="{ containerRef: getMarkdownContainerRef }"
        :changeStyleSetting="true"
      />
    </div>
  </div>
</template>
<script lang="ts" setup>
  import { computed, onMounted, reactive, ref } from 'vue';
  import { MarkDown, MarkDownActionType } from '/@/components/Markdown';
  import { useDesign } from '/@/hooks/web/useDesign';
  import { Rule } from '/@/components/Form';
  import { FormInstance, Col, Row, Form } from 'ant-design-vue';
  import { MarkdownEssay } from '/@/views/essay/writing/types/MarkdownEditorConfig';

  const formRef = ref<Nullable<FormInstance>>(null);
  const headRef = ref(null);
  const markDownRef = ref<Nullable<MarkDownActionType>>(null);
  const markdownContainerRef = ref(null);
  const formModel = reactive<MarkdownEssay>({
    content: '',
    title: '',
  });
  const getMarkdownContainerRef = computed(() => markdownContainerRef);
  const { prefixCls } = useDesign('markdown');
  const rules: Record<string, Rule[]> = {
    title: [{ required: true }],
    content: [{ required: true }],
  };

  function handleFinish(values: MarkdownEssay) {
    console.log(values, formModel);
  }

  onMounted(() => {
    // if (markdownContainerRef.value) {
    markDownRef.value?.initAndMountEditor();
    // } else {
    //   watch(markdownContainerRef, () => {
    //     markDownRef.value?.initAndMountEditor();
    //   });
    // }
  });
</script>

<style lang="less">
  @prefix-cls: ~'@{namespace}-markdown';
  .@{prefix-cls} {
    .ant-input-group-addon {
      border-radius: 20px 0 0 20px;
      width: 80px;
    }

    .ant-input {
      border-radius: 0 20px 20px 0;
    }
  }
</style>
