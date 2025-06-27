<!-- App.vue 或 Layout.vue -->
<!-- App.vue -->
<template>
  <router-view />
</template>


<!--<template>-->
<!--  <el-container style="height: 100vh">-->
<!--    &lt;!&ndash; 左侧菜单栏 &ndash;&gt;-->
<!--    <el-aside width="200px">-->
<!--      <SidebarMenu />-->
<!--    </el-aside>-->

<!--    &lt;!&ndash; 右侧内容区域 &ndash;&gt;-->
<!--    <el-container>-->
<!--      &lt;!&ndash; 顶部导航栏 &ndash;&gt;-->
<!--      <el-header class="flex items-center justify-between px-4 shadow">-->
<!--        <div class="text-lg font-bold">管理后台</div>-->
<!--        <el-dropdown>-->
<!--          <span class="el-dropdown-link cursor-pointer flex items-center space-x-2">-->
<!--            <el-avatar :size="32" :src="user.avatar" />-->
<!--            <span>{{ user.name }}</span>-->
<!--            <el-icon><ArrowDown /></el-icon>-->
<!--          </span>-->
<!--          <template #dropdown>-->
<!--            <el-dropdown-menu>-->
<!--              <el-dropdown-item @click="goProfile">个人信息</el-dropdown-item>-->
<!--              <el-dropdown-item divided @click="logout">退出登录</el-dropdown-item>-->
<!--            </el-dropdown-menu>-->
<!--          </template>-->
<!--        </el-dropdown>-->
<!--      </el-header>-->

<!--      &lt;!&ndash; 主内容区 &ndash;&gt;-->
<!--      <el-main>-->
<!--        <router-view />-->
<!--      </el-main>-->
<!--    </el-container>-->
<!--  </el-container>-->
<!--</template>-->

<!--<script setup>-->
<!--import SidebarMenu from '@/components/SidebarMenu.vue'-->
<!--import { useRouter } from 'vue-router'-->
<!--import { ElMessage } from 'element-plus'-->
<!--import { ArrowDown } from '@element-plus/icons-vue'-->

<!--const router = useRouter()-->

<!--const user = {-->
<!--  name: '管理员张三',-->
<!--  avatar: 'https://api.dicebear.com/7.x/initials/svg?seed=张三'-->
<!--}-->

<!--function goProfile() {-->
<!--  router.push('/profile')-->
<!--}-->

<!--function logout() {-->
<!--  localStorage.removeItem('token')-->
<!--  localStorage.removeItem('tenant')-->
<!--  ElMessage.success('已退出')-->
<!--  router.replace('/login')-->
<!--}-->

<!--</script>-->

<!--<style scoped>-->
<!--.el-header {-->
<!--  height: 60px;-->
<!--  background-color: #fff;-->
<!--  display: flex;-->
<!--  align-items: center;-->
<!--  justify-content: space-between;-->
<!--}-->
<!--</style>-->

