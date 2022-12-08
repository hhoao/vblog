import type { Router } from 'vue-router';
import { useAppStore } from '/@/store/modules/app';
import { PageEnum } from '/@/enums/pageEnum';
import { removeTabChangeListener } from '/@/logics/mitt/routeChange';

export function createStateGuard(router: Router) {
  router.afterEach((to) => {
    // Just enter the login page and clear the authentication information
    if (to.path === PageEnum.BASE_LOGIN) {
      const appStore = useAppStore();
      appStore.resetAllState();
      removeTabChangeListener();
    }
  });
}
