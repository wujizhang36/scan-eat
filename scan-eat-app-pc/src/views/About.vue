<template>
  <PageLayout title="关于我们">
    <el-card>
      <h2 class="text-xl font-semibold mb-4">扫码点餐系统（SaaS 多商户平台）</h2>

      <p class="mb-3">
        本系统是一套基于 SaaS 架构的多商户扫码点餐平台，适用于各类餐饮商户。
        支持扫码点餐、预约、外卖、门店管理等多项功能，帮助商家实现智能化运营管理。
      </p>

      <el-divider />

      <h3 class="text-lg font-semibold mb-2">🌟 核心功能</h3>
      <ul class="list-disc pl-6 mb-4">
        <li>扫码点餐：顾客扫码后自助点单、下单、支付</li>
        <li>预约管理：支持顾客提前预订餐桌和时间</li>
        <li>外卖管理：支持平台或门店自行配送</li>
        <li>购物车监控：运营分析未下单客户行为</li>
        <li>员工管理：支持多门店员工权限划分</li>
        <li>订单与评价：全流程订单处理和用户评价收集</li>
      </ul>

      <el-divider />

      <h3 class="text-lg font-semibold mb-2">🛠 技术栈</h3>
      <ul class="list-disc pl-6 mb-4">
        <li>Vue 3 + Composition API</li>
        <li>Element Plus UI 组件库</li>
        <li>Vite 构建工具</li>
        <li>Vue Router 动态路由控制</li>
      </ul>

      <el-divider />

      <h3 class="text-lg font-semibold mb-2">📞 联系我们</h3>
      <ul class="list-disc pl-6 mb-4">
        <li>官方邮箱：support@scan-eat.com</li>
        <li>联系电话：+81-03-xxxx-xxxx</li>
        <li>公司地址：日本东京涩谷区〇〇〇〇〇〇</li>
      </ul>

      <p class="text-sm text-gray-500 mt-4">版权所有 © 2025 扫码点餐系统团队</p>
    </el-card>
  </PageLayout>
</template>

<script setup>
import PageLayout from '@/components/layout/PageLayout.vue'
</script>

<style scoped>
h2, h3 {
  color: #333;
}
</style>
