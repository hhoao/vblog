<template>
  <div class="sticky -top-800px">
    <a-card :loading="hotArticleLoading">
      <p> 热门文章 </p>
      <ul>
        <li
          v-for="item in hotArticleListData"
          :key="item.id"
          class="list-square list-inside text-current overflow-hidden overflow-ellipsis whitespace-nowrap"
        >
          <router-link :to="`/article/${item.id}`" class="text-current">
            {{ item.title }}
          </router-link>
        </li>
      </ul>
    </a-card>
    <a-card :loading="tagLoading">
      <p>标签列表</p>
      <a-tag v-for="tag of tagList" :key="tag.id" color="blue">
        <a href="">{{ tag.label }}</a>
      </a-tag>
    </a-card>
    <a-card :loading="hotArticleLoading">
      <p> 最新评论 </p>
      <ul>
        <li
          v-for="item in newlyCommentListData"
          :key="item.id"
          class="list-square list-inside text-current overflow-hidden overflow-ellipsis whitespace-nowrap"
        >
          <router-link :to="`/article/${item.id}`" class="text-current">
            {{ item.content }}
          </router-link>
        </li>
      </ul>
    </a-card>
    <a-card :loading="aboutMeLoading">
      <p>关于</p>
      about me
    </a-card>
  </div>
</template>

<script lang="ts" setup>
  import { onMounted, ref } from 'vue';
  import { TagModel } from '/@/api/models/TagModel';
  import { getTagListApi } from '/@/api/tag';
  import { getBaseArticlePageListApi } from '/@/api/article';
  import { BaseArticleModel } from '/@/api/models/DetailArticleModel';
  import { getArticleCommentListApi } from '/@/api/comment';
  import { ArticleCommentModel } from '/@/api/models/ArticleCommentModel';

  const hotArticleLoading = ref(true);
  const tagLoading = ref(true);
  const commentLoading = ref(true);
  const aboutMeLoading = ref(false);

  const tagList = ref<TagModel[]>([]);
  const hotArticleListData = ref<BaseArticleModel[]>([]);
  const newlyCommentListData = ref<ArticleCommentModel[]>([]);

  function getTagList() {
    getTagListApi().then((res) => {
      for (let tag of res.list) {
        tagList.value.push(tag);
      }
    });
    tagLoading.value = false;
  }

  function getHotArticlePageList() {
    getBaseArticlePageListApi().then((res) => {
      for (let article of res.list) {
        hotArticleListData.value.push(article);
      }
      hotArticleLoading.value = false;
    });
  }

  function getNewlyComment() {
    getArticleCommentListApi().then((res) => {
      for (let comment of res.list) {
        newlyCommentListData.value.push(comment);
      }
    });
    commentLoading.value = false;
  }

  onMounted(() => {
    getTagList();
    getHotArticlePageList();
    getNewlyComment();
  });
</script>
<style scoped lang="less">
  .ant-card {
    margin-bottom: 10px;
  }
</style>
