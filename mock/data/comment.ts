import * as Mock from 'mockjs';
import { CommentModel } from '/@/api/model/CommentModel';

function getComment() {
  return {
    id: Mock.mock('@id'),
    author: '@name',
    content: '@sentence',
    lastModification: Mock.mock('@date("yyyy-MM-dd")'),
  };
}

export const commentList = ((): CommentModel[] => {
  const result: CommentModel[] = [];
  for (let index = 0; index < 20; index++) {
    result.push(getComment());
  }
  return result;
})();
