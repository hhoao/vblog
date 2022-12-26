import { defHttp } from '/@/utils/http/axios';
import { BasicFetchResult } from '/@/api/models/BaseModel';
import { TagModel, TagPageModelParams } from '/@/api/models/TagModel';

export const getTagListApi = (params: TagPageModelParams = { pageNum: 1, pageSize: 5 }) =>
  defHttp.get<BasicFetchResult<TagModel>>({ url: '/api/tags', params });
