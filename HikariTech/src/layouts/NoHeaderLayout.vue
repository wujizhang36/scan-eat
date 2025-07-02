<template>
  <div class="min-h-screen flex flex-col">
    <!-- 不包含头部 -->
    <main class="flex-1">
      <slot />
    </main>

    <!-- 底部 -->
    <Footer />
  </div>
</template>

<script setup>
import Footer from '@/components/layout/Footer.vue'
</script>

<style scoped>
/* 这里也可根据需要扩展 */
</style>
