import { defHttp } from '/@/utils/http/axios';
import {
  BaseArticleModel,
  DetailArticleModel,
  DetailArticleModelParams,
} from '/@/api/models/DetailArticleModel';
import { BasicFetchResult } from '/@/api/models/BaseModel';

export const getBaseArticlePageListApi = (params?: DetailArticleModelParams) =>
  defHttp.get<BasicFetchResult<BaseArticleModel>>({ url: '/articles/base/list', params });

export const getDetailsArticle = (params?: DetailArticleModelParams) =>
  defHttp.get<DetailArticleModel>({ url: `/articles/details/${params?.id}` });
