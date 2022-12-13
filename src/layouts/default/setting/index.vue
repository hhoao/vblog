<template>
  <div :class="[prefixCls, `${prefixCls}`]" @click="openDrawer(true)">
    <Icon icon="ion:settings-outline" role="img" />
    <SettingDrawer @register="register" />
  </div>
</template>
<script lang="ts" setup>
  import SettingDrawer from './SettingDrawer';
  import Icon from '/@/components/Icon';

  import { useDrawer } from '/@/components/Drawer';
  import { useDesign } from '/@/hooks/web/useDesign';
  const { prefixCls } = useDesign('layout-setting');
  const [register, { openDrawer }] = useDrawer();
  defineExpose({
    prefixCls,
  });
</script>
