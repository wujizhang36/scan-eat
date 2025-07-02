<template>
  <div class="flex flex-col min-h-screen">

    <!-- hero -->
    <section class="bg-gradient-to-r from-blue-600 to-indigo-700 text-white py-20 px-4 md:px-8 text-center">
      <div class="max-w-3xl mx-auto">
        <h1 class="text-4xl md:text-5xl font-bold mb-4">关于我们</h1>
        <p class="text-lg md:text-xl">用创新科技驱动企业数字化变革</p>
      </div>
    </section>

    <!-- company mission -->
    <section class="py-16 px-4 md:px-8 bg-gray-50">
      <div class="max-w-5xl mx-auto text-center">
        <h2 class="text-3xl font-bold mb-6">我们的使命</h2>
        <p class="text-gray-700 text-lg leading-relaxed">
          我们致力于为企业提供专业的 IT 解决方案，通过持续的技术创新和卓越的服务，帮助客户实现高效、安全、可持续的数字化转型。
        </p>
      </div>
    </section>

    <!-- team -->
    <section class="py-16 px-4 md:px-8">
      <div class="max-w-6xl mx-auto">
        <h2 class="text-3xl font-bold text-center mb-10">我们的团队</h2>
        <div class="grid gap-8 sm:grid-cols-1 md:grid-cols-2 lg:grid-cols-3">
          <div class="bg-white rounded-xl shadow p-6 text-center hover:shadow-lg transition">
            <img
                src="https://images.pexels.com/photos/220453/pexels-photo-220453.jpeg?auto=compress&cs=tinysrgb&w=200"
                alt="team member"
                class="w-24 h-24 rounded-full mx-auto mb-4 object-cover"
            />
            <h3 class="text-xl font-semibold">张伟</h3>
            <p class="text-gray-600">首席技术官 CTO</p>
          </div>
          <div class="bg-white rounded-xl shadow p-6 text-center hover:shadow-lg transition">
            <img
                src="https://images.pexels.com/photos/774909/pexels-photo-774909.jpeg?auto=compress&cs=tinysrgb&w=200"
                alt="team member"
                class="w-24 h-24 rounded-full mx-auto mb-4 object-cover"
            />
            <h3 class="text-xl font-semibold">李娜</h3>
            <p class="text-gray-600">项目经理</p>
          </div>
          <div class="bg-white rounded-xl shadow p-6 text-center hover:shadow-lg transition">
            <img
                src="https://images.pexels.com/photos/2379005/pexels-photo-2379005.jpeg?auto=compress&cs=tinysrgb&w=200"
                alt="team member"
                class="w-24 h-24 rounded-full mx-auto mb-4 object-cover"
            />
            <h3 class="text-xl font-semibold">王强</h3>
            <p class="text-gray-600">解决方案架构师</p>
          </div>
        </div>
      </div>
    </section>

    <!-- history -->
    <section class="py-16 px-4 md:px-8 bg-gray-50">
      <div class="max-w-5xl mx-auto">
        <h2 class="text-3xl font-bold text-center mb-10">发展历程</h2>
        <ul class="space-y-6">
          <li>
            <div class="flex items-start space-x-4">
              <span class="text-blue-700 font-bold">2018</span>
              <p>公司成立，聚焦企业数字化咨询服务。</p>
            </div>
          </li>
          <li>
            <div class="flex items-start space-x-4">
              <span class="text-blue-700 font-bold">2019</span>
              <p>自主研发智能化管理系统并投入市场。</p>
            </div>
          </li>
          <li>
            <div class="flex items-start space-x-4">
              <span class="text-blue-700 font-bold">2022</span>
              <p>为50+大中型企业提供端到端的数字化转型方案。</p>
            </div>
          </li>
        </ul>
      </div>
    </section>

    <!-- contact -->
    <section class="bg-blue-700 text-white py-16 px-4 text-center">
      <h2 class="text-3xl font-bold mb-4">想进一步了解我们？</h2>
      <p class="mb-6">欢迎随时联系我们，开启合作新篇章</p>
      <button
          class="bg-white text-blue-700 px-6 py-3 rounded-full shadow hover:bg-gray-100 transition"
      >
        联系我们
      </button>
    </section>

  </div>
</template>

<script setup>
// 可按需添加
</script>
