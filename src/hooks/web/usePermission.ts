import type { RouteRecordRaw } from 'vue-router';
import { usePermissionStore } from '/@/store/modules/permission';
import { useUserStore } from '/@/store/modules/user';

import { useTabs } from './useTabs';

import { resetRouter, router } from '/@/router';
import { RoleEnum } from '/@/enums/roleEnum';
import { useMultipleTabStore } from '/@/store/modules/multipleTab';

// User permissions related operations
export function usePermission() {
  const userStore = useUserStore();
  const permissionStore = usePermissionStore();
  const { closeAll } = useTabs(router);

  /**
   * Reset and regain authority resource information
   * @param id
   */
  async function resume() {
    const tabStore = useMultipleTabStore();
    tabStore.clearCacheTabs();
    resetRouter();
    const routes = await permissionStore.buildRoutesAction();
    routes.forEach((route) => {
      router.addRoute(route as unknown as RouteRecordRaw);
    });
    permissionStore.setLastBuildMenuTime();
    closeAll();
  }

  /**
   * Determine whether there is permission
   */
  function hasPermission(value?: RoleEnum | RoleEnum[] | string | string[], def = true): boolean {
    if (!value) {
      return def;
    }
    return userStore.getRole == (value as RoleEnum);
  }

  /**
   * Change roles
   * @param roles
   */
  async function changeRole(roles: RoleEnum): Promise<void> {
    userStore.setRole(roles);
    await resume();
  }

  /**
   * refresh menu data
   */
  async function refreshMenu() {
    resume();
  }

  return { changeRole, hasPermission, refreshMenu };
}
