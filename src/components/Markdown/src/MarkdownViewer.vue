<template>
  <div v-html="getHtmlData" :class="$props.class" class="markdown-viewer"></div>
</template>

<script lang="ts" setup>
  import { computed, onMounted, watch } from 'vue';
  import showdown from 'showdown';
  import showdownToc from 'showdown-toc';
  import hljs from 'highlight.js';

  const props = defineProps<{
    tocWrapper: object;
    value?: string | undefined;
    class?: string;
  }>();
  const converter = new showdown.Converter({
    extensions: [showdownToc(props.tocWrapper)],
  });
  converter.setOption('tables', true);

  const getHtmlData = computed(() => {
    return converter.makeHtml(props.value ?? '');
  });
  watch(
    () => props.value,
    (value) => {
      if (value) {
        document.querySelectorAll('pre code').forEach((el) => {
          hljs.highlightElement(el);
        });
      }
    },
    {
      flush: 'post',
    },
  );

  onMounted(() => {});
</script>

<style lang="less">
  .markdown-viewer {
    width: 100%;
    & h1,
    h2,
    h3,
    h4,
    h5 {
      font-weight: bold;
    }
    & code {
      background: @code-bg-color;
    }
    & pre code {
      background: @code-block-bg-color;
    }
  }
</style>
