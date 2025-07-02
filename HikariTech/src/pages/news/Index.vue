<template>
  <div class="flex flex-col min-h-screen">

    <!-- hero -->
    <section class="bg-gradient-to-r from-blue-600 to-indigo-700 text-white py-20 px-4 md:px-8 text-center">
      <div class="max-w-3xl mx-auto">
        <h1 class="text-4xl md:text-5xl font-bold mb-4">新闻资讯</h1>
        <p class="text-lg md:text-xl">了解行业动态与公司新闻</p>
      </div>
    </section>

    <!-- news list -->
    <section class="py-16 px-4 md:px-8 bg-gray-50">
      <div class="max-w-7xl mx-auto">
        <div class="grid gap-8 sm:grid-cols-1 md:grid-cols-2 lg:grid-cols-3">
          <div
              v-for="(news, index) in newsList"
              :key="index"
              class="bg-white rounded-xl shadow hover:shadow-lg transition overflow-hidden"
          >
            <img
                :src="news.image"
                :alt="news.title"
                class="w-full h-48 object-cover"
            />
            <div class="p-6">
              <h3 class="text-xl font-bold mb-2">{{ news.title }}</h3>
              <p class="text-gray-600 mb-4">{{ news.summary }}</p>
              <div class="text-sm text-gray-400">{{ news.date }}</div>
            </div>
          </div>
        </div>

        <div class="flex justify-center mt-10">
          <button
              class="bg-blue-700 text-white px-6 py-3 rounded-full shadow hover:bg-blue-800 transition"
          >
            查看更多
          </button>
        </div>
      </div>
    </section>

    <!-- contact -->
    <section class="bg-blue-700 text-white py-16 px-4 text-center">
      <h2 class="text-3xl font-bold mb-4">需要更多资讯？</h2>
      <p class="mb-6">欢迎联系我们获取最新行业信息</p>
      <button
          class="bg-white text-blue-700 px-6 py-3 rounded-full shadow hover:bg-gray-100 transition"
      >
        联系我们
      </button>
    </section>

  </div>
</template>

<script setup>
import { ref } from "vue";

const newsList = ref([
  {
    title: "企业数字化趋势 2025",
    summary: "了解数字化转型对企业未来发展的深远影响。",
    date: "2025-06-30",
    image: "https://images.pexels.com/photos/3861969/pexels-photo-3861969.jpeg?auto=compress&cs=tinysrgb&w=800"
  },
  {
    title: "云计算在中小企业的应用",
    summary: "如何利用云服务降低成本、提升敏捷度。",
    date: "2025-06-15",
    image: "https://images.pexels.com/photos/1181671/pexels-photo-1181671.jpeg?auto=compress&cs=tinysrgb&w=800"
  },
  {
    title: "企业信息安全新思路",
    summary: "数据安全成为企业战略重点，如何防护？",
    date: "2025-05-28",
    image: "https://images.pexels.com/photos/3861972/pexels-photo-3861972.jpeg?auto=compress&cs=tinysrgb&w=800"
  }
]);
</script>
