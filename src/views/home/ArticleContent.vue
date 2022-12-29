<template>
  <div :class="prefixCls">
    <a-card v-for="item of articleListData" :key="item" class="mb-30px">
      <a-skeleton :loading="loading" active avatar>
        <div class="font-bold mb-10px text-st">
          <router-link :to="`/article/${item.id}`" class="text-current text-lg">
            {{ item.title }}
          </router-link>
        </div>
        <div
          class="clear-both overflow-hidden overflow-ellipsis text-gray-500"
          style="
            overflow: hidden;
            text-overflow: ellipsis;
            display: -webkit-box;
            -webkit-box-orient: vertical;
            -webkit-line-clamp: 5;
          "
        >
          <img
            v-if="!loading && item.cover"
            class="float-left h-3/4 w-2/7 m-2"
            alt="logo"
            :src="item.cover"
          />
          <article v-html="item.content" class="md-description text-base"></article>
        </div>
        <p class="text-base mt-4">
          <a-space>
            <span> <LikeOutlined /></span>
            <span> <StarOutlined /></span>
            <span> <MessageOutlined /></span>
            <span> {{ item.lastModification }}</span>
          </a-space>
        </p>
      </a-skeleton>
    </a-card>
    <div>
      <a-pagination
        v-model:current="page.pageNum"
        show-quick-jumper
        :total="page.total"
        @change="pageOnChange"
      />
    </div>
  </div>
</template>

<script lang="ts" setup>
  import { onMounted, reactive, ref } from 'vue';
  import { getDetailsArticlePageListApi } from '/@/api/article';
  import { BaseArticleModel, DetailArticleModelPageParams } from '/@/api/models/DetailArticleModel';
  import { StarOutlined, LikeOutlined, MessageOutlined } from '@ant-design/icons-vue';
  import showdown from 'showdown';
  import { useDesign } from '/@/hooks/web/useDesign';
  import { BasicPageParams } from '/@/api/models/BaseModel';

  const articleListData = ref<BaseArticleModel[]>([]);
  const loading = ref<boolean>(true);
  const page = reactive<BasicPageParams>({
    pageNum: 1,
    pageSize: 10,
    total: 100,
  });
  let { prefixCls } = useDesign('article-content');
  let converter = new showdown.Converter({ metadata: true });
  converter.setFlavor('github');

  function pageOnChange(pageNum, pageSize) {
    getArticlePageList({ pageNum, pageSize });
  }

  function init() {
    getArticlePageList({ pageNum: 1, pageSize: 10 });
  }

  async function getArticlePageList(pageParam: DetailArticleModelPageParams) {
    await getDetailsArticlePageListApi(pageParam).then((res) => {
      page.pageNum = res.pageNum;
      page.pageSize = res.pageSize;
      page.totalPage = res.totalPage;
      page.total = Number(res.total);
      for (let article of res.list) {
        article.content = converter.makeHtml(article.content);
        articleListData.value.push(article);
      }
    });
    loading.value = false;
  }

  onMounted(() => {
    init();
  });
</script>

<style lang="less">
  @prefix-cls: ~'@{namespace}-article-content';
  .@{prefix-cls} {
    .ant-card {
      margin-bottom: 10px;
    }

    .md-description {
      * {
        font-size: 1em;
        margin: 0px;
      }
    }
  }
</style>
