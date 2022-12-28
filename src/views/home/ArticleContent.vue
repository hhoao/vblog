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
            class="float-left h-3/4"
            alt="logo"
            :src="item.cover"
          />
          <article v-html="item.content" class="md-description text-base"></article>
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
  import { getDetailsArticlePageListApi } from '/@/api/article';
  import { BaseArticleModel } from '/@/api/models/DetailArticleModel';
  import { StarOutlined, LikeOutlined, MessageOutlined } from '@ant-design/icons-vue';
  import showdown from 'showdown';
  import { useDesign } from '/@/hooks/web/useDesign';

  const articleListData = ref<BaseArticleModel[]>([]);
  const loading = ref<boolean>(true);
  let { prefixCls } = useDesign('article-content');
  let converter = new showdown.Converter({ metadata: true });
  converter.setFlavor('github');

  async function getArticlePageList() {
    await getDetailsArticlePageListApi().then((res) => {
      for (let article of res.list) {
        article.content = converter.makeHtml(article.content);
        articleListData.value.push(article);
      }
    });
    loading.value = false;
  }

  onMounted(() => {
    getArticlePageList();
  });
</script>

<style lang="less">
  @prefix-cls: ~'@{namespace}-article-content';
  .@{prefix-cls} {
    .ant-card {
      margin-bottom: 10px;
    }

    .md-description {
      h1 {
        font-size: 1em;
      }

      * {
        margin: 0px;
      }
    }
  }
</style>
