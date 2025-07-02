<template>
  <footer class="bg-gray-800 text-white py-6 mt-8">
    <div class="container mx-auto text-center text-sm">
      <p>&copy; 2025 {{ companyName }} 版权所有</p>
      <p class="mt-2">
        <router-link to="/privacy" class="underline hover:text-gray-300">
          隐私政策
        </router-link>
        |
        <router-link to="/terms" class="underline hover:text-gray-300">
          服务条款
        </router-link>
      </p>
    </div>
  </footer>
</template>

<script setup>
const companyName = import.meta.env.VITE_COMPANY_NAME;

console.log(companyName)
// 无需逻辑
</script>

<style scoped>
/* 如果需要可在此补充更多自定义样式 */
</style>
