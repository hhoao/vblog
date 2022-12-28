<template>
  <div :class="prefixCls">
    <a class="float-left"> <LeftOutlined />上一篇</a>
    <a class="float-right">下一篇<RightOutlined /></a>
  </div>
</template>
<script setup>
  import { LeftOutlined, RightOutlined } from '@ant-design/icons-vue';
  import { useDesign } from '/@/hooks/web/useDesign';

  let { prefixCls } = useDesign('article-left-right');
</script>

<style lang="less">
  @prefix-cls: ~'@{namespace}-article-left-right';

  .@{prefix-cls} {
    a {
      color: @text-hover-color;
    }
  }
</style>
