import { MockMethod } from 'vite-plugin-mock';
import { resultError, resultPageSuccess, resultSuccess } from '../_util';
import { roleList } from '../data/role';

export default [
  {
    url: '/basic-api/roles',
    timeout: 100,
    method: 'get',
    response: ({ query }) => {
      const { page = 1, pageSize = 20 } = query;
      if (pageSize == 0) {
        return resultSuccess(roleList);
      }
      return resultPageSuccess(page, pageSize, roleList);
    },
  },
  {
    url: '/basic-api/role',
    timeout: 500,
    method: 'post',
    response: ({ query }) => {
      const { id, status } = query;
      return resultSuccess({ id, status });
    },
  },
  {
    url: '/basic-api/account',
    timeout: 500,
    method: 'post',
    response: ({ body }) => {
      const { account } = body || {};
      if (account && account.indexOf('admin') !== -1) {
        return resultError('该字段不能包含admin');
      } else {
        return resultSuccess(`${account} can use`);
      }
    },
  },
] as MockMethod[];
