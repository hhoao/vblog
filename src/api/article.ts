import { defHttp } from '/@/utils/http/axios';

import { ArticlePageParams, ArticleParam } from '/@/api/model/articleModel';

export enum ArticleApi {
  GET_ARTICLE_PAGE_LIST = '/v1/articles',
  ADD_ARTICLE = '/v1/articles',
  UPDATE_ARTICLE = '/v1/articles',
  DELETE_ARTICLE = '/v1/articles',
}

export const getArticlePageListApi = (params: ArticlePageParams = { pageNum: 1, pageSize: 5 }) =>
  defHttp.get({ url: ArticleApi.GET_ARTICLE_PAGE_LIST, params });

export const addArticleApi = (params: ArticleParam) =>
  defHttp.post({ url: ArticleApi.ADD_ARTICLE, params });

export const updateArticleApi = (params: ArticleParam) =>
  defHttp.put({ url: ArticleApi.UPDATE_ARTICLE + '/' + params.id, params });
export const deleteArticleApi = (params: ArticleParam) =>
  defHttp.delete({ url: ArticleApi.DELETE_ARTICLE + '/' + params.id, params });
