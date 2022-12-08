import { defHttp } from '/@/utils/http/axios';
import { BasicFetchResult } from '/@/api/models/BaseModel';
import { ArticleCommentModel } from '/@/api/models/ArticleCommentModel';

export const getArticleCommentListApi = (params?: ArticleCommentModel) =>
  defHttp.get<BasicFetchResult<ArticleCommentModel>>({ url: '/comments', params });
