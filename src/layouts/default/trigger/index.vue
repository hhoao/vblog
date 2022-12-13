<template>
  <div :class="[prefixCls]">
    <SiderTrigger v-if="sider" />
    <HeaderTrigger v-else />
  </div>
</template>
<script lang="ts" setup>
  import { propTypes } from '/@/utils/propTypes';
  import HeaderTrigger from './HeaderTrigger.vue';
  import SiderTrigger from '/@/layouts/default/trigger/SiderTrigger.vue';
  import { useDesign } from '/@/hooks/web/useDesign';

  defineProps({
    sider: propTypes.bool.def(true),
  });
  const { prefixCls } = useDesign('layout-trigger');
</script>
<style lang="less">
  @prefix-cls: ~'@{namespace}-layout-trigger';
  .@{prefix-cls} {
    &:hover {
      border: 1px #b1b1b1 solid;
      transition: border-color 1000ms;
      cursor: pointer;
    }

    svg {
      margin: 10px;
      height: 16px;
      width: 16px;
    }
  }
</style>
