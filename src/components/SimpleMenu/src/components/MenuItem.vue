<template>
  <li :class="getClass" :style="getCollapse ? {} : getItemStyle" @click.stop="handleClickItem">
    <Tooltip v-if="showTooptip" placement="right">
      <template #title>
        <slot name="title"></slot>
      </template>
      <div :class="`${prefixCls}-tooltip`">
        <slot></slot>
      </div>
    </Tooltip>

    <template v-else>
      <slot></slot>
      <slot name="title"></slot>
    </template>
  </li>
</template>

<script lang="ts">
  import { computed, defineComponent, getCurrentInstance, PropType, ref, unref, watch } from 'vue';
  import { useDesign } from '/@/hooks/web/useDesign';
  import { propTypes } from '/@/utils/propTypes';
  import { useMenuItem } from './useMenu';
  import { Tooltip } from 'ant-design-vue';
  import { useSimpleRootMenuContext } from './useSimpleMenuContext';
  import { Menu } from '/@/router/types';
  import { router } from '/@/router';

  export default defineComponent({
    name: 'MenuItem',
    components: { Tooltip },
    props: {
      meta: {
        type: Object as PropType<Menu>,
        default: () => ({}),
      },
      disabled: propTypes.bool,
    },
    setup(props, { slots }) {
      const instance = getCurrentInstance();

      const active = ref(false);

      const { getItemStyle, getParentList, getParentMenu, getParentRootMenu } =
        useMenuItem(instance);

      const { prefixCls } = useDesign('menu');

      const { rootMenuEmitter, activeName } = useSimpleRootMenuContext();

      const getClass = computed(() => {
        return [
          `${prefixCls}-item`,
          {
            [`${prefixCls}-item-active`]: unref(active),
            [`${prefixCls}-item-selected`]: unref(active),
            [`${prefixCls}-item-disabled`]: !!props.disabled,
          },
        ];
      });

      const getCollapse = computed(() => unref(getParentRootMenu)?.props.collapse);

      const showTooptip = computed(() => {
        return unref(getParentMenu)?.type.name === 'Menu' && unref(getCollapse) && slots.title;
      });

      function handleClickItem() {
        const { disabled } = props;
        if (disabled) {
          return;
        }

        rootMenuEmitter.emit('on-menu-item-select', props.meta);
        if (unref(getCollapse)) {
          return;
        }
        const { uidList } = getParentList();

        rootMenuEmitter.emit('on-update-opened', {
          opend: false,
          parent: instance?.parent,
          uidList: uidList,
        });
        let anchorName = '';
        let s = props.meta.name.replaceAll(' ', '-').toLowerCase();
        for (let ch of s) {
          let code = ch.charCodeAt(0);
          if (
            ch.charCodeAt(0) > 127 ||
            (code >= 97 && code <= 122) ||
            (code >= 65 && code <= 90) ||
            (code >= 48 && code <= 57) ||
            ch == '-'
          ) {
            anchorName += ch;
          }
        }
        const anchor = document.getElementById(anchorName);
        history.replaceState(
          router.currentRoute.value.path,
          '',
          router.currentRoute.value.path + '#' + anchorName,
        );
        if (anchor) {
          const body = document.body;
          body.scrollTo({ left: 0, top: anchor.offsetTop, behavior: 'smooth' });
        }
      }
      watch(
        () => activeName.value,
        (name: string) => {
          if (name === props.meta?.path) {
            const { list, uidList } = getParentList();
            active.value = true;
            list.forEach((item) => {
              if (item.proxy) {
                (item.proxy as any).active = true;
              }
            });

            rootMenuEmitter.emit('on-update-active-name:submenu', uidList);
          } else {
            active.value = false;
          }
        },
        { immediate: true },
      );

      return { getClass, prefixCls, getItemStyle, getCollapse, handleClickItem, showTooptip };
    },
  });
</script>
