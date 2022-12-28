import type { Menu as MenuType } from '/@/router/types';
import type { MenuState } from './types';

import { computed, Ref, toRaw, unref } from 'vue';

import { useTimeoutFn } from '/@/hooks/core/useTimeout';
import { useDebounceFn } from '@vueuse/core';

export function useOpenKeys(
  menuState: MenuState,
  menus: Ref<MenuType[]>,
  accordion: Ref<boolean>,
  mixSider: Ref<boolean>,
  collapse: Ref<boolean>,
) {
  const debounceSetOpenKeys = useDebounceFn(setOpenKeys, 50);
  async function setOpenKeys() {
    const native = !mixSider.value;
    const menuList = toRaw(menus.value);
    useTimeoutFn(
      () => {
        if (menuList?.length === 0) {
          menuState.activeSubMenuNames = [];
          menuState.openNames = [];
          return;
        }
        // const keys = getAllParentPath(menuList, hash);

        // if (!unref(accordion)) {
        //   menuState.openNames = uniq([...menuState.openNames, ...keys]);
        // } else {
        //   menuState.openNames = keys;
        // }
        // menuState.activeSubMenuNames = menuState.openNames;
      },
      30,
      native,
    );
  }

  const getOpenKeys = computed(() => {
    return unref(collapse) ? [] : menuState.openNames;
  });

  return { setOpenKeys: debounceSetOpenKeys, getOpenKeys };
}
