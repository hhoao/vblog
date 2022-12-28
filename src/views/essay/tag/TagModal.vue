<template>
  <BasicModal v-bind="$attrs" @register="registerModal" :title="getTitle" @ok="handleSubmit">
    <BasicForm @register="registerForm" />
  </BasicModal>
</template>
<script lang="ts" setup>
  import { ref, computed, unref } from 'vue';
  import { BasicModal, useModalInner } from '/@/components/Modal';
  import { BasicForm, useForm } from '/@/components/Form';
  import { articleFormSchema } from './tag.data';
  import { addTagApi, updateTagApi } from '/@/api/tag';

  const emits = defineEmits(['success', 'register']);
  const isUpdate = ref(true);
  const rowId = ref('');

  const [registerForm, { setFieldsValue, updateSchema, resetFields, validate }] = useForm({
    labelWidth: 100,
    schemas: articleFormSchema,
    showActionButtonGroup: false,
    actionColOptions: {
      span: 23,
    },
  });

  const [registerModal, { setModalProps, closeModal }] = useModalInner(async (data) => {
    resetFields();
    setModalProps({ confirmLoading: false });
    isUpdate.value = !!data?.isUpdate;

    if (unref(isUpdate)) {
      rowId.value = data.record.id;
      setFieldsValue({
        ...data.record,
      });
    }

    updateSchema([
      {
        field: 'pwd',
        show: !unref(isUpdate),
      },
    ]);
  });

  const getTitle = computed(() => (!unref(isUpdate) ? '新增标签' : '编辑标签'));

  async function handleSubmit() {
    try {
      const values = await validate();
      setModalProps({ confirmLoading: true });
      // TODO custom api
      unref(isUpdate) ? await updateTagApi(values) : await addTagApi(values);
      closeModal();
      emits('success', { isUpdate: unref(isUpdate), values: { ...values, id: rowId.value } });
    } finally {
      setModalProps({ confirmLoading: false });
    }
  }
</script>
