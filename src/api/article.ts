import { defHttp } from '/@/utils/http/axios';

import { ArticleModel } from '/@/api/model/articleModel';

export const getArticlePageList = (params: ArticleModel) =>
  defHttp.get({ url: '/articles', params });

export const addArticle = (params: ArticleModel) => defHttp.post({ url: '/articles', params });

export const updateArticle = (params: ArticleModel) => defHttp.put({ url: '/articles', params });
export const deleteArticle = (params: ArticleModel) => defHttp.delete({ url: '/articles', params });
