import { BasicPageParams } from '/@/api/models/BaseModel';

export interface DetailArticleModel extends BaseArticleModel {
  content: string;
}
export interface SearchArticleModel {
  id: string;
  title: string;
  digest: string;
}
export interface BaseArticlePageListParam {
  title?: string;
  author?: string;
  type?: string;
  level?: number;
  visible?: boolean;
}
export interface DetailArticleModelParams {
  id: string;
}
export interface SearchArticlePageParams extends BasicPageParams {
  queryInfo: string;
}
export type DetailArticleModelPageParams = BaseArticlePageListParam & BasicPageParams;

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
