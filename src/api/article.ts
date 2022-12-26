import { defHttp } from '/@/utils/http/axios';
import {
  BaseArticleModel,
  DetailArticleModel,
  DetailArticleModelPageParams,
  DetailArticleModelParams,
} from '/@/api/models/DetailArticleModel';
import { BasicFetchResult } from '/@/api/models/BaseModel';

export const getBaseArticlePageListApi = (
  params: DetailArticleModelPageParams = { pageNum: 1, pageSize: 5 },
) =>
  defHttp.get<BasicFetchResult<BaseArticleModel>>({
    url: '/api/articles',
    params: params ? Object.assign(params, { base: true }) : { base: true },
  });

export const getDetailsArticlePageListApi = (
  params: DetailArticleModelPageParams = { pageNum: 1, pageSize: 5 },
) =>
  defHttp.get<BasicFetchResult<DetailArticleModel>>({
    url: '/api/articles',
    params: params ? Object.assign(params, { base: false }) : { base: false },
  });

export const getDetailsArticle = (params?: DetailArticleModelParams) =>
  defHttp.get<DetailArticleModel>({
    url: `/api/articles/${params?.id}`,
    params: params ? Object.assign(params, { base: false }) : { base: false },
  });
