import { MockMethod } from 'vite-plugin-mock';
import { resultPageSuccess, resultSuccess } from '../_util';
import { commentList } from '../data/comment';

export default [
  {
    url: '/api/comments',
    timeout: 100,
    method: 'get',
    response: ({ query }) => {
      const { page = 1, pageSize = 8 } = query;
      if (pageSize == 0) {
        return resultSuccess(commentList);
      }
      return resultPageSuccess(page, pageSize, commentList);
    },
  },
] as MockMethod[];
