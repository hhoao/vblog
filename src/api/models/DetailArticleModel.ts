export interface DetailArticleModel extends BaseArticleModel {
  content: string;
}
export interface DetailArticleModelParams {
  id: string;
}

export interface BaseArticleModel {
  id: string;
  title: string;
  author: string;
  digest: string;
  type: string;
  top: boolean;
  level: number;
  visible: boolean;
  cover: string;
  lastModification?: string;
  readingAmount?: number;
}
