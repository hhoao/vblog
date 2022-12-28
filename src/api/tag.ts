import { defHttp } from '/@/utils/http/axios';
import { BasicFetchResult } from '/@/api/model/baseModel';
import { TagModel, TagPageParams, TagParams } from '/@/api/model/TagModel';

export enum TagApi {
  GET_TAG_LIST = '/v1/tags',
  ADD_TAG = '/v1/tags',
  UPDATE_TAG = '/v1/tags',
  DELETE_TAG = '/v1/tags',
}
export const getTagListApi = (params: TagPageParams = { pageNum: 1, pageSize: 5 }) =>
  defHttp.get<BasicFetchResult<TagModel>>({ url: TagApi.GET_TAG_LIST, params });

export const addTagApi = (params: TagParams) => defHttp.post({ url: TagApi.ADD_TAG, params });

export const updateTagApi = (params: TagParams) =>
  defHttp.put({ url: TagApi.UPDATE_TAG + '/' + params.id, params });
export const deleteTagApi = (params: TagParams) =>
  defHttp.delete({ url: TagApi.DELETE_TAG + '/' + params.id, params });
