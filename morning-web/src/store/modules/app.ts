import defaultSettings from "@/settings";

// 导入 Element Plus 中英文语言包
import zhCn from "element-plus/es/locale/lang/zh-cn";
import en from "element-plus/es/locale/lang/en";
import { store } from "@/store";

// setup
export const useAppStore = defineStore("app", () => {
  // state
  const device = useStorage("device", "desktop");
  const size = useStorage<any>("size", defaultSettings.size);
  const language = useStorage("language", defaultSettings.language);

  const sidebarStatus = useStorage("sidebarStatus", "closed");

  const sidebar = reactive({
    opened: sidebarStatus.value !== "closed",
    withoutAnimation: false,
  });
  const activeTopMenuPath = useStorage("activeTopMenuPath", "");
  /**
   * 根据语言标识读取对应的语言包
   */
  const locale = computed(() => {
    if (language?.value == "en") {
      return en;
    } else {
      return zhCn;
    }
  });

  // actions
  function toggleSidebar() {
    sidebar.opened = !sidebar.opened;
    if (sidebar.opened) {
      sidebarStatus.value = "opened";
    } else {
      sidebarStatus.value = "closed";
    }
  }

  function closeSideBar() {
    sidebar.opened = false;
    sidebarStatus.value = "closed";
  }

  function openSideBar() {
    sidebar.opened = true;
    sidebarStatus.value = "opened";
  }

  function toggleDevice(val: string) {
    device.value = val;
  }

  function changeSize(val: string) {
    size.value = val;
  }
  /**
   * 切换语言
   *
   * @param val
   */
  function changeLanguage(val: string) {
    language.value = val;
  }
  /**
   * 混合模式顶部切换
   */
  function activeTopMenu(val: string) {
    activeTopMenuPath.value = val;
  }
  return {
    device,
    sidebar,
    language,
    locale,
    size,
    activeTopMenu,
    toggleDevice,
    changeSize,
    changeLanguage,
    toggleSidebar,
    closeSideBar,
    openSideBar,
    activeTopMenuPath,
  };
});

// 手动提供给 useStore() 函数 pinia 实例
// https://pinia.vuejs.org/zh/core-concepts/outside-component-usage.html#using-a-store-outside-of-a-component
export function useAppStoreHook() {
  return useAppStore(store);
}
