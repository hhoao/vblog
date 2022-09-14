import type { AppRouteModule } from '/@/router/types';

import { LAYOUT } from '/@/router/constant';
import { t } from '/@/hooks/web/useI18n';

const account: AppRouteModule = {
  path: '/account',
  name: 'PageDemo',
  component: LAYOUT,
  redirect: '/account/center',
  meta: {
    orderNo: 20,
    icon: 'ion:aperture-outline',
    title: t('routes.account.moduleName'),
  },
  children: [
    {
      path: 'center',
      name: 'AccountCenterPage',
      component: () => import('/@/views/account/center/index.vue'),
      meta: {
        title: t('routes.account.accountCenter'),
      },
    },
    {
      path: 'setting',
      name: 'AccountSettingPage',
      component: () => import('/@/views/account/setting/index.vue'),
      meta: {
        title: t('routes.account.accountSetting'),
      },
    },
  ],
};

export default account;
