import { defHttp } from '/@/utils/http/axios';
import { BasicFetchResult } from '/@/api/models/BaseModel';
import {
  ArticleCommentModel,
  ArticleCommentPageModelParams,
} from '/@/api/models/ArticleCommentModel';

export const getArticleCommentListApi = (
  params: ArticleCommentPageModelParams = { pageNum: 1, pageSize: 5 },
) => defHttp.get<BasicFetchResult<ArticleCommentModel>>({ url: '/api/comments', params });
