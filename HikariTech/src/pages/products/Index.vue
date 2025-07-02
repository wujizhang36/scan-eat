<template>
  <div class="flex flex-col min-h-screen">

    <!-- hero -->
    <section class="bg-gradient-to-r from-blue-600 to-indigo-700 text-white py-20 px-4 md:px-8 text-center">
      <div class="max-w-3xl mx-auto">
        <h1 class="text-4xl md:text-5xl font-bold mb-4">产品与服务</h1>
        <p class="text-lg md:text-xl">为企业提供一站式数字化解决方案</p>
      </div>
    </section>

    <!-- products -->
    <section class="py-16 px-4 md:px-8 bg-gray-50">
      <div class="max-w-7xl mx-auto">
        <h2 class="text-3xl md:text-4xl font-bold text-center mb-12">我们的产品</h2>
        <div class="grid gap-8 sm:grid-cols-1 md:grid-cols-2 lg:grid-cols-3">
          <div class="bg-white rounded-xl shadow hover:shadow-lg transition">
            <img
                src="https://images.pexels.com/photos/3861969/pexels-photo-3861969.jpeg?auto=compress&cs=tinysrgb&w=800"
                alt="产品A"
                class="w-full h-48 object-cover rounded-t-xl"
            />
            <div class="p-6">
              <h3 class="text-xl font-bold mb-2">企业管理系统</h3>
              <p class="text-gray-600">帮助企业高效管理流程、库存、财务和员工。</p>
            </div>
          </div>
          <div class="bg-white rounded-xl shadow hover:shadow-lg transition">
            <img
                src="https://images.pexels.com/photos/1181671/pexels-photo-1181671.jpeg?auto=compress&cs=tinysrgb&w=800"
                alt="产品B"
                class="w-full h-48 object-cover rounded-t-xl"
            />
            <div class="p-6">
              <h3 class="text-xl font-bold mb-2">云数据平台</h3>
              <p class="text-gray-600">安全、稳定、可扩展的数据存储和分析服务。</p>
            </div>
          </div>
          <div class="bg-white rounded-xl shadow hover:shadow-lg transition">
            <img
                src="https://images.pexels.com/photos/3861972/pexels-photo-3861972.jpeg?auto=compress&cs=tinysrgb&w=800"
                alt="产品C"
                class="w-full h-48 object-cover rounded-t-xl"
            />
            <div class="p-6">
              <h3 class="text-xl font-bold mb-2">信息安全解决方案</h3>
              <p class="text-gray-600">全方位的信息安全策略及实施方案。</p>
            </div>
          </div>
        </div>
      </div>
    </section>

    <!-- services -->
    <section class="py-16 px-4 md:px-8">
      <div class="max-w-7xl mx-auto">
        <h2 class="text-3xl md:text-4xl font-bold text-center mb-12">我们的服务</h2>
        <div class="grid gap-8 sm:grid-cols-1 md:grid-cols-2 lg:grid-cols-3">
          <div class="bg-white p-6 rounded-xl shadow hover:shadow-lg transition">
            <h3 class="text-xl font-semibold mb-3">软件定制开发</h3>
            <p class="text-gray-600">为各行业量身打造业务所需的数字化系统。</p>
          </div>
          <div class="bg-white p-6 rounded-xl shadow hover:shadow-lg transition">
            <h3 class="text-xl font-semibold mb-3">IT 咨询与规划</h3>
            <p class="text-gray-600">为企业提供全生命周期 IT 战略咨询服务。</p>
          </div>
          <div class="bg-white p-6 rounded-xl shadow hover:shadow-lg transition">
            <h3 class="text-xl font-semibold mb-3">系统集成服务</h3>
            <p class="text-gray-600">整合软硬件资源，打造高效稳定的信息系统。</p>
          </div>
        </div>
      </div>
    </section>

    <!-- contact -->
    <section class="bg-blue-700 text-white py-16 px-4 text-center">
      <h2 class="text-3xl font-bold mb-4">需要了解更多？</h2>
      <p class="mb-6">欢迎随时联系我们，获取专属 IT 咨询和报价</p>
      <button
          class="bg-white text-blue-700 px-6 py-3 rounded-full shadow hover:bg-gray-100 transition"
      >
        联系我们
      </button>
    </section>

  </div>
</template>

<script setup>
// 如果需要可在这里引入Header/Footer
</script>
