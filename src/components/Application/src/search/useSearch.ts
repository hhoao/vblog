import { nextTick, Ref, ref, unref } from 'vue';
import { useScrollTo } from '/@/hooks/event/useScrollTo';
import { onKeyStroke, useDebounceFn } from '@vueuse/core';
import { searchArticle } from '/@/api/article';
import { useGo } from '/@/hooks/web/usePage';

export interface SearchResult {
  name: string;
  icon?: string;
  id: string;
  digest: string;
}

export function useSearch(refs: Ref<HTMLElement[]>, scrollWrap: Ref<ElRef>, emit: EmitType) {
  const searchResult = ref<SearchResult[]>([]);
  const keyword = ref('');
  const activeIndex = ref(-1);

  const go = useGo();
  const handleSearch = useDebounceFn(search, 200);

  async function search(e: ChangeEvent) {
    e?.stopPropagation();

    const key = e.target.value;
    keyword.value = key.trim();
    if (!key) {
      searchResult.value = [];
      return;
    }
    searchResult.value = await searchArticle({ queryInfo: key, pageSize: 5, pageNum: 1 }).then(
      (res) => {
        const ret: SearchResult[] = [];
        for (const item of res.list) {
          ret.push({
            id: item.id,
            name: item.title,
            digest: item.digest,
          });
        }
        return ret;
      },
    );

    activeIndex.value = 0;
  }

  // Activate when the mouse moves to a certain line
  function handleMouseenter(e: any) {
    const index = e.target.dataset.index;
    activeIndex.value = Number(index);
  }

  // Arrow key up
  function handleUp() {
    if (!searchResult.value.length) return;
    activeIndex.value--;
    if (activeIndex.value < 0) {
      activeIndex.value = searchResult.value.length - 1;
    }
    handleScroll();
  }

  // Arrow key down
  function handleDown() {
    if (!searchResult.value.length) return;
    activeIndex.value++;
    if (activeIndex.value > searchResult.value.length - 1) {
      activeIndex.value = 0;
    }
    handleScroll();
  }

  // When the keyboard up and down keys move to an invisible place
  // the scroll bar needs to scroll automatically
  function handleScroll() {
    const refList = unref(refs);
    if (!refList || !Array.isArray(refList) || refList.length === 0 || !unref(scrollWrap)) {
      return;
    }

    const index = unref(activeIndex);
    const currentRef = refList[index];
    if (!currentRef) {
      return;
    }
    const wrapEl = unref(scrollWrap);
    if (!wrapEl) {
      return;
    }
    const scrollHeight = currentRef.offsetTop + currentRef.offsetHeight;
    const wrapHeight = wrapEl.offsetHeight;
    const { start } = useScrollTo({
      el: wrapEl,
      duration: 100,
      to: scrollHeight - wrapHeight,
    });
    start();
  }

  // enter keyboard event
  async function handleEnter() {
    if (!searchResult.value.length) {
      return;
    }
    const result = unref(searchResult);
    const index = unref(activeIndex);
    if (result.length === 0 || index < 0) {
      return;
    }
    const to = result[index];
    handleClose();
    await nextTick();
    // console.log(to);
    go(`/article/${to.id}`);
  }

  // close search modal
  function handleClose() {
    searchResult.value = [];
    emit('close');
  }

  // enter search
  onKeyStroke('Enter', handleEnter);
  // Monitor keyboard arrow keys
  onKeyStroke('ArrowUp', handleUp);
  onKeyStroke('ArrowDown', handleDown);
  // esc close
  onKeyStroke('Escape', handleClose);

  return { handleSearch, searchResult, keyword, activeIndex, handleMouseenter, handleEnter };
}
