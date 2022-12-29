<template>
  <div :class="[$props.class, prefixCls]" v-dompurify-html="getHtmlData" v-highlight></div>
</template>

<script lang="ts" setup>
  import { computed, onUpdated, ref } from 'vue';
  import showdown from 'showdown';
  import { useDesign } from '/@/hooks/web/useDesign';

  const props = defineProps<{
    value?: string | undefined;
    class?: string;
  }>();
  let { prefixCls } = useDesign('markdown-viewer');

  const converter = new showdown.Converter({
    extensions: [],
  });
  converter.setFlavor('github');
  converter.setOption('tables', true);
  converter.setOption('tasklists', true);
  const isRendered = ref(false);

  const getHtmlData = computed(() => {
    return converter.makeHtml(props.value ?? '');
  });
  const getIsRendered = computed(() => isRendered.value);

  onUpdated(() => {
    isRendered.value = true;
  });

  defineExpose({ getIsRendered });
</script>

<style lang="less">
  @prefix-cls: ~'@{namespace}-markdown-viewer';
  .@{prefix-cls} {
    width: 100%;
    font-size: 1.2em;

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

    & pre {
      padding: 10px;
      background: @code-block-bg-color;

      & code {
        background: inherit;
      }
    }
  }
</style>
