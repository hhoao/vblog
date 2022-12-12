import { defHttp } from '/@/utils/http/axios';
import {
  BaseArticleModel,
  DetailArticleModel,
  DetailArticleModelParams,
} from '/@/api/models/DetailArticleModel';
import { BasicFetchResult } from '/@/api/models/BaseModel';

export const getBaseArticlePageListApi = (params?: DetailArticleModelParams) =>
  defHttp.get<BasicFetchResult<BaseArticleModel>>({
    url: '/articles',
    params: params ? Object.assign(params, { base: true }) : { base: true },
  });

export const getDetailsArticle = (params?: DetailArticleModelParams) =>
  defHttp.get<DetailArticleModel>({
    url: `/articles/${params?.id}`,
    params: params ? Object.assign(params, { base: false }) : { base: false },
  });
