import { MockMethod } from 'vite-plugin-mock';
import {
  getRequestToken,
  requestParams,
  resultError,
  resultPageSuccess,
  resultSuccess,
} from '../_util';
import { accountList, createFakeUserList, generateToken, getUserByToken } from '../data/user';
import { GetUserInfoModel } from '/@/api/model/userModel';

const tokenHead = 'Bearer';
export default [
  {
    url: '/basic-api/accounts',
    timeout: 100,
    method: 'get',
    response: ({ query }) => {
      const { page = 1, pageSize = 20 } = query;
      return resultPageSuccess(page, pageSize, accountList);
    },
  },
  {
    url: '/basic-api/account',
    timeout: 1000,
    method: 'get',
    response: (request: requestParams) => {
      const token = getRequestToken(request);

      if (!token) return resultError('Invalid token');
      let checkUser: undefined | GetUserInfoModel = undefined;
      if (token.startsWith(tokenHead)) {
        checkUser = getUserByToken(token.substring(tokenHead.length));
      }
      if (!checkUser) {
        return resultError('The corresponding user information was not obtained!');
      }
      return resultSuccess(checkUser);
    },
  },
  {
    url: '/basic-api/accounts/auth/token',
    timeout: 200,
    method: 'post',
    response: ({ body }) => {
      const { username, password } = body;
      const checkUser = createFakeUserList().find(
        (item) => item.username === username && password === item.password,
      );
      if (!checkUser) {
        return resultError('Incorrect account or password！');
      }
      const token = generateToken(checkUser.id);
      return resultSuccess({
        token,
        tokenHead,
      });
    },
  },
  {
    url: '/basic-api/accounts/auth/token',
    timeout: 200,
    method: 'delete',
    response: (request: requestParams) => {
      const token = getRequestToken(request);
      if (!token) return resultError('Invalid token');
      let checkUser: undefined | GetUserInfoModel = undefined;
      if (token.startsWith(tokenHead)) {
        checkUser = getUserByToken(token.substring(tokenHead.length));
      }
      if (!checkUser) {
        return resultError('Invalid token!');
      }
      return resultSuccess(undefined, { message: 'Token has been destroyed' });
    },
  },
] as MockMethod[];
