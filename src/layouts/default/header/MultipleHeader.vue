<template>
  <div v-if="getIsShowPlaceholderDom" :style="getPlaceholderDomStyle"></div>
  <div :style="getWrapStyle" :class="getClass">
    <LayoutHeader v-if="getShowInsetHeaderRef" />
  </div>
</template>
<script lang="ts" setup>
  import { unref, computed, CSSProperties } from 'vue';

  import LayoutHeader from './index.vue';

  import { useHeaderSetting } from '/@/hooks/setting/useHeaderSetting';
  import { useMenuSetting } from '/@/hooks/setting/useMenuSetting';
  import { useFullContent } from '/@/hooks/web/useFullContent';
  import { useAppInject } from '/@/hooks/web/useAppInject';
  import { useDesign } from '/@/hooks/web/useDesign';
  import { useLayoutHeight } from '../content/useContentViewHeight';

  const HEADER_HEIGHT = 48;

  const { setHeaderHeight } = useLayoutHeight();
  const { prefixCls } = useDesign('layout-multiple-header');

  const { getCalcContentWidth, getSplit } = useMenuSetting();
  const { getIsMobile } = useAppInject();
  const { getFixed, getShowInsetHeaderRef, getShowFullHeaderRef, getShowHeader } =
    useHeaderSetting();

  const { getFullContent } = useFullContent();

  const getIsShowPlaceholderDom = computed(() => {
    return unref(getFixed) || unref(getShowFullHeaderRef);
  });

  const getWrapStyle = computed((): CSSProperties => {
    const style: CSSProperties = {};
    if (unref(getFixed)) {
      style.width = unref(getIsMobile) ? '100%' : unref(getCalcContentWidth);
    }
    if (unref(getShowFullHeaderRef)) {
      style.top = `${HEADER_HEIGHT}px`;
    }
    return style;
  });

  const getIsFixed = computed(() => {
    return unref(getFixed) || unref(getShowFullHeaderRef);
  });

  const getPlaceholderDomStyle = computed((): CSSProperties => {
    let height = 0;
    if (
      (unref(getShowFullHeaderRef) || !unref(getSplit)) &&
      unref(getShowHeader) &&
      !unref(getFullContent)
    ) {
      height += HEADER_HEIGHT;
    }
    setHeaderHeight(height);
    return {
      height: `${height}px`,
    };
  });

  const getClass = computed(() => {
    return [prefixCls, `${prefixCls}}`, { [`${prefixCls}--fixed`]: unref(getIsFixed) }];
  });
</script>
<style lang="less" scoped>
  @prefix-cls: ~'@{namespace}-layout-multiple-header';

  .@{prefix-cls} {
    transition: width 0.2s;
    flex: 0 0 auto;

    &--dark {
      margin-left: -1px;
    }

    &--fixed {
      position: fixed;
      top: 0;
      z-index: @multiple-tab-fixed-z-index;
      width: 100%;
    }
  }
</style>
