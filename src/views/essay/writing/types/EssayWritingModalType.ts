export interface EssayWritingModalType {
  open: () => any;
  close: () => any;
}

export interface ModalData {
  contentLevel: number;
  tags: string[];
  digest: string;
  form: number;
  cover: string;
}
