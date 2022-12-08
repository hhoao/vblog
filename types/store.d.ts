import { ErrorTypeEnum } from '/@/enums/exceptionEnum';
import { MenuModeEnum, MenuTypeEnum } from '/@/enums/menuEnum';
import { RoleInfo } from '/@/api/model/userModel';

// Error-log information
export interface ErrorLogInfo {
  // Type of error
  type: ErrorTypeEnum;
  // Error file
  file: string;
  // Error name
  name?: string;
  // Error message
  message: string;
  // Error stack
  stack?: string;
  // Error detail
  detail: string;
  // Error url
  url: string;
  // Error time
  time?: string;
}
export interface AccountTag {
  id: number;
  label: string;
}
export interface UserInfo {
  id: number;
  username: string;
  avatar: string;
  desc?: string;
  homePath?: string;
  role: RoleInfo;
  phone?: string;
  email?: string;
  icon?: string;
  status?: false;
  latestTime?: string;
  notifyCount?: number;
  introduction?: string;
  createTime?: string;
  signature?: string;
  unreadCount?: number;
  title?: string;
  address?: string;
  country?: string;
  password?: string;
  tags?: AccountTag[];
}
export interface BeforeMiniState {
  menuCollapsed?: boolean;
  menuSplit?: boolean;
  menuMode?: MenuModeEnum;
  menuType?: MenuTypeEnum;
}
