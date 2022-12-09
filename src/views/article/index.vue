<template>
  <div class="w-3/5 ml-1/5 mr-1/5 mt-20px container p-20px">
    <article-card>
      <article-content :id="id" />
    </article-card>
    <article-card>
      <a-button class="float-left"> 上一篇</a-button>
      <a-button class="float-right">下一篇</a-button>
      <div class="clear-both"></div>
    </article-card>
    <article-comment />
  </div>
</template>

<script lang="ts" setup>
  import ArticleCard from './ArticleCard.vue';
  import ArticleComment from '/@/views/article/ArticleComment.vue';
  import ArticleContent from '/@/views/article/ArticleContent.vue';
  defineProps({
    id: String,
  });
</script>

<style scoped lang="less"></style>
