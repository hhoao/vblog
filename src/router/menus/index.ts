import type { Menu, MenuModule } from '/@/router/types';
import { usePermissionStore } from '/@/store/modules/permission';
import { getAllParentPath, transformMenuModule } from '/@/router/helper/menuHelper';

const modules = import.meta.globEager('./modules/**/*.ts');

const menuModules: MenuModule[] = [];

Object.keys(modules).forEach((key) => {
  const mod = modules[key].default || {};
  const modList = Array.isArray(mod) ? [...mod] : [mod];
  menuModules.push(...modList);
});

// ===========================
// ==========Helper===========
// ===========================

const staticMenus: Menu[] = [];
(() => {
  menuModules.sort((a, b) => {
    return (a.orderNo || 0) - (b.orderNo || 0);
  });

  for (const menu of menuModules) {
    staticMenus.push(transformMenuModule(menu));
  }
})();

async function getAsyncMenus() {
  const permissionStore = usePermissionStore();
  return permissionStore.getFrontMenuList.filter((item) => !item.hideMenu);
}

export const getMenus = async (): Promise<Menu[]> => {
  return await getAsyncMenus();
};

export async function getCurrentParentPath(currentPath: string) {
  const menus = await getAsyncMenus();
  const allParentPath = await getAllParentPath(menus, currentPath);
  return allParentPath?.[0];
}

// Get the level 1 menu, delete children
export async function getShallowMenus(): Promise<Menu[]> {
  const menus = await getAsyncMenus();
  return menus.map((item) => ({ ...item, children: undefined }));
}

// Get the children of the menu
export async function getChildrenMenus(parentPath: string) {
  const menus = await getMenus();
  const parent = menus.find((item) => item.path === parentPath);
  if (!parent || !parent.children || !!parent?.meta?.hideChildrenInMenu) {
    return [] as Menu[];
  }

  return parent.children;
}

// function basicFilter(routes: RouteRecordNormalized[]) {
//   return (menu: Menu) => {
//     const matchRoute = routes.find((route) => {
//       if (isUrl(menu.path)) return true;
//
//       if (route.meta?.carryParam) {
//         return pathToRegexp(route.path).test(menu.path);
//       }
//       const isSame = route.path === menu.path;
//       if (!isSame) return false;
//
//       if (route.meta?.ignoreAuth) return true;
//
//       return isSame || pathToRegexp(route.path).test(menu.path);
//     });
//
//     if (!matchRoute) return false;
//     menu.icon = (menu.icon || matchRoute.meta.icon) as string;
//     menu.meta = matchRoute.meta;
//     return true;
//   };
// }
