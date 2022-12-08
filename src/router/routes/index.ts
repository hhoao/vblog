import type { AppRouteModule } from '/@/router/types';

const modules = import.meta.globEager('./constantModules/**/*.ts');

const constantRoutes: AppRouteModule[] = [];

Object.keys(modules).forEach((key) => {
  const mod = modules[key].default || {};
  const modList = Array.isArray(mod) ? [...mod] : [mod];
  constantRoutes.push(...modList);
});

export const routes = [...constantRoutes];
