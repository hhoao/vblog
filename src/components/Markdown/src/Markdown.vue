<template>
  <div ref="wrapRef"></div>
</template>
<script lang="ts" setup>
  import type { Ref } from 'vue';
  import {
    computed,
    nextTick,
    onBeforeMount,
    onBeforeUnmount,
    onDeactivated,
    ref,
    unref,
    watch,
  } from 'vue';
  import Vditor from 'vditor';
  import 'vditor/dist/index.css';
  import { useLocale } from '/@/locales/useLocale';
  import { useRootSetting } from '/@/hooks/setting/useRootSetting';
  import { handler } from '/@/layouts/default/setting/handler';
  import { HandlerEnum } from '/@/layouts/default/setting/enum';
  import { cloneDeep } from 'lodash-es';
  import { useAppStore } from '/@/store/modules/app';
  import { onMountedOrActivated } from '/@/hooks/core/onMountedOrActivated';
  import { useContentHeight } from '/@/hooks/web/useContentHeight';

  interface MarkdownOptions {
    placeholder?: string;
    value?: string;
    upload?: IUpload;
    // 是否改变网站样式设置
    changeStyleSetting?: boolean;
    // 高度填充配置, 不填写则默认不开启
    fillHeightConfig?: {
      containerRef: Ref;
    };
  }

  type Lang = 'zh_CN' | 'en_US' | 'ja_JP' | 'ko_KR' | undefined;

  const props = defineProps<MarkdownOptions>();
  const emit = defineEmits(['update:value']);
  const wrapRef = ref<ElRef>(null);
  const vditorRef = ref(null) as Ref<Nullable<Vditor>>;
  const initializedRef = ref(false);
  const appStore = useAppStore();
  const { getLocale } = useLocale();
  const { getDarkMode } = useRootSetting();
  const valueRef = ref(props.value || '');

  const getCurrentLang = computed((): 'zh_CN' | 'en_US' | 'ja_JP' | 'ko_KR' => {
    let lang: Lang;
    switch (unref(getLocale)) {
      case 'en':
        lang = 'en_US';
        break;
      case 'ja':
        lang = 'ja_JP';
        break;
      case 'ko':
        lang = 'ko_KR';
        break;
      default:
        lang = 'zh_CN';
    }
    return lang;
  });

  const intiProps: IOptions = {
    cache: {
      id: 'enableCache',
      enable: true,
    },
    height: 520,
    theme: getDarkMode.value === 'dark' ? 'dark' : 'classic',
    lang: unref(getCurrentLang),
    mode: 'sv',
    fullscreen: {
      index: 520,
    },
    tab: '    ',
    counter: { enable: true },
    toolbar: [
      'emoji',
      'headings',
      'bold',
      'italic',
      'strike',
      'line',
      'quote',
      'list',
      'ordered-list',
      'check',
      'outdent',
      'indent',
      'code',
      'inline-code',
      'insert-after',
      'insert-before',
      'undo',
      'redo',
      'upload',
      'link',
      'table',
      'record',
      'edit-mode',
      'both',
      'preview',
      'fullscreen',
      'outline',
      'code-theme',
      'content-theme',
      'export',
      'devtools',
      'info',
      'help',
      'br',
    ],
    preview: {
      hljs: {
        style: 'dracula',
      },
      actions: [],
    },
    input: (v) => {
      valueRef.value = v;
      emit('update:value', v);
    },
    after: () => {
      nextTick(() => {
        // modalFn?.redoModalHeight?.();
        initializedRef.value = true;
      });
    },
  } as MarkdownOptions;

  const instance = {
    getVditor: (): Vditor => vditorRef.value!,
  };
  let contentHeight: Ref;
  let preProjectConfig;

  watch(
    [() => getDarkMode.value, () => initializedRef.value],
    ([val, initialized]) => {
      if (!initialized) {
        return;
      }
      const theme = val === 'dark' ? 'dark' : 'classic';
      instance.getVditor()?.setTheme(theme);
    },
    {
      immediate: true,
      flush: 'post',
    },
  );

  watch(
    () => props.value,
    (v) => {
      if (v) {
        if (v !== valueRef.value) {
          instance.getVditor()?.setValue(v);
        }
        valueRef.value = v;
      }
    },
  );

  function changeStyleSettingBeforeMount() {
    if (props.changeStyleSetting) {
      let config = handler(HandlerEnum.MENU_COLLAPSED, true);
      appStore.setProjectConfig(config);
      config = handler(HandlerEnum.TABS_SHOW, false);
      appStore.setProjectConfig(config);
    }
  }
  function storeStyleSetting() {
    preProjectConfig = cloneDeep(appStore.getProjectConfig);
  }
  function destroy() {
    restoreStyleSettingAfterSwitchOtherMenu();
    const vditorInstance = unref(vditorRef);
    vditorInstance?.clearStack();
    if (!vditorInstance) return;
    try {
      vditorInstance?.destroy?.();
    } catch (error) {}
    vditorRef.value = null;
    initializedRef.value = false;
  }
  function restoreStyleSettingAfterSwitchOtherMenu() {
    appStore.setProjectConfig(preProjectConfig);
  }
  function mount(props) {
    const wrapEl = unref(wrapRef) as HTMLElement;
    if (!wrapEl) return;
    vditorRef.value = new Vditor(wrapEl, props);
  }
  function getFillHeight() {
    if (props.fillHeightConfig) {
      contentHeight = useContentHeight(
        computed(() => {
          return true;
        }),
        props.fillHeightConfig.containerRef,
        [],
        [props.fillHeightConfig.containerRef],
        0,
      ).contentHeight;
    } else {
      return;
    }
  }
  function initAndMountEditor() {
    if (props.fillHeightConfig) {
      getFillHeight();
    }
    const editorOptions = {
      ...intiProps,
      ...props,
    };
    if (editorOptions.fillHeightConfig) {
      if (contentHeight.value) {
        editorOptions.height = contentHeight.value;
        mount(editorOptions);
      } else {
        const heightWatchStopper = watch(
          contentHeight,
          () => {
            editorOptions.height = contentHeight.value ?? editorOptions.height;
            mount(editorOptions);
            heightWatchStopper();
          },
          {
            deep: true,
          },
        );
      }
    } else {
      mount(editorOptions);
    }
  }
  onBeforeMount(() => {
    storeStyleSetting();
    changeStyleSettingBeforeMount();
  });
  onMountedOrActivated(() => {
    initAndMountEditor();
  });

  onBeforeUnmount(destroy);
  onDeactivated(destroy);
  defineExpose({
    getVditor: () => {
      return instance.getVditor();
    },
  });
</script>
