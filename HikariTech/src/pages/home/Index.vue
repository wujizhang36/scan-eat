<template>
  <div class="flex flex-col min-h-screen">

    <!-- hero -->
    <section class="relative bg-gradient-to-r from-blue-600 to-indigo-700 text-white py-28 px-4 md:px-8 text-center overflow-hidden">
      <img
          src="https://images.unsplash.com/photo-1519389950473-47ba0277781c?auto=format&fit=crop&w=1600&q=80"
          alt="digital transformation"
          class="absolute inset-0 w-full h-full object-cover opacity-20"
      />
      <div class="relative max-w-2xl mx-auto">
        <h1 class="text-4xl md:text-6xl font-extrabold mb-6 drop-shadow">赋能企业数字化转型</h1>
        <p class="text-lg md:text-xl mb-8">专业的 IT 解决方案与创新技术助您领跑未来</p>
        <button class="bg-white text-blue-700 px-8 py-3 rounded-full shadow hover:bg-gray-100 transition font-semibold">
          立即联系我们
        </button>
      </div>
    </section>

    <!-- services -->
    <section class="py-20 px-4 md:px-8 bg-gray-50">
      <div class="max-w-7xl mx-auto">
        <h2 class="text-3xl md:text-4xl font-bold text-center mb-14">我们的核心服务</h2>
        <div class="grid gap-8 sm:grid-cols-1 md:grid-cols-2 lg:grid-cols-3">
          <div class="bg-white p-8 rounded-2xl shadow hover:shadow-2xl transition transform hover:-translate-y-1">
            <h3 class="text-xl font-bold mb-3">企业级软件开发</h3>
            <p class="text-gray-600">定制化开发，满足企业多样化业务需求。</p>
          </div>
          <div class="bg-white p-8 rounded-2xl shadow hover:shadow-2xl transition transform hover:-translate-y-1">
            <h3 class="text-xl font-bold mb-3">系统集成</h3>
            <p class="text-gray-600">整合软硬件资源，打造高效信息系统。</p>
          </div>
          <div class="bg-white p-8 rounded-2xl shadow hover:shadow-2xl transition transform hover:-translate-y-1">
            <h3 class="text-xl font-bold mb-3">IT 咨询服务</h3>
            <p class="text-gray-600">专业顾问助力企业规划未来 IT 战略。</p>
          </div>
        </div>
      </div>
    </section>

    <!-- projects -->
    <section class="py-20 px-4 md:px-8 bg-white">
      <div class="max-w-7xl mx-auto">
        <h2 class="text-3xl md:text-4xl font-bold text-center mb-14">部分项目案例</h2>
        <div class="grid gap-8 sm:grid-cols-1 md:grid-cols-2 lg:grid-cols-3">
          <div class="rounded-2xl overflow-hidden shadow hover:shadow-2xl transition transform hover:-translate-y-1">
            <img
                src="https://images.pexels.com/photos/4483610/pexels-photo-4483610.jpeg?auto=compress&cs=tinysrgb&w=800"
                alt="project"
                class="w-full h-52 object-cover"
            />
            <div class="p-6">
              <h3 class="text-xl font-bold mb-2">智能制造管理系统</h3>
              <p class="text-gray-600">为制造业客户打造数字化生产流程。</p>
            </div>
          </div>
          <div class="rounded-2xl overflow-hidden shadow hover:shadow-2xl transition transform hover:-translate-y-1">
            <img
                src="https://images.unsplash.com/photo-1518770660439-4636190af475?auto=format&fit=crop&w=800&q=80"
                alt="project"
                class="w-full h-52 object-cover"
            />
            <div class="p-6">
              <h3 class="text-xl font-bold mb-2">云平台解决方案</h3>
              <p class="text-gray-600">高效安全的云基础架构设计与部署。</p>
            </div>
          </div>
          <div class="rounded-2xl overflow-hidden shadow hover:shadow-2xl transition transform hover:-translate-y-1">
            <img
                src="https://images.pexels.com/photos/5380663/pexels-photo-5380663.jpeg?auto=compress&cs=tinysrgb&w=800"
                alt="project"
                class="w-full h-52 object-cover"
            />
            <div class="p-6">
              <h3 class="text-xl font-bold mb-2">信息安全体系建设</h3>
              <p class="text-gray-600">全方位信息安全风险管理。</p>
            </div>
          </div>
        </div>
      </div>
    </section>

    <!-- contact -->
    <section class="bg-gradient-to-r from-blue-700 to-indigo-700 text-white py-20 px-4 text-center">
      <h2 class="text-3xl md:text-4xl font-bold mb-4">想与我们合作？</h2>
      <p class="mb-8">欢迎随时联系我们，定制您的专属 IT 解决方案</p>
      <button
          class="bg-white text-blue-700 px-8 py-3 rounded-full shadow hover:bg-gray-100 transition font-semibold"
      >
        获取报价
      </button>
    </section>

  </div>
</template>

<script setup>
// 确认路径
import Header from "@/components/layout/Header.vue";
import Footer from "@/components/layout/Footer.vue";
</script>
