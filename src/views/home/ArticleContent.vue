<template>
  <div>
    <a-card v-for="item of articleListData" :key="item" class="mb-30px">
      <a-skeleton :loading="loading" active avatar>
        <div class="text-lg font-bold mb-10px text-st">
          <router-link :to="`/article/${item.id}`" class="text-current">
            {{ item.title }}
          </router-link>
        </div>
        <div
          class="text-base h-8rem clear-both display-inline overflow-hidden overflow-ellipsis text-gray-500"
        >
          <img
            v-if="!loading"
            class="float-left h-3/4"
            alt="logo"
            src="https://gw.alipayobjects.com/zos/rmsportal/mqaQswcyDLcXyDKnZfES.png"
          />
          {{ item.digest }}
        </div>
        <p class="text-base">
          <a-space>
            <span> <LikeOutlined /></span>
            <span> <StarOutlined /></span>
            <span> <MessageOutlined /></span>
            <span> {{ item.lastModification }}</span>
          </a-space>
        </p>
      </a-skeleton>
    </a-card>
  </div>
</template>

<script lang="ts" setup>
  import { onMounted, ref } from 'vue';
  import { getBaseArticlePageListApi } from '/@/api/article';
  import { BaseArticleModel } from '/@/api/models/DetailArticleModel';
  import { StarOutlined, LikeOutlined, MessageOutlined } from '@ant-design/icons-vue';

  const articleListData = ref<BaseArticleModel[]>([]);
  const loading = ref<boolean>(true);

  async function getArticlePageList() {
    await getBaseArticlePageListApi().then((res) => {
      for (let article of res.list) {
        articleListData.value.push(article);
      }
    });
    loading.value = false;
  }

  onMounted(() => {
    getArticlePageList();
  });
</script>

<style scoped>
  .ant-card {
    margin-bottom: 10px;
  }
</style>
