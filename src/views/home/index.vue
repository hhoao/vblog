<template>
  <div ref="container" class="m-20px">
    <a-row v-if="!getIsMobile" :gutter="20">
      <a-col :span="4" />
      <a-col :span="12">
        <article-content />
      </a-col>
      <a-col :span="4">
        <article-sidebar />
      </a-col>
      <a-col :span="4" />
    </a-row>
    <article-content v-else />
  </div>
</template>

<script setup lang="ts">
  import ArticleContent from '/@/views/home/ArticleContent.vue';
  import ArticleSidebar from '/@/views/home/ArticleSidebar.vue';
  import { ref } from 'vue';
  import { useAppInject } from '/@/hooks/web/useAppInject';

  const { getIsMobile } = useAppInject();
  const container = ref(null);
</script>

<style scoped></style>
