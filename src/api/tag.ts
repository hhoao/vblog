import { defHttp } from '/@/utils/http/axios';
import { BasicFetchResult } from '/@/api/models/BaseModel';
import { TagModel } from '/@/api/models/TagModel';

export const getTagListApi = (params?: TagModel) =>
  defHttp.get<BasicFetchResult<TagModel>>({ url: '/tags', params });
