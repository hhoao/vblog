<template>
  <Layout :class="prefixCls">
    <LayoutFeatures />
    <Layout :class="[layoutClass]">
      <LayoutSideBar v-if="getShowSidebar" />
      <Layout :class="`${prefixCls}-main`">
        <LayoutMultipleHeader v-if="getShowHeader" />
        <LayoutTrigger v-if="getShowSidebar" class="fixed z-1" :sider="false" />
        <LayoutContent />
        <LayoutFooter />
      </Layout>
    </Layout>
  </Layout>
</template>

<script lang="ts" setup>
  import { computed } from 'vue';
  import { Layout } from 'ant-design-vue';

  import LayoutContent from './content/index.vue';
  import LayoutSideBar from './sider/index.vue';

  import { useHeaderSetting } from '/@/hooks/setting/useHeaderSetting';
  import { useDesign } from '/@/hooks/web/useDesign';
  import { useMenuSetting } from '/@/hooks/setting/useMenuSetting';
  import LayoutTrigger from '/@/layouts/default/trigger/index.vue';
  import LayoutFooter from '/@/layouts/default/footer/index.vue';
  import LayoutFeatures from '/@/layouts/default/feature/index.vue';
  import LayoutMultipleHeader from '/@/layouts/default/header/MultipleHeader.vue';
  const { getShowSidebar } = useMenuSetting();
  const { getShowHeader } = useHeaderSetting();

  const { prefixCls } = useDesign('default-layout');

  const layoutClass = computed(() => {
    let cls: string[] = ['ant-layout'];
    return cls;
  });
</script>
<style lang="less">
  @prefix-cls: ~'@{namespace}-default-layout';

  .@{prefix-cls} {
    display: flex;
    width: 100%;
    min-height: 100%;
    background-color: @content-bg;
    flex-direction: column;

    > .ant-layout {
      min-height: 100%;
    }

    &-main {
      width: 100%;
      margin-left: 1px;
    }
  }
</style>
