<template>
  <div class="w-3/5 ml-1/5 mr-1/5 mt-20px container p-20px">
    <article-content :id="id" />
    <a-divider />
    <article-left-right />
    <a-divider />
    <div class="divide-y"></div>
    <div class="clear-both"></div>
    <article-comment />
  </div>
</template>

<script lang="ts" setup>
  import ArticleComment from '/@/views/article/ArticleComment.vue';
  import ArticleContent from '/@/views/article/ArticleContent.vue';
  import { onMounted } from 'vue';
  import ArticleLeftRight from '/@/views/article/ArticleLeftRight.vue';

  defineProps({
    id: String,
  });
  onMounted(() => {});
</script>

<style lang="less"></style>
