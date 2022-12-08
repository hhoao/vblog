import { DetailArticleModel, BaseArticleModel } from '/@/api/models/DetailArticleModel';
import * as Mock from 'mockjs';

export const simplerArticles = ((): BaseArticleModel[] => {
  const result: BaseArticleModel[] = [];
  for (let index = 0; index < 20; index++) {
    result.push({
      id: Mock.mock('@id'),
      title: '@ctitle',
      type: '@integer(1, 3)',
      author: '@cname()',
      cover: '@url',
      digest: '@cparagraph',
      top: Mock.mock('@boolean'),
      level: Mock.mock('@integer(0, 1)'),
      lastModification: '@date("yyyy-MM-dd")',
      readingAmount: Mock.mock('@integer'),
      visible: true,
    });
  }
  return result;
})();

export const detailsArticle = ((): DetailArticleModel => {
  const result: DetailArticleModel = {
    id: Mock.mock('@id'),
    title: '@ctitle',
    type: '@integer(1, 3)',
    author: '@cname()',
    cover: '@url',
    digest: '@cparagraph',
    top: Mock.mock('@boolean'),
    level: Mock.mock('@integer(0, 1)'),
    lastModification: '@date("yyyy-MM-dd")',
    readingAmount: Mock.mock('@integer'),
    visible: true,
    content: '@cparagraph' + '@cparagraph' + '@cparagraph',
  };
  return result;
})();
