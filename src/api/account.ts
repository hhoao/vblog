import { defHttp } from '/@/utils/http/axios';

import {
  AccountListGetResultModel,
  AccountPageParams,
  AccountParams,
} from '/@/api/model/systemModel';
import { GetUserInfoModel, LoginParams, LoginResultModel } from '/@/api/model/userModel';
import { ErrorMessageMode } from '/#/axios';

export enum AccountApi {
  ACCOUNT_INFO = '/v1/account',
  AccountPageList = '/v1/accounts',
  Login = '/v1/accounts/auth/token',
  Logout = '/v1/accounts/auth/token',
}
export const getAccountInfo = () => defHttp.get<GetUserInfoModel>({ url: AccountApi.ACCOUNT_INFO });

export const getAccountPageList = (params: AccountPageParams) =>
  defHttp.get<AccountListGetResultModel>({ url: AccountApi.AccountPageList, params });

export const getAccountList = (params: AccountParams) => {
  return getAccountPageList({
    pageNum: 1,
    pageSize: 0,
    ...params,
  });
};

export const isAccountExist = (username: string) =>
  defHttp.post(
    { url: AccountApi.AccountPageList, params: { username } },
    { errorMessageMode: 'none' },
  );

export function loginApi(params: LoginParams, mode: ErrorMessageMode = 'modal') {
  return defHttp.post<LoginResultModel>(
    {
      url: AccountApi.Login,
      params,
    },
    {
      errorMessageMode: mode,
    },
  );
}

export function doLogout() {
  return defHttp.delete({ url: AccountApi.Logout });
}
