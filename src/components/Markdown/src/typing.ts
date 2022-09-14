import Vditor from 'vditor';
export interface MarkDownActionType {
  getVditor: () => Vditor;
  //必须调用此函数编辑器才会显示
  mount: (props: IOptions) => any;
  initAndMountEditor;
}
