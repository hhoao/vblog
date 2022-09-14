import type { App } from 'vue';
import { Button } from './Button';
import { Input, Layout } from 'ant-design-vue';
import { PageWrapper } from '/@/components/Page';

export function registerGlobComp(app: App) {
  app.use(Input).use(Button).use(Layout).use(PageWrapper);
}
