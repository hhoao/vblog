<template>
  <div class="font-bold text-3xl">
    {{ articleData?.title }}
  </div>
  <a-space class="mb-10px">
    <span> <like-outlined /></span>
    <span> <star-outlined /></span>
    <span> <message-outlined /></span>
    <span> {{ articleData?.lastModification }}</span>
  </a-space>
  <markdown-viewer ref="markdownViewerRef" :value="articleData?.content" />
</template>

<script lang="ts" setup>
  import { onMounted, onUnmounted, ref, watch } from 'vue';
  import { useMenuStoreWithOut } from '/@/store/modules/menu';
  import { DetailArticleModel, DetailArticleModelParams } from '/@/api/models/DetailArticleModel';
  import { getDetailsArticle } from '/@/api/article';
  import { StarOutlined, LikeOutlined, MessageOutlined } from '@ant-design/icons-vue';
  import MarkdownViewer from '/@/components/Markdown/src/MarkdownViewer.vue';
  import { router } from '/@/router';
  import { getHeaders } from '/@/views/article/outline';

  const props = defineProps({
    id: String,
  });
  const { setMenus } = useMenuStoreWithOut();
  const markdownViewerRef = ref<Nullable<{ getIsRendered }>>(null);
  const articleData = ref<Nullable<DetailArticleModel>>(null);
  const loading = ref<boolean>(true);

  async function getDetailsArticlePageList(params: DetailArticleModelParams) {
    await getDetailsArticle(params).then((res) => {
      articleData.value = res;
    });
    loading.value = false;
  }

  onUnmounted(() => {
    setMenus([]);
  });

  onMounted(() => {
    watch(
      () => markdownViewerRef.value?.getIsRendered,
      (value) => {
        if (value) {
          setMenus(getHeaders());
          const anchor = document.getElementById(router.currentRoute.value.hash.substring(1));
          if (anchor) {
            const body = document.body;
            body.scrollTo({ left: 0, top: anchor.offsetTop, behavior: 'smooth' });
          }
        }
      },
      {
        immediate: true,
      },
    );
    props.id && getDetailsArticlePageList({ id: props.id });
  });
</script>

<style scoped lang="less"></style>
