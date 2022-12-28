import { defHttp } from '/@/utils/http/axios';
import { BasicFetchResult } from '/@/api/model/baseModel';
import { CommentModel, CommentPageParams, CommentParams } from '/@/api/model/CommentModel';

export enum CommentApi {
  GET_COMMENT_PAGE_LIST = '/v1/comments',
  ADD_COMMENT = '/v1/comments',
  UPDATE_COMMENT = '/v1/comments',
  DELETE_COMMENT = '/v1/comments',
}

export const getCommentPageListApi = (params: CommentPageParams = { pageNum: 1, pageSize: 5 }) =>
  defHttp.get<BasicFetchResult<CommentModel>>({ url: CommentApi.GET_COMMENT_PAGE_LIST, params });

export const addCommentApi = (params: CommentParams) =>
  defHttp.post({ url: CommentApi.ADD_COMMENT, params });

export const updateCommentApi = (params: CommentParams) =>
  defHttp.put({ url: CommentApi.UPDATE_COMMENT + '/' + params.id, params });
export const deleteCommentApi = (params: CommentParams) =>
  defHttp.delete({ url: CommentApi.DELETE_COMMENT + '/' + params.id, params });
