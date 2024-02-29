<script setup lang="ts">
import { ref } from 'vue'
import { RouterView } from 'vue-router'
import { Setting } from '@element-plus/icons-vue'
import router from '@/router'
const activeIndex = ref('1')
const handleSelect = (key: string, keyPath: string[]) => {
  console.log(key, keyPath)
  if (key === '1') {
    router.push('/')
  } else if (key === '2') {
    router.push('/about')
  } else if (key === '3') {
    router.push('/auth/login')
  }
}
</script>

<template>
  <div class="header">
    <el-row style="height: 3rem; background-color: red">
      <el-col :span="24"
        ><div class="grid-content ep-bg-purple-dark" ></div>
        <el-menu
          :default-active="activeIndex"
          class=""
          mode="horizontal"
          :ellipsis="false"
          @select="handleSelect"
        >
          <el-menu-item index="0">
            <img style="width: 50px" src="../assets/logo.svg" alt="M logo" />
          </el-menu-item>
          <div class="flex-grow" ></div>
          <el-menu-item index="1">首页 </el-menu-item>
          <el-menu-item index="2">关于</el-menu-item>
          <el-menu-item index="3">登录</el-menu-item>
          <el-sub-menu index="4">
            <template #title>
              <el-icon><Setting /></el-icon>
              <span>设置</span>
            </template>
            <el-menu-item index="4-1">item one</el-menu-item>
            <el-menu-item index="4-2">item two</el-menu-item>
            <el-menu-item index="4-3">item three</el-menu-item>
          </el-sub-menu>
        </el-menu>
      </el-col>
    </el-row>
  </div>
  <div class="content">
    <RouterView />
  </div>

  <div class="footer"> Footer </div>

</template>

<style scoped>
.logo {
  display: block;
  margin: 0 auto 2rem;
}
.common-layout {
  height: 100vh;
}

.common-layout el-container {
  height: 100vh;
  max-height: 100vh;
}
.shadow-gray-200 {
  --tw-shadow-color: rgb(var(--color-gray-200) / 1);
  --tw-shadow: var(--tw-shadow-colored);
}

.border-transparent {
  border-color: transparent;
}
</style>
