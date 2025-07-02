<template>
  <div class="flex flex-col min-h-screen">

    <!-- hero -->
    <section class="bg-gradient-to-r from-blue-600 to-indigo-700 text-white py-20 px-4 md:px-8 text-center">
      <div class="max-w-3xl mx-auto">
        <h1 class="text-4xl md:text-5xl font-bold mb-4">获取定制方案</h1>
        <p class="text-lg md:text-xl">告诉我们您的需求，我们将为您量身打造数字化解决方案</p>
      </div>
    </section>

    <!-- custom plan form -->
    <section class="py-16 px-4 md:px-8 bg-gray-50">
      <div class="max-w-3xl mx-auto bg-white rounded-xl shadow p-8">
        <h2 class="text-3xl font-bold mb-6 text-center">提交需求</h2>
        <form @submit.prevent="submitForm" class="space-y-4">
          <div>
            <label class="block mb-1 font-semibold">姓名</label>
            <input type="text" v-model="form.name" class="w-full border rounded px-3 py-2" required />
          </div>
          <div>
            <label class="block mb-1 font-semibold">邮箱</label>
            <input type="email" v-model="form.email" class="w-full border rounded px-3 py-2" required />
          </div>
          <div>
            <label class="block mb-1 font-semibold">联系电话</label>
            <input type="tel" v-model="form.phone" class="w-full border rounded px-3 py-2" required />
          </div>
          <div>
            <label class="block mb-1 font-semibold">预算范围</label>
            <select v-model="form.budget" class="w-full border rounded px-3 py-2">
              <option value="">请选择</option>
              <option>10万以内</option>
              <option>10~50万</option>
              <option>50~100万</option>
              <option>100万以上</option>
            </select>
          </div>
          <div>
            <label class="block mb-1 font-semibold">项目需求描述</label>
            <textarea v-model="form.description" class="w-full border rounded px-3 py-2" rows="5" required></textarea>
          </div>
          <button
              type="submit"
              class="bg-blue-700 text-white px-6 py-3 rounded-full hover:bg-blue-800 transition"
          >
            提交需求
          </button>
        </form>
      </div>
    </section>

    <!-- contact -->
    <section class="py-16 px-4 md:px-8">
      <div class="max-w-5xl mx-auto grid gap-8 md:grid-cols-3 text-center">
        <div class="bg-white p-6 rounded-xl shadow">
          <h3 class="text-xl font-bold mb-2">电话</h3>
          <p class="text-gray-600">+81 3-1234-5678</p>
        </div>
        <div class="bg-white p-6 rounded-xl shadow">
          <h3 class="text-xl font-bold mb-2">邮箱</h3>
          <p class="text-gray-600">info@yourcompany.jp</p>
        </div>
        <div class="bg-white p-6 rounded-xl shadow">
          <h3 class="text-xl font-bold mb-2">地址</h3>
          <p class="text-gray-600">日本东京都新宿区西新宿2-8-1</p>
        </div>
      </div>
    </section>

  </div>
</template>

<script setup>
import { reactive } from "vue";

const form = reactive({
  name: "",
  email: "",
  phone: "",
  budget: "",
  description: ""
});

const submitForm = () => {
  alert(`感谢您提交的信息，${form.name}！我们会尽快与您联系。`);
  // 可对接后端 API
  form.name = "";
  form.email = "";
  form.phone = "";
  form.budget = "";
  form.description = "";
};
</script>
