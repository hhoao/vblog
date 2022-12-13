<template>
  <div v-if="!getIsMobile" v-show="showClassSideBarRef" :style="getHiddenDomStyle"></div>
  <a-layout-sider
    v-show="showClassSideBarRef"
    ref="sideRef"
    breakpoint="lg"
    collapsible
    :class="getSiderClass"
    :width="getMenuWidth"
    :collapsed="getCollapsed"
    :collapsed-width="getCollapsedWidth"
    :trigger="null"
    v-bind="getTriggerAttr"
    @breakpoint="onBreakpointChange"
  >
    <LayoutMenu :menu-mode="getMode" :split-type="getSplitType" />
    <DragBar ref="dragBarRef" />
  </a-layout-sider>
</template>
<script lang="ts" setup>
  import { computed, ref, unref, CSSProperties } from 'vue';

  import { MenuModeEnum, MenuSplitTyeEnum } from '/@/enums/menuEnum';

  import { useMenuSetting } from '/@/hooks/setting/useMenuSetting';
  import { useTrigger, useDragLine, useSiderEvent } from './useLayoutSider';
  import { useAppInject } from '/@/hooks/web/useAppInject';
  import { useDesign } from '/@/hooks/web/useDesign';

  import DragBar from './DragBar.vue';
  import LayoutMenu from '/@/layouts/default/menu/index.vue';

  const dragBarRef = ref<ElRef>(null);
  const sideRef = ref<ElRef>(null);

  const { getCollapsed, getMenuWidth, getSplit, getRealWidth, getMenuHidden, getIsMixMode } =
    useMenuSetting();

  const { prefixCls } = useDesign('layout-sideBar');

  const { getIsMobile } = useAppInject();

  const { getTriggerAttr } = useTrigger(getIsMobile);

  useDragLine(sideRef, dragBarRef);

  const { getCollapsedWidth, onBreakpointChange } = useSiderEvent();

  const getMode = computed(() => {
    return unref(getSplit) ? MenuModeEnum.INLINE : null;
  });

  const getSplitType = computed(() => {
    return unref(getSplit) ? MenuSplitTyeEnum.LEFT : MenuSplitTyeEnum.NONE;
  });

  const showClassSideBarRef = computed(() => {
    return unref(getSplit) ? !unref(getMenuHidden) : true;
  });

  const getSiderClass = computed(() => {
    return [
      prefixCls,
      {
        [`${prefixCls}--fixed`]: true,
        [`${prefixCls}--mix`]: unref(getIsMixMode) && !unref(getIsMobile),
      },
    ];
  });

  const getHiddenDomStyle = computed((): CSSProperties => {
    const width = `${unref(getRealWidth)}px`;
    return {
      width: width,
      overflow: 'hidden',
      flex: `0 0 ${width}`,
      maxWidth: width,
      minWidth: width,
      transition: 'all 0.2s',
    };
  });
</script>
<style lang="less" scoped>
  @prefix-cls: ~'@{namespace}-layout-sideBar';

  .@{prefix-cls} {
    //border-right: 2px @sider-border-color solid;
    --tw-shadow: 0 1px 3px 0 rgb(0 0 0/0.1), 0 1px 2px -1px rgb(0 0 0/0.1);
    --tw-shadow-colored: 0 1px 3px 0 var(--tw-shadow-color), 0 1px 2px -1px var(--tw-shadow-color);
    z-index: @layout-sider-fixed-z-index;
    background-color: @sider-bg-color !important;
    -webkit-box-shadow: var(--tw-ring-offset-shadow, 0 0 #0000), var(--tw-ring-shadow, 0 0 #0000),
      var(--tw-shadow);
    box-shadow: var(--tw-ring-offset-shadow, 0 0 #0000), var(--tw-ring-shadow, 0 0 #0000),
      var(--tw-shadow);

    &--fixed {
      position: fixed;
      top: 0;
      left: 0;
      height: 100%;
    }

    &--mix {
      top: @header-height;
      height: calc(100% - @header-height);
    }

    .ant-layout-sider-zero-width-trigger {
      top: 40%;
      z-index: 10;
    }

    & .ant-layout-sider-trigger {
      height: 36px;
      line-height: 36px;
    }
  }
</style>
