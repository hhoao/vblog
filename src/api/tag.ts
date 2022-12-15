import { defHttp } from '/@/utils/http/axios';
import { BasicFetchResult } from '/@/api/model/baseModel';
import { TagModel, TagPageParams, TagParams } from '/@/api/model/TagModel';

export const getTagListApi = (params: TagPageParams = { pageNum: 1, pageSize: 5 }) =>
  defHttp.get<BasicFetchResult<TagModel>>({ url: '/tags', params });

export const addTagApi = (params: TagParams) => defHttp.post({ url: '/tags', params });

export const updateTagApi = (params: TagParams) =>
  defHttp.put({ url: `/tags/${params.id}`, params });
export const deleteTagApi = (params: TagParams) =>
  defHttp.delete({ url: `/tags/${params.id}`, params });
