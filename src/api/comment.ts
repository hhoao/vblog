import { defHttp } from '/@/utils/http/axios';
import { BasicFetchResult } from '/@/api/model/baseModel';
import { CommentModel, CommentPageParams, CommentParams } from '/@/api/model/CommentModel';

export const geCommentPageListApi = (params: CommentPageParams = { pageNum: 1, pageSize: 5 }) =>
  defHttp.get<BasicFetchResult<CommentModel>>({ url: '/comments', params });

export const addCommentApi = (params: CommentParams) => defHttp.post({ url: '/comments', params });

export const updateCommentApi = (params: CommentParams) =>
  defHttp.put({ url: `/comments/${params.id}`, params });
export const deleteCommentApi = (params: CommentParams) =>
  defHttp.delete({ url: `/comments/${params.id}`, params });
