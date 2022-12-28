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
  <markdown-viewer
    ref="markdownViewerRef"
    :value="articleData?.content"
    :toc-wrapper="tocWrapper"
  />
</template>

<script lang="ts" setup>
  import { Menu } from '/@/router/types';
  import { onMounted, reactive, ref, watch } from 'vue';
  import { useMenuStoreWithOut } from '/@/store/modules/menu';
  import { DetailArticleModel, DetailArticleModelParams } from '/@/api/models/DetailArticleModel';
  import { getDetailsArticle } from '/@/api/article';
  import { StarOutlined, LikeOutlined, MessageOutlined } from '@ant-design/icons-vue';
  import MarkdownViewer from '/@/components/Markdown/src/MarkdownViewer.vue';
  import { router } from '/@/router';

  interface TocItem {
    anchor: string;
    level: number;
    text: string;
  }

  interface MenuWrapper extends Menu {
    level: number;
    parent?: MenuWrapper;
  }

  const props = defineProps({
    id: String,
  });
  const tocWrapper = reactive<{ toc: TocItem[] }>({ toc: [] });
  const { setMenus } = useMenuStoreWithOut();
  const markdownViewerRef = ref<Nullable<{ getIsRendered }>>(null);
  const articleData = ref<Nullable<DetailArticleModel>>(null);
  const loading = ref<boolean>(true);

  watch(
    () => tocWrapper,
    () => {
      if (tocWrapper.toc.length == 0) return;
      let menus: MenuWrapper[] = [];
      if (tocWrapper.toc.length == 0) {
        return;
      }
      // console.log(router.currentRoute.value.params);
      let curMenu: MenuWrapper = {
        name: tocWrapper.toc[0].text,
        path: tocWrapper.toc[0].text,
        meta: { anchorJump: true },
        children: [],
        level: tocWrapper.toc[0].level,
      };
      menus.push(curMenu);
      for (let i = 1; i < tocWrapper.toc.length; i++) {
        let nextMenu: MenuWrapper = {
          name: tocWrapper.toc[i].text,
          path: tocWrapper.toc[i].text,
          meta: { anchorJump: true },
          children: [],
          level: tocWrapper.toc[i].level,
        };
        while (nextMenu.level <= curMenu.level && curMenu.parent) {
          curMenu = curMenu.parent;
        }
        if (nextMenu.level > curMenu.level) {
          curMenu.children?.push(nextMenu);
          nextMenu.parent = curMenu;
        } else {
          menus.push(nextMenu);
        }
        curMenu = nextMenu;
      }
      setMenus(menus);
    },

    {
      deep: true,
    },
  );

  async function getDetailsArticlePageList(params: DetailArticleModelParams) {
    await getDetailsArticle(params).then((res) => {
      articleData.value = res;
    });
    loading.value = false;
  }
  onMounted(() => {
    watch(
      () => markdownViewerRef.value?.getIsRendered,
      (value) => {
        if (value == true) {
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
