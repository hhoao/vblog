import { defHttp } from '/@/utils/http/axios';

import {
  AccountListGetResultModel,
  AccountPageParams,
  AccountParams,
} from '/@/api/model/systemModel';
import { GetUserInfoModel, LoginParams, LoginResultModel } from '/@/api/model/userModel';
import { ErrorMessageMode } from '/#/axios';

enum Api {
  ACCOUNT_INFO = '/account',
  AccountPageList = '/accounts',
  Login = '/accounts/auth/token',
  Logout = '/accounts/auth/token',
}
export const getAccountInfo = () => defHttp.get<GetUserInfoModel>({ url: Api.ACCOUNT_INFO });

export const getAccountPageList = (params: AccountPageParams) =>
  defHttp.get<AccountListGetResultModel>({ url: Api.AccountPageList, params });

export const getAccountList = (params: AccountParams) => {
  return getAccountPageList({
    pageNum: 1,
    pageSize: 0,
    ...params,
  });
};

export const isAccountExist = (username: string) =>
  defHttp.post({ url: Api.AccountPageList, params: { username } }, { errorMessageMode: 'none' });

export function loginApi(params: LoginParams, mode: ErrorMessageMode = 'modal') {
  return defHttp.post<LoginResultModel>(
    {
      url: Api.Login,
      params,
    },
    {
      errorMessageMode: mode,
    },
  );
}

export function doLogout() {
  return defHttp.delete({ url: Api.Logout });
}
