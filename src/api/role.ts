import {
  RoleListGetResultModel,
  RolePageListGetResultModel,
  RolePageParams,
  RoleParams,
} from '/@/api/model/systemModel';
import { defHttp } from '/@/utils/http/axios';

enum Api {
  setRoleStatus = '/v1/role',
  RolePageList = '/v1/roles',
  GetAllRoleList = '/v1/role',
}

export const getRoleListByPage = (params?: RolePageParams) =>
  defHttp.get<RolePageListGetResultModel>({ url: Api.RolePageList, params });

export const getAllRoleList = (params?: RoleParams) =>
  defHttp.get<RoleListGetResultModel>({ url: Api.GetAllRoleList, params });

export const setRoleStatus = (id: number, status: string) =>
  defHttp.post({ url: Api.setRoleStatus, params: { id, status } });
