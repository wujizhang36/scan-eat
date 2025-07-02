<!--App.vue-->
<template>
  <RouterView v-slot="{ Component }">
    <!-- 通过 route.meta.layout 选择布局 -->
    <component :is="layout">
      <component :is="Component" />
    </component>
  </RouterView>
</template>

<script setup>
import DefaultLayout from '@/layouts/DefaultLayout.vue'
import NoHeaderLayout from '@/layouts/NoHeaderLayout.vue'
import { useRoute } from 'vue-router'
import { computed } from 'vue'

const route = useRoute()

/**
 * 根据路由 meta 配置动态选择布局
 * 例如在 routes.js 中给某个路由加 meta: { layout: 'no-header' }
 */
const layout = computed(() => {
  switch (route.meta.layout) {
    case 'no-header':
      return NoHeaderLayout
    default:
      return DefaultLayout
  }
})
</script>

<style>
/* 这里也可以写全局 CSS reset 或者加载 Tailwind */
body {
  margin: 0;
  font-family: system-ui, -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, "Helvetica Neue", Arial, sans-serif;
  background-color: #f9f9f9;
  color: #333;
}
</style>
