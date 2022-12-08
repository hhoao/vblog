import { MockMethod } from 'vite-plugin-mock';
import { resultPageSuccess, resultSuccess } from '../_util';
import { detailsArticle, simplerArticles } from '../data/article';

export default [
  {
    url: '/basic-api/articles/base/list',
    timeout: 100,
    method: 'get',
    response: ({ query }) => {
      const { page = 1, pageSize = 8 } = query;
      if (pageSize == 0) {
        return resultSuccess(simplerArticles);
      }
      return resultPageSuccess(page, pageSize, simplerArticles);
    },
  },
  {
    url: '/basic-api/articles/details/:id',
    timeout: 100,
    method: 'get',
    response: () => {
      return resultSuccess(detailsArticle);
    },
  },
] as MockMethod[];
