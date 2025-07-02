<template>
  <div class="min-h-screen flex flex-col">
    <!-- 头部 -->
    <Header />

    <!-- 主体 -->
    <main class="flex-1">
      <slot />
    </main>

    <!-- 底部 -->
    <Footer />
  </div>
</template>

<script setup>
import Header from '@/components/layout/Header.vue'
import Footer from '@/components/layout/Footer.vue'
</script>

<style scoped>
/* 可以按需补充 */
</style>
