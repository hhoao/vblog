import { MockMethod } from 'vite-plugin-mock';
import { resultPageSuccess, resultSuccess } from '../_util';
import {
  detailsArticle,
  detailsArticleList,
  simplerArticle,
  simplerArticleList,
} from '../data/article';

export default [
  {
    url: '/basic-api/articles',
    timeout: 100,
    method: 'delete',
    response: () => {
      return resultSuccess();
    },
  },
  {
    url: '/basic-api/articles/:id',
    timeout: 100,
    method: 'delete',
    response: () => {
      return resultSuccess();
    },
  },
  {
    url: '/basic-api/articles/:id',
    timeout: 100,
    method: 'put',
    response: () => {
      return resultSuccess();
    },
  },
  {
    url: '/basic-api/articles',
    timeout: 100,
    method: 'put',
    response: () => {
      return resultSuccess();
    },
  },
  {
    url: '/basic-api/articles',
    timeout: 100,
    method: 'get',
    response: ({ query }) => {
      const { page = 1, pageSize = 8, base = false } = query;
      if (pageSize == 0) {
        if (base == 'false' || !base) {
          return resultSuccess(detailsArticleList);
        } else {
          return resultSuccess(simplerArticleList);
        }
      } else {
        if (base == 'false' || !base) {
          return resultPageSuccess(page, pageSize, detailsArticleList);
        } else {
          return resultPageSuccess(page, pageSize, simplerArticleList);
        }
      }
    },
  },
  {
    url: '/basic-api/articles/:id',
    timeout: 100,
    method: 'get',
    response: ({ query }) => {
      const { base = false } = query;
      if (base == 'false' || !base) {
        return resultSuccess(detailsArticle);
      } else {
        return resultSuccess(simplerArticle);
      }
    },
  },
] as MockMethod[];
