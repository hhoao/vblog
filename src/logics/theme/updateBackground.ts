import { lighten, darken, lightness } from '/@/utils/color';
import { useAppStore } from '/@/store/modules/app';
import { ThemeEnum } from '/@/enums/appEnum';
import { setCssVar } from './util';

const HEADER_BG_COLOR_VAR = '--header-bg-color';
const HEADER_BG_HOVER_COLOR_VAR = '--header-bg-hover-color';
const HEADER_MENU_ACTIVE_BG_COLOR_VAR = '--header-active-menu-bg-color';

const SIDER_ACTIVE_BG_COLOR = '--sider-active-bg-color';
const SIDER_DARK_BG_COLOR = '--sider-dark-bg-color';
const SIDER_DARK_DARKEN_BG_COLOR = '--sider-dark-darken-bg-color';
const SIDER_LIGHTEN_BG_COLOR = '--sider-dark-lighten-bg-color';

/**
 * Change the background color of the top header
 * @param color
 */
export function updateHeaderBgColor(color?: string) {
  const appStore = useAppStore();
  const darkMode = appStore.getDarkMode === ThemeEnum.DARK;
  if (!color) {
    if (darkMode) {
      color = '#151515';
    } else {
      color = appStore.getHeaderSetting.bgColor;
    }
  }
  const lightly = lightness(color!.toLowerCase());
  let isDark = false;
  if (lightly) {
    isDark = lightly <= 0.5;
    let hoverColor: string;
    if (!isDark && lightly > 0.9) {
      hoverColor = darken(color!, 6);
    } else {
      hoverColor = lighten(color!, 6);
    }
    setCssVar(HEADER_BG_COLOR_VAR, color);
    setCssVar(HEADER_BG_HOVER_COLOR_VAR, hoverColor);
    setCssVar(HEADER_MENU_ACTIVE_BG_COLOR_VAR, hoverColor);
  }
  // bg color
  // setCssVar(HEADER_BG_COLOR_VAR, color);

  // hover color
  // const hoverColor = lighten(color!, 6);
  // setCssVar(HEADER_BG_HOVER_COLOR_VAR, hoverColor);
  // setCssVar(HEADER_MENU_ACTIVE_BG_COLOR_VAR, hoverColor);

  // Determine the depth of the color value and automatically switch the theme
  // const isDark = colorIsDark(color!);

  appStore.setProjectConfig({
    headerSetting: {
      theme: isDark ? ThemeEnum.DARK : ThemeEnum.LIGHT,
    },
  });
}

/**
 * Change the background color of the left menu
 * @param color  bg color
 */
export function updateSidebarBgColor(color?: string) {
  const appStore = useAppStore();

  // if (!isHexColor(color)) return;
  const darkMode = appStore.getDarkMode === ThemeEnum.DARK;
  if (!color) {
    if (darkMode) {
      color = '#212121';
    } else {
      color = appStore.getMenuSetting.bgColor;
    }
  }
  const lightly = lightness(color!.toLowerCase());
  let isDark = true;
  if (lightly) {
    isDark = lightly <= 0.5;
    let postColor: string;
    let activeColor: string;
    if (!isDark && !darkMode) {
      if (lightly > 0.9) {
        postColor = darken(color!, 4);
        activeColor = darken(color!, 8);
      } else {
        postColor = darken(color!, 2);
        activeColor = darken(color!, 6);
      }
    } else {
      postColor = lighten(color!, 3);
      activeColor = lighten(color!, 6);
    }
    setCssVar(SIDER_DARK_DARKEN_BG_COLOR, postColor);
    setCssVar(SIDER_ACTIVE_BG_COLOR, activeColor);
    setCssVar(SIDER_LIGHTEN_BG_COLOR, postColor);
    setCssVar(SIDER_DARK_BG_COLOR, color);
  }
  appStore.setProjectConfig({
    menuSetting: {
      theme: isDark ? ThemeEnum.DARK : ThemeEnum.LIGHT,
    },
  });
}
