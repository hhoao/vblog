<template>
  <a-input
    v-model:value="currentSelect"
    disabled
    :style="{ width }"
    :placeholder="t('component.icon.placeholder')"
    :class="prefixCls"
  >
    <template #addonAfter>
      <a-popover
        v-model="visible"
        placement="bottomLeft"
        trigger="click"
        :overlay-class-name="`${prefixCls}-popover`"
      >
        <template #title>
          <div class="flex justify-between">
            <a-input
              :placeholder="t('component.icon.search')"
              allow-clear
              @change="debounceHandleSearchChange"
            />
          </div>
        </template>

        <template #content>
          <div v-if="getPaginationList.length">
            <ScrollContainer class="border border-solid border-t-0">
              <ul class="flex flex-wrap px-2">
                <li
                  v-for="icon in getPaginationList"
                  :key="icon"
                  :class="currentSelect === icon ? 'border border-primary' : ''"
                  class="p-2 w-1/8 cursor-pointer mr-1 mt-1 flex justify-center items-center border border-solid hover:border-primary"
                  :title="icon"
                  @click="handleClick(icon)"
                >
                  <!-- <Icon :icon="icon" :prefix="prefix" /> -->
                  <SvgIcon v-if="isSvgMode" :name="icon" />
                  <Icon v-else :icon="icon" />
                </li>
              </ul>
            </ScrollContainer>
            <div v-if="getTotal >= pageSize" class="flex py-2 items-center justify-center">
              <a-pagination
                show-less-items
                size="small"
                :page-size="pageSize"
                :total="getTotal"
                @change="handlePageChange"
              />
            </div>
          </div>
          <template v-else
            ><div class="p-5"><a-empty /></div>
          </template>
        </template>

        <span v-if="isSvgMode && currentSelect" class="cursor-pointer px-2 py-1 flex items-center">
          <SvgIcon :name="currentSelect" />
        </span>
        <Icon v-else :icon="currentSelect || 'ion:apps-outline'" class="cursor-pointer px-2 py-1" />
      </a-popover>
    </template>
  </a-input>
</template>
<script lang="ts" setup>
  import { ref, watchEffect, watch } from 'vue';
  import { useDesign } from '/@/hooks/web/useDesign';
  import { ScrollContainer } from '/@/components/Container';
  import { Input, Popover, Pagination, Empty } from 'ant-design-vue';
  import Icon from './Icon.vue';
  import SvgIcon from './SvgIcon.vue';

  import iconsData from '../data/icons.data';
  import { propTypes } from '/@/utils/propTypes';
  import { usePagination } from '/@/hooks/web/usePagination';
  import { useDebounceFn } from '@vueuse/core';
  import { useI18n } from '/@/hooks/web/useI18n';
  import svgIcons from 'virtual:svg-icons-names';

  // ????????????????????????????????????WebStorm??????????????????????????????????????????unused??????
  const AInput = Input;
  const APopover = Popover;
  const APagination = Pagination;
  const AEmpty = Empty;

  function getIcons() {
    const data = iconsData as any;
    const prefix: string = data?.prefix ?? '';
    let result: string[] = [];
    if (prefix) {
      result = (data?.icons ?? []).map((item) => `${prefix}:${item}`);
    } else if (Array.isArray(iconsData)) {
      result = iconsData as string[];
    }
    return result;
  }

  function getSvgIcons() {
    return svgIcons.map((icon) => icon.replace('icon-', ''));
  }

  const props = defineProps({
    value: propTypes.string,
    width: propTypes.string.def('100%'),
    pageSize: propTypes.number.def(140),
    copy: propTypes.bool.def(false),
    mode: propTypes.oneOf<('svg' | 'iconify')[]>(['svg', 'iconify']).def('iconify'),
  });

  const emit = defineEmits(['change', 'update:value']);

  const isSvgMode = props.mode === 'svg';
  const icons = isSvgMode ? getSvgIcons() : getIcons();

  const currentSelect = ref('');
  const visible = ref(false);
  const currentList = ref(icons);

  const { t } = useI18n();
  const { prefixCls } = useDesign('icon-picker');

  const debounceHandleSearchChange = useDebounceFn(handleSearchChange, 100);

  const { getPaginationList, getTotal, setCurrentPage } = usePagination(
    currentList,
    props.pageSize,
  );

  watchEffect(() => {
    currentSelect.value = props.value;
  });

  watch(
    () => currentSelect.value,
    (v) => {
      emit('update:value', v);
      return emit('change', v);
    },
  );

  function handlePageChange(page: number) {
    setCurrentPage(page);
  }

  function handleClick(icon: string) {
    currentSelect.value = icon;
  }

  function handleSearchChange(e: ChangeEvent) {
    const value = e.target.value;
    if (!value) {
      setCurrentPage(1);
      currentList.value = icons;
      return;
    }
    currentList.value = icons.filter((item) => item.includes(value));
  }
</script>
<style lang="less">
  @prefix-cls: ~'@{namespace}-icon-picker';

  .@{prefix-cls} {
    .ant-input-group-addon {
      padding: 0;
    }

    &-popover {
      width: 300px;

      .ant-popover-inner-content {
        padding: 0;
      }

      .scrollbar {
        height: 220px;
      }
    }
  }
</style>
