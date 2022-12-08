import { AppRouteRecordRaw } from '/@/router/types';
import { LAYOUT } from '/@/router/constant';

const ERROR_LOG_ROUTE: AppRouteRecordRaw = {
  path: '/error-log',
  name: 'ErrorLog',
  component: LAYOUT,
  redirect: '/error-log/list',
  meta: {
    title: 'ErrorLog',
    hideBreadcrumb: true,
    hideChildrenInMenu: true,
  },
  children: [
    {
      path: 'list',
      name: 'ErrorLogList',
      component: () => import('/@/views/sys/error-log/index.vue'),
      meta: {
        title: 'Error-Log-List',
        hideBreadcrumb: true,
        currentActiveMenu: '/error-log',
      },
    },
  ],
};
export default ERROR_LOG_ROUTE;
