import { BasicPageParams, BasicFetchResult } from '/@/api/model/baseModel';
import { RouteMeta } from 'vue-router';

export type AccountPageParams = BasicPageParams & AccountParams;

export type AccountParams = {
  account?: string;
  nickname?: string;
};

export type RoleParams = {
  roleName?: string;
  status?: string;
};

export type RolePageParams = BasicPageParams & RoleParams;

export interface AccountListItem {
  id: string;
  // account: string;
  email: string;
  // nickname: string;
  username: string;
  role: number;
  createTime: string;
  latestTime: string;
  // remark: string;
  status: number;
}

export interface DeptListItem {
  id: string;
  orderNo: string;
  createTime: string;
  remark: string;
  status: number;
}

export interface MenuListItem {
  id: string;
  orderNo: string;
  createTime: string;
  status: number;
  icon: string;
  component: string;
  permission: string;
  meta: RouteMeta;
  alias: string;
  path: string;
  name: string;
  parentId: number;
}

export interface RoleListItem {
  id: string;
  // roleName: string;
  name: string;
  // roleValue: string;
  status: number;
  // orderNo: string;
  createTime: string;
}

/**
 * @description: Request list return value
 */
export type AccountListGetResultModel = BasicFetchResult<AccountListItem>;

export type MenuListGetResultModel = BasicFetchResult<MenuListItem>;

export type RolePageListGetResultModel = BasicFetchResult<RoleListItem>;

export type RoleListGetResultModel = RoleListItem[];
