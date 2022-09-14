import type { AppRouteModule } from '/@/router/types';

import { LAYOUT } from '/@/router/constant';
import { t } from '/@/hooks/web/useI18n';

const system: AppRouteModule = {
  path: '/system',
  name: 'System',
  component: LAYOUT,
  redirect: '/system/account',
  meta: {
    orderNo: 2000,
    icon: 'ion:settings-outline',
    title: t('routes.system.moduleName'),
  },
  children: [
    {
      path: 'account',
      name: 'AccountManagement',
      meta: {
        title: t('routes.system.account'),
        ignoreKeepAlive: false,
      },
      component: () => import('/@/views/system/account/index.vue'),
    },
    {
      path: 'account_detail/:id',
      name: 'AccountDetail',
      component: () => import('/@/views/system/account/AccountDetail.vue'),
      meta: {
        hideMenu: true,
        title: t('routes.system.account_detail'),
        ignoreKeepAlive: true,
        showMenu: false,
        currentActiveMenu: '/system/account',
      },
    },
    {
      path: 'role',
      name: 'RoleManagement',
      meta: {
        title: t('routes.system.role'),
        ignoreKeepAlive: true,
      },
      component: () => import('/@/views/system/role/index.vue'),
    },
    {
      path: 'changePassword',
      name: 'ChangePassword',
      meta: {
        title: t('routes.system.password'),
        ignoreKeepAlive: true,
      },
      component: () => import('/@/views/system/password/index.vue'),
    },
  ],
};

export default system;
