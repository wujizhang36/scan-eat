<template>
  <div class="sidebar-wrapper">
    <!-- 租户 Logo 区域 -->
    <div class="logo-area">
      <img :src="tenantLogo" alt="租户Logo" class="logo-img" />
      <span class="logo-text">{{ tenantName }}</span>
    </div>

    <!-- 菜单区域 -->
    <el-menu
        :default-active="$route.path"
        class="menu"
        background-color="#ffffff"
        router
    >
      <template v-for="item in menuRoutes" :key="item.path">
        <el-menu-item :index="item.path">
          <el-icon v-if="item.meta?.icon">
            <component :is="item.meta.icon" />
          </el-icon>
          <span>{{ item.meta.title }}</span>
        </el-menu-item>
      </template>
    </el-menu>
  </div>
</template>

<script setup>
import { useRouter } from 'vue-router'

const menuRoutes = useRouter().options.routes
    .find(r => r.path === '/')?.children
    .filter(r => r.meta && r.meta.title && !r.meta.hidden)

// 模拟租户数据
const tenantLogo = localStorage.getItem('tenantLogo') || 'https://api.dicebear.com/7.x/icons/svg?seed=T'
const tenantName = localStorage.getItem('tenant') || '租户名称'
</script>

<style scoped>
.sidebar-wrapper {
  display: flex;
  flex-direction: column;
  height: 100%;
  background-color: #ffffff;
  border-right: 1px solid #ebeef5;
}

.logo-area {
  display: flex;
  align-items: center;
  height: 60px;
  padding: 0 16px;
  border-bottom: 1px solid #ebeef5;
}

.logo-img {
  width: 32px;
  height: 32px;
  border-radius: 6px;
  margin-right: 8px;
}

.logo-text {
  font-size: 16px;
  font-weight: bold;
  color: #333;
}

.menu {
  flex: 1;
  border-right: none;
}
</style>
