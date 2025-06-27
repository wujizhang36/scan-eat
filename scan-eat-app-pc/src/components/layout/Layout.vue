<!-- Layout.vue 中 Header 区域 -->
<template>
  <div class="flex justify-between items-center px-4 py-2 bg-white shadow">
    <!-- 左侧 logo -->
    <div class="text-xl font-bold">扫码点餐系统</div>

    <!-- 右侧用户菜单 -->
    <el-dropdown>
      <span class="el-dropdown-link cursor-pointer flex items-center space-x-2">
        <el-avatar :size="32" :src="user.avatar" />
        <span>{{ user.name }}</span>
        <el-icon><el-icon-arrow-down /></el-icon>
      </span>

      <template #dropdown>
        <el-dropdown-menu>
          <el-dropdown-item @click="goProfile">个人信息</el-dropdown-item>
          <!-- 也可以在这里放“修改密码” -->
          <el-dropdown-item divided @click="logout">退出登录</el-dropdown-item>
        </el-dropdown-menu>
      </template>
    </el-dropdown>
  </div>
</template>

<script setup>
import { useRouter } from 'vue-router'
import { ElMessage } from 'element-plus'
import { ElIconArrowDown } from '@element-plus/icons-vue'

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
/* 可以根据需要进行样式调整 */
.flex {
  display: flex;
  align-items: center;      /* 垂直居中 */
}
</style>
