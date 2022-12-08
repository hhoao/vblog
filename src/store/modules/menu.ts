import { defineStore } from 'pinia';
import { store } from '/@/store';
import { Menu } from '/@/router/types';

interface MenuState {
  menus: Menu[];
}

export const useMenuStore = defineStore({
  id: 'app-menu',
  state: (): MenuState => ({
    menus: [],
  }),
  getters: {
    getMenus(): Menu[] {
      return this.menus;
    },
  },
  actions: {
    setMenus(menus: Menu[]) {
      this.menus = menus;
    },
  },
});

// Need to be used outside the setup
export function useMenuStoreWithOut() {
  return useMenuStore(store);
}
