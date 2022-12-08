<template>
  <SiderTrigger v-if="sider" />
  <HeaderTrigger v-else />
</template>
<script lang="ts">
  import { defineComponent } from 'vue';
  import { propTypes } from '/@/utils/propTypes';
  import HeaderTrigger from './HeaderTrigger.vue';
  import SiderTrigger from '/@/layouts/default/trigger/SiderTrigger.vue';

  export default defineComponent({
    name: 'LayoutTrigger',
    components: {
      SiderTrigger: SiderTrigger,
      HeaderTrigger: HeaderTrigger,
    },
    props: {
      sider: propTypes.bool.def(true),
    },
  });
</script>
