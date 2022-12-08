import { AppRouteModule } from '/@/router/types';
import { LAYOUT } from '/@/router/constant';

const article: AppRouteModule = {
  path: '/article',
  name: 'Article',
  component: LAYOUT,
  meta: { title: '文章', icon: 'article' },
  children: [
    {
      path: '/article/:id',
      name: 'ArticleIndex',
      component: () => import('/@/views/article/index.vue'),
      props: true,
      meta: { props: true },
    },
  ],
};
export default article;
