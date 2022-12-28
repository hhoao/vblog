import { BasicColumn } from '/@/components/Table';
import { FormSchema } from '/@/components/Table';

export const columns: BasicColumn[] = [
  {
    title: '标签',
    dataIndex: 'label',
    width: 120,
  },
];

export const searchFormSchema: FormSchema[] = [
  {
    field: 'label',
    label: '标签',
    component: 'Input',
    colProps: { span: 8 },
  },
];

export const articleFormSchema: FormSchema[] = [
  {
    field: 'label',
    label: '标签',
    component: 'Input',
    rules: [
      {
        required: true,
        message: '请输入标签',
      },
    ],
  },
];
