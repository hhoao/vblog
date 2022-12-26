<template>
  <div class="w-3/5 ml-1/5 mr-1/5 mt-20px container p-20px">
    <article-content :id="id" />
    <a-button class="float-left"> 上一篇</a-button>
    <a-button class="float-right">下一篇</a-button>
    <div class="clear-both"></div>
    <article-comment />
  </div>
</template>

<script lang="ts" setup>
  import ArticleComment from '/@/views/article/ArticleComment.vue';
  import ArticleContent from '/@/views/article/ArticleContent.vue';
  import { onMounted } from 'vue';
  // import { createRouter } from 'vue-router';
  defineProps({
    id: String,
  });
  onMounted(() => {});
</script>

<style lang="less"></style>
