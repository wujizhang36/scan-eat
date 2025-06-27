<!--ManinLayout.vue-->
<template>
  <el-container style="height: 100vh">
    <!-- 左侧菜单栏 -->
    <el-aside width="200px" class="aside">
      <SidebarMenu />
    </el-aside>

    <!-- 右侧内容区域 -->
    <el-container>
      <!-- 顶部导航栏 -->
      <el-header class="header">
        <div class="text-lg font-bold">管理后台</div>
        <el-dropdown>
          <span class="el-dropdown-link cursor-pointer flex items-center space-x-2">
            <el-avatar :size="32" :src="user.avatar" />
            <span>{{ user.name }}</span>
            <el-icon><ArrowDown /></el-icon>
          </span>
          <template #dropdown>
            <el-dropdown-menu>
              <el-dropdown-item @click="goProfile">个人信息</el-dropdown-item>
              <el-dropdown-item divided @click="logout">退出登录</el-dropdown-item>
            </el-dropdown-menu>
          </template>
        </el-dropdown>
      </el-header>

      <!-- 主内容区 -->
      <el-main class="main">
        <router-view />
      </el-main>
    </el-container>
  </el-container>
</template>

<script setup>
import SidebarMenu from '@/components/SidebarMenu.vue'
import { useRouter } from 'vue-router'
import { ElMessage } from 'element-plus'
import { ArrowDown } from '@element-plus/icons-vue'

const router = useRouter()

const user = {
  name: '管理员张三',
  avatar: 'https://api.dicebear.com/7.x/initials/svg?seed=张三'
}

function goProfile() {
  router.push('/profile')
}

function logout() {
  localStorage.removeItem('token')
  localStorage.removeItem('tenant')
  ElMessage.success('已退出')
  router.replace('/login')
}
</script>

<style scoped>
.aside {
  border-right: 1px solid #dcdfe6; /* Element Plus 默认分割线色 */
  background-color: #fff;
}

.header {
  height: 60px;
  background-color: #fff;
  display: flex;
  align-items: center;
  justify-content: space-between;
  border-bottom: 1px solid #ebeef5;
}

.main {
  background-color: #f5f7fa;
  padding: 16px;
}
</style>
