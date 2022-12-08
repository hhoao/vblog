import { AppRouteModule } from '/@/router/types';
import { LAYOUT } from '/@/router/constant';

const home: AppRouteModule = {
  path: '/',
  name: 'Root',
  redirect: '/home',
  component: LAYOUT,
  meta: { title: '首页', icon: 'home' },
  children: [
    {
      path: '/home',
      name: 'Home',
      component: () => import('/@/views/home/index.vue'),
      meta: {},
    },
  ],
};
export default home;
