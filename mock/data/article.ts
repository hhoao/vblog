import { BaseArticleModel, DetailArticleModel } from '/@/api/models/DetailArticleModel';
import * as Mock from 'mockjs';

export const getDetailsArticle = (): DetailArticleModel => {
  return {
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
};
export const getSimplerArticle = (): BaseArticleModel => {
  return {
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
  };
};
export const simplerArticle = (() => {
  return getSimplerArticle();
})();
export const simplerArticleList = ((): BaseArticleModel[] => {
  const result: BaseArticleModel[] = [];
  for (let index = 0; index < 20; index++) {
    result.push(getSimplerArticle());
  }
  return result;
})();

export const detailsArticle = ((): DetailArticleModel => {
  return getDetailsArticle();
})();

export const detailsArticleList = ((): DetailArticleModel[] => {
  const result: DetailArticleModel[] = [];
  for (let i = 0; i < 10; i++) {
    result.push(getDetailsArticle());
  }
  return result;
})();
