<template>
  <div class="flex flex-col min-h-screen">

    <!-- hero -->
    <section class="bg-gradient-to-r from-blue-600 to-indigo-700 text-white py-20 px-4 md:px-8 text-center">
      <div class="max-w-3xl mx-auto">
        <h1 class="text-4xl md:text-5xl font-bold mb-4">联系我们</h1>
        <p class="text-lg md:text-xl">期待与您携手，共创数字化未来</p>
      </div>
    </section>

    <!-- contact info + form -->
    <section class="py-16 px-4 md:px-8 bg-gray-50">
      <div class="max-w-7xl mx-auto grid gap-8 md:grid-cols-2">

        <!-- info -->
        <div>
          <h2 class="text-3xl font-bold mb-6">联系方式</h2>
          <ul class="space-y-4 text-gray-700">
            <li><strong>电话：</strong> +81 3-1234-5678</li>
            <li><strong>邮箱：</strong> info@yourcompany.jp</li>
            <li><strong>地址：</strong> 日本东京都新宿区西新宿2-8-1</li>
            <li><strong>工作时间：</strong> 周一至周五 9:00 - 18:00</li>
          </ul>
          <div class="mt-8">
            <img
                src="https://images.pexels.com/photos/3184292/pexels-photo-3184292.jpeg?auto=compress&cs=tinysrgb&w=800"
                alt="office"
                class="rounded-xl shadow w-full object-cover h-48"
            />
          </div>
        </div>

        <!-- form -->
        <div>
          <h2 class="text-3xl font-bold mb-6">留言给我们</h2>
          <form class="space-y-4">
            <input
                type="text"
                placeholder="您的姓名"
                class="w-full border rounded-lg p-3 focus:outline-none focus:ring-2 focus:ring-blue-500"
            />
            <input
                type="email"
                placeholder="您的邮箱"
                class="w-full border rounded-lg p-3 focus:outline-none focus:ring-2 focus:ring-blue-500"
            />
            <textarea
                placeholder="您的留言"
                class="w-full border rounded-lg p-3 h-32 focus:outline-none focus:ring-2 focus:ring-blue-500"
            ></textarea>
            <button
                type="submit"
                class="bg-blue-700 text-white px-6 py-3 rounded-full shadow hover:bg-blue-800 transition"
            >
              提交留言
            </button>
          </form>
        </div>

      </div>
    </section>

    <!-- map -->
    <section class="py-16 px-4 md:px-8">
      <div class="max-w-5xl mx-auto">
        <h2 class="text-3xl font-bold text-center mb-6">公司位置</h2>
        <div class="rounded-xl overflow-hidden shadow">
          <iframe
              src="https://maps.google.com/maps?q=35.6895,139.6917&z=14&ie=UTF8&iwloc=&output=embed"
              width="100%"
              height="400"
              frameborder="0"
              style="border:0;"
              allowfullscreen
          ></iframe>
        </div>
      </div>
    </section>


    <!-- final CTA -->
    <section class="bg-blue-700 text-white py-16 px-4 text-center">
      <h2 class="text-3xl font-bold mb-4">让我们开始合作吧</h2>
      <p class="mb-6">无论您的需求有多复杂，我们都愿意倾听与帮助</p>
      <router-link
          to="/custom-solution"
          class="bg-white text-blue-700 px-6 py-3 rounded-full shadow hover:bg-gray-100 transition"
      >
        获取定制方案
      </router-link>
    </section>

  </div>
</template>

<script setup>
// 可按需引入 Header / Footer
</script>
