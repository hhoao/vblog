import type { AppRouteModule } from '/@/router/types';

import { LAYOUT } from '/@/router/constant';
import { t } from '/@/hooks/web/useI18n';

const markdown: AppRouteModule = {
  path: '/essay',
  name: 'Essay',
  component: LAYOUT,
  redirect: '/essay/writing',
  meta: {
    orderNo: 30,
    icon: 'ion:layers-outline',
    title: t('routes.essay.moduleName'),
  },
  children: [
    {
      path: 'writing',
      name: 'Writing',
      component: () => import('/@/views/essay/writing/index.vue'),
      meta: {
        title: t('routes.essay.writing'),
      },
    },
  ],
};

export default markdown;
