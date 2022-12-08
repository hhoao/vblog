<template>
  <MenuItem :key="item.path">
    <MenuItemContent v-bind="$props" :item="item" />
  </MenuItem>
</template>
<script lang="ts">
  import { defineComponent } from 'vue';
  import { MenuItem } from 'ant-design-vue';
  import { itemProps } from '../props';

  import MenuItemContent from './MenuItemContent.vue';

  export default defineComponent({
    name: 'BasicMenuItem',
    components: { MenuItem, MenuItemContent },
    props: itemProps,
    setup() {
      return {};
    },
  });
</script>
