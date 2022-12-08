<template>
  <button @click="toggleCollapsedCopy">
    <DoubleRightOutlined v-if="getCollapsed" />
    <DoubleLeftOutlined v-else />
  </button>
</template>
<script lang="ts">
  import { defineComponent } from 'vue';
  import { DoubleRightOutlined, DoubleLeftOutlined } from '@ant-design/icons-vue';
  import { useMenuSetting } from '/@/hooks/setting/useMenuSetting';

  export default defineComponent({
    name: 'SiderTrigger',
    components: { DoubleRightOutlined, DoubleLeftOutlined },
    setup() {
      const { getCollapsed, toggleCollapsed } = useMenuSetting();

      function toggleCollapsedCopy() {
        console.log('toggle');
        toggleCollapsed();
      }
      return { getCollapsed, toggleCollapsed, toggleCollapsedCopy };
    },
  });
</script>
