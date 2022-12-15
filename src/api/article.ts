import { defHttp } from '/@/utils/http/axios';

import { ArticlePageParams, ArticleParam } from '/@/api/model/articleModel';

export const getArticlePageListApi = (params: ArticlePageParams = { pageNum: 1, pageSize: 5 }) =>
  defHttp.get({ url: '/articles', params });

export const addArticleApi = (params: ArticleParam) => defHttp.post({ url: '/articles', params });

export const updateArticleApi = (params: ArticleParam) =>
  defHttp.put({ url: `/articles/${params.id}`, params });
export const deleteArticleApi = (params: ArticleParam) =>
  defHttp.delete({ url: `/articles/${params.id}`, params });
