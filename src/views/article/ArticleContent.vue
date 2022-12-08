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
  <markdown-viewer :value="articleData?.content" :toc-wrapper="tocWrapper" />
</template>

<script lang="ts" setup>
  import { Menu } from '/@/router/types';
  import { onMounted, reactive, ref, watch } from 'vue';
  import { useMenuStoreWithOut } from '/@/store/modules/menu';
  import { DetailArticleModel, DetailArticleModelParams } from '/@/api/models/DetailArticleModel';
  import { getDetailsArticle } from '/@/api/article';
  import { StarOutlined, LikeOutlined, MessageOutlined } from '@ant-design/icons-vue';
  import MarkdownViewer from '/@/components/Markdown/src/MarkdownViewer.vue';

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
      let curMenu: MenuWrapper = {
        name: tocWrapper.toc[0].text,
        path: tocWrapper.toc[0].text,
        children: [],
        level: tocWrapper.toc[0].level,
      };
      menus.push(curMenu);
      for (let i = 1; i < tocWrapper.toc.length; i++) {
        let nextMenu: MenuWrapper = {
          name: tocWrapper.toc[i].text,
          path: tocWrapper.toc[i].text,
          children: [],
          level: tocWrapper.toc[i].level,
        };
        while (nextMenu.level <= curMenu.level && curMenu.parent) {
          curMenu = curMenu.parent;
        }
        if (nextMenu.level > curMenu.level) {
          curMenu.children?.push(nextMenu);
        }
        nextMenu.parent = curMenu;
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
      articleData.value.content =
        '# 认识 rollup\n' +
        '\n' +
        'Rollup 是一个 JavaScript 模块打包工具，可以将多个小的代码片段编译为完整的库和应用。\n' +
        '\n' +
        '官网: https://www.rollupjs.com/\n' +
        '\n' +
        '## 概述\n' +
        '\n' +
        'Rollup 是一个 JavaScript 模块打包工具，可以将多个小的代码片段编译为完整的库和应用。与传统的 CommonJS 和 AMD 这一类非标准化的解决方案不同，Rollup 使用的是 ES6 版本 Javascript 中的模块标准。新的 ES 模块可以让你自由、无缝地按需使用你最喜爱的库中那些有用的单个函数。这一特性在未来将随处可用，但 Rollup 让你现在就可以，想用就用。\n' +
        '\n' +
        '### 安装[​](https://www.rollupjs.com/#%E5%AE%89%E8%A3%85 "标题的直接链接")\n' +
        '\n' +
        '```\n' +
        'npm install --global rollup\n' +
        '```\n' +
        '\n' +
        '这样安装可以让 Rollup 成为全局可用的命令行，你也可以仅将其安装于本地。\n' +
        '\n' +
        '### 快速开始\n' +
        '\n' +
        'Rollup 可以通过两种方式使用：使用命令行方式，可以为命令行传入一个可选的配置文件。或者使用 `JavaScript API`方式。运行 `rollup --help` 可以查看可用的选项和参数。\n' +
        '\n' +
        '以下命令假设你以 `main.js` 文件作为应用的入口点，并将所有的引入编译为单文件 `bundle.js`。\n' +
        '\n' +
        '用于浏览器：\n' +
        '\n' +
        '```javascript\n' +
        "# 编译为一个在 <script> 标签中可用的自运行函数 ('iife')\n" +
        'rollup main.js --file bundle.js --format iife\n' +
        '```\n' +
        '\n' +
        '用于 Node.js：\n' +
        '\n' +
        '```shell\n' +
        "# 编译为 CommonJS 模块 ('cjs')\n" +
        'rollup main.js --file bundle.js --format cjs\n' +
        '```\n' +
        '\n' +
        '同时用于浏览器和 Node.js：\n' +
        '\n' +
        '```shell\n' +
        '# 需要为 UMD 格式的包指定一个名称\n' +
        'rollup main.js --file bundle.js --format umd --name "myBundle"\n' +
        '```\n' +
        '\n' +
        '## 功能\n' +
        '\n' +
        '- 可以将Javascript新模块标准编译为CommonJS、AMD以及IIFE风格等多种格式。\n' +
        '  \n' +
        '- 代码进行静态分析\n' +
        '  \n' +
        '\n' +
        '## 配置文件\n' +
        '\n' +
        '具体配置文件信息: https://www.rollupjs.com/guide/big-list-of-options\n' +
        '\n' +
        'Rollup 的配置文件并不是必须的，但是配置文件非常强大而且很方便，所以我们**推荐你能够使用**。\n' +
        '\n' +
        '```javascript\n' +
        '//rollup.config.js\n' +
        '// rollup.config.js\n' +
        '\n' +
        'export default { // 可以是一个数组（用于多个输入的情况）\n' +
        '  // 核心的输入选项\n' +
        '  external,\n' +
        '  input, // 必要项\n' +
        '  plugins,\n' +
        '\n' +
        '  // 高级输入选项\n' +
        '  cache,\n' +
        '  onwarn,\n' +
        '  preserveEntrySignatures,\n' +
        '  strictDeprecations,\n' +
        '\n' +
        '  // 危险区\n' +
        '  acorn,\n' +
        '  acornInjectPlugins,\n' +
        '  context,\n' +
        '  moduleContext,\n' +
        '  preserveSymlinks,\n' +
        '  shimMissingExports,\n' +
        '  treeshake,\n' +
        '\n' +
        '  // 实验性\n' +
        '  experimentalCacheExpiry,\n' +
        '  perf,\n' +
        '\n' +
        '  output: { // 必要项 (可以是一个数组，用于多输出的情况)\n' +
        '    // 核心的输出选项\n' +
        '    dir,\n' +
        '    file,\n' +
        '    format, // 必要项\n' +
        '    globals,\n' +
        '    name,\n' +
        '    plugins,\n' +
        '\n' +
        '    // 高级输出选项\n' +
        '    assetFileNames,\n' +
        '    banner,\n' +
        '    chunkFileNames,\n' +
        '    compact,\n' +
        '    entryFileNames,\n' +
        '    extend,\n' +
        '    footer,\n' +
        '    hoistTransitiveImports,\n' +
        '    inlineDynamicImports,\n' +
        '    interop,\n' +
        '    intro,\n' +
        '    manualChunks,\n' +
        '    minifyInternalExports,\n' +
        '    outro,\n' +
        '    paths,\n' +
        '    preserveModules,\n' +
        '    sourcemap,\n' +
        '    sourcemapExcludeSources,\n' +
        '    sourcemapFile,\n' +
        '    sourcemapPathTransform,\n' +
        '\n' +
        '    // 危险区\n' +
        '    amd,\n' +
        '    esModule,\n' +
        '    exports,\n' +
        '    externalLiveBindings,\n' +
        '    freeze,\n' +
        '    indent,\n' +
        '    namespaceToStringTag,\n' +
        '    noConflict,\n' +
        '    preferConst,\n' +
        '    strict,\n' +
        '    systemNullSetters\n' +
        '  },\n' +
        '\n' +
        '  watch: {\n' +
        '    buildDelay,\n' +
        '    chokidar,\n' +
        '    clearScreen,\n' +
        '    skipWrite,\n' +
        '    exclude,\n' +
        '    include\n' +
        '  } | false\n' +
        '};\n' +
        '```\n' +
        '\n' +
        '## Rollup Plugin Visualizer\n' +
        '\n' +
        '可视化并分析您的 (Rollup) 包，以查看哪些模块占用了空间。\n' +
        '\n' +
        '## 安装\n' +
        '\n' +
        '```shell\n' +
        'npm install --save-dev rollup-plugin-visualizer\n' +
        '```\n' +
        '\n' +
        '## 用法\n' +
        '\n' +
        '使用import:\n' +
        '\n' +
        '```javascript\n' +
        '// es\n' +
        'import { visualizer } from "rollup-plugin-visualizer";\n' +
        '// or\n' +
        '// cjs\n' +
        'const { visualizer } = require("rollup-plugin-visualizer");\n' +
        '```\n' +
        '\n' +
        '使用`rollup.config.js`配置文件\n' +
        '\n' +
        '```javascript\n' +
        '//rollup.config.js\n' +
        'module.exports = {\n' +
        '  plugins: [\n' +
        '    // put it the last one\n' +
        '    visualizer(),\n' +
        '  ],\n' +
        '};\n' +
        '```\n' +
        '\n' +
        '通过vite:\n' +
        '\n' +
        '```javascript\n' +
        'module.exports = {\n' +
        '  plugins: [visualizer()],\n' +
        '};\n' +
        '```\n' +
        '\n' +
        '使用vite TypeScript`vite.config.ts`:\n' +
        '\n' +
        '```javascript\n' +
        "import { defineConfig, type PluginOption } from 'vite'\n" +
        'export default defineConfig({\n' +
        '  plugins: [visualizer() as PluginOption],\n' +
        '})\n' +
        '```\n' +
        '\n' +
        '通过vite SvelteKit(`vite.config.ts`):\n' +
        '\n' +
        '```javascript\n' +
        'const config = {\n' +
        '  plugins: [\n' +
        '    visualizer({\n' +
        '      emitFile: true,\n' +
        '      file: "stats.html",\n' +
        '    }),\n' +
        '  ],\n' +
        '};\n' +
        '\n' +
        'export default config;\n' +
        '```\n' +
        '\n' +
        '打包后会生成一个stat.html页面';
    });
    loading.value = false;
  }
  onMounted(() => {
    props.id && getDetailsArticlePageList({ id: props.id });
  });
</script>

<style scoped lang="less"></style>
