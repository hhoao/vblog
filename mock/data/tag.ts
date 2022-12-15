import * as Mock from 'mockjs';
import { TagModel } from '/@/api/model/TagModel';

function getTag() {
  return {
    id: Mock.mock('@number'),
    label: '@word',
  };
}
export const tagList = ((): TagModel[] => {
  const result: TagModel[] = [];
  for (let index = 0; index < 20; index++) {
    result.push(getTag());
  }
  return result;
})();
