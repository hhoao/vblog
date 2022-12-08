<template>
  <RouterView>
    <template #default="{ Component, route }">
      <transition :name="getBasicTransition" mode="out-in" appear>
        <component :is="Component" :key="route.fullPath" />
      </transition>
    </template>
  </RouterView>
</template>

<script lang="ts">
  import { defineComponent } from 'vue';

  import { useTransitionSetting } from '/@/hooks/setting/useTransitionSetting';

  export default defineComponent({
    name: 'PageLayout',
    components: {},
    setup() {
      const { getBasicTransition } = useTransitionSetting();
      return {
        getBasicTransition,
      };
    },
  });
</script>
