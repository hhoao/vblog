import { BasicColumn } from '/@/components/Table';
import { FormSchema } from '/@/components/Table';

export const columns: BasicColumn[] = [
  {
    title: '标题',
    dataIndex: 'title',
    width: 120,
  },
  {
    title: '作者',
    dataIndex: 'author',
    width: 120,
  },
  {
    title: '封面',
    dataIndex: 'cover',
    width: 120,
  },
  {
    title: '类型',
    dataIndex: 'type',
    format: (text) => {
      text = text.toString();
      switch (text) {
        case '0':
          return '原创';
        case '1':
          return '翻译';
        case '2':
          return '转载';
      }
      return '其他';
    },
    width: 120,
  },
  {
    title: '等级',
    dataIndex: 'level',
    format: (text) => {
      text = text.toString();
      switch (text) {
        case '0':
          return '初级';
        case '1':
          return '中级';
        case '2':
          return '高级';
      }
      return '其他';
    },
    width: 120,
  },
  {
    title: '置顶',
    dataIndex: 'top',
    edit: true,
    editable: true,
    editComponent: 'Switch',
    width: 120,
  },
  {
    title: '是否可见',
    dataIndex: 'visible',
    edit: true,
    editable: true,
    editComponent: 'Switch',
    width: 120,
  },
  {
    title: '最后修改时间',
    dataIndex: 'lastModification',
    width: 180,
  },
];

export const searchFormSchema: FormSchema[] = [
  {
    field: 'title',
    label: '标题',
    component: 'Input',
    colProps: { span: 8 },
  },
];

export const articleFormSchema: FormSchema[] = [
  {
    field: 'title',
    label: '标题',
    component: 'Input',
    rules: [
      {
        required: true,
        message: '请输入标题',
        min: 3,
      },
    ],
  },
  {
    field: 'author',
    label: '作者',
    component: 'Input',
    required: true,
  },
];
