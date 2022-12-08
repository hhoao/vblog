import * as Mock from 'mockjs';
import { ArticleCommentModel } from '/@/api/models/ArticleCommentModel';

export const commentList = ((): ArticleCommentModel[] => {
  const result: ArticleCommentModel[] = [];
  for (let index = 0; index < 20; index++) {
    result.push({
      id: Mock.mock('@number'),
      author: '@name',
      content: '@sentence',
      lastModification: new Date(),
    });
  }
  return result;
})();
