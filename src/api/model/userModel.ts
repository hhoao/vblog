/**
 * @description: Login interface parameters
 */
import { UserInfo } from '/#/store';

export interface LoginParams {
  username: string;
  password: string;
}

export interface RoleInfo {
  id: number;
  name: string;
  status: boolean;
  createTime?: string;
  description?: string;
}

/**
 * @description: Login interface return value
 */
export interface LoginResultModel {
  userId: string | number;
  token: string;
  tokenHead: string;
  role: RoleInfo;
}

/**
 * @description: Get user information return value
 */
export type GetUserInfoModel = UserInfo;
