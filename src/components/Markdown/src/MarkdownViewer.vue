<template>
  <div :class="[$props.class, prefixCls]" v-html="getHtmlData"></div>
</template>

<script lang="ts" setup>
  import { computed, onMounted, ref, watch } from 'vue';
  import showdown from 'showdown';
  import showdownToc from 'showdown-toc';
  import hljs from 'highlight.js';
  import { useDesign } from '/@/hooks/web/useDesign';

  const props = defineProps<{
    tocWrapper: object;
    value?: string | undefined;
    class?: string;
  }>();
  let { prefixCls } = useDesign('markdown-viewer');
  const converter = new showdown.Converter({
    extensions: [showdownToc(props.tocWrapper)],
  });
  converter.setFlavor('github');
  converter.setOption('tables', true);
  converter.setOption('tasklists', true);
  const isRendered = ref(false);

  const getHtmlData = computed(() => {
    return converter.makeHtml(props.value ?? '');
  });
  watch(
    () => props.value,
    (value) => {
      if (value) {
        document.querySelectorAll('pre code').forEach((el: HTMLElement) => {
          hljs.highlightElement(el);
        });
        isRendered.value = true;
      }
    },
    {
      immediate: true,
      flush: 'post',
    },
  );
  const getIsRendered = computed(() => isRendered.value);
  // = () => {
  //  return isRendered.value;
  // };
  onMounted(() => {});

  defineExpose({ getIsRendered });
</script>

<style lang="less">
  @prefix-cls: ~'@{namespace}-markdown-viewer';
  .@{prefix-cls} {
    width: 100%;

    table {
      border-collapse: collapse;
    }

    th,
    td {
      padding: 0.4em 1em;
      border: 1px solid #dddfe3;
    }

    h1 {
      font-size: 1.4em;
    }

    h2 {
      font-size: 1.35em;
    }

    h3 {
      font-size: 1.3em;
    }

    h4 {
      font-size: 1.25em;
    }

    h5 {
      font-size: 1.2em;
    }

    h6 {
      font-size: 1.15em;
    }

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
