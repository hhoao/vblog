import * as Mock from 'mockjs';
import { ArticleCommentModel } from '/@/api/models/ArticleCommentModel';

export const commentList = ((): ArticleCommentModel[] => {
  const result: ArticleCommentModel[] = [];
  for (let index = 0; index < 20; index++) {
    result.push({
      id: Mock.mock('@id'),
      author: '@name',
      content: '@sentence',
      lastModification: Mock.mock('@date("yyyy-MM-dd")'),
    });
  }
  return result;
})();
