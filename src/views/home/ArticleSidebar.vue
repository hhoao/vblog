<template>
  <div class="sticky -top-200px">
    <div class="overflow-hidden">
      <a-card :loading="loading" title="热门文章">
        <a-list :data-source="hotArticleListData">
          <template #renderItem="{ item }">
            <a-list-item>
              <a-list-item-meta>
                <template #title>
                  <a href="">{{ item.title }}</a>
                </template>
                <template #avatar>
                  <a-avatar src="" />
                </template>
              </a-list-item-meta>
            </a-list-item>
          </template>
        </a-list>
      </a-card>
      <a-card :loading="loading" title="标签列表">
        <a-button v-for="tag of tagList" :key="tag.id" size="small">
          {{ tag.label }}
        </a-button>
      </a-card>
      <a-card :loading="loading" title="最新留言">
        <a-list :data-source="newlyCommentListData">
          <template #renderItem="{ item }">
            <a-list-item class="text-base display-inline overflow-hidden overflow-ellipsis">
              <a-list-item-meta>
                <template #datetime>
                  <a-tooltip :title="item.datetime.format('YYYY-MM-DD HH:mm:ss')">
                    <span>{{ item.lastModification }}</span>
                  </a-tooltip>
                </template>
                <template #title>
                  <a href="" class="truncate">{{ item.content }}</a>
                </template>
                <template #avatar>
                  <a-avatar src="" />
                </template>
              </a-list-item-meta>
            </a-list-item>
          </template>
        </a-list>
      </a-card>
      <a-card :loading="loading" title="关于">about me</a-card>
    </div>
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

  const loading = ref(false);
  const tagList = ref<TagModel[]>([]);
  const hotArticleListData = ref<BaseArticleModel[]>([]);
  const newlyCommentListData = ref<ArticleCommentModel[]>([]);

  function getTagList() {
    getTagListApi().then((res) => {
      for (let tag of res.list) {
        tagList.value.push(tag);
      }
    });
  }

  function getHotArticlePageList() {
    getBaseArticlePageListApi().then((res) => {
      for (let article of res.list) {
        hotArticleListData.value.push(article);
      }
      loading.value = false;
    });
  }
  function getNewlyComment() {
    getArticleCommentListApi().then((res) => {
      for (let comment of res.list) {
        newlyCommentListData.value.push(comment);
      }
    });
    loading.value = false;
  }
  onMounted(() => {
    getTagList();
    getHotArticlePageList();
    getNewlyComment();
  });
</script>
<style scoped>
  .ant-card {
    margin-bottom: 10px;
  }
</style>
