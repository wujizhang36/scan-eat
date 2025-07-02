<template>
  <header class="bg-white shadow-md fixed top-0 left-0 w-full z-50">
    <div class="container mx-auto flex items-center justify-between py-4 px-6">
      <!-- Logo -->
      <router-link to="/" class="text-2xl font-bold text-blue-600 flex items-center">
        <img src="@/assets/logo.svg" alt="Logo" class="w-10 h-10 mr-2" />
        {{ companyName }}
      </router-link>

      <!-- Desktop Navigation -->
      <nav class="hidden md:flex space-x-8">
        <router-link
            to="/"
            class="text-gray-800 hover:text-blue-600 transition-all duration-300"
            active-class="text-blue-600 font-semibold"
        >
          首页
        </router-link>
        <router-link
            to="/about"
            class="text-gray-800 hover:text-blue-600 transition-all duration-300"
            active-class="text-blue-600 font-semibold"
        >
          关于我们
        </router-link>
        <router-link
            to="/products"
            class="text-gray-800 hover:text-blue-600 transition-all duration-300"
            active-class="text-blue-600 font-semibold"
        >
          产品与服务
        </router-link>
        <router-link
            to="/news"
            class="text-gray-800 hover:text-blue-600 transition-all duration-300"
            active-class="text-blue-600 font-semibold"
        >
          新闻资讯
        </router-link>
        <router-link
            to="/contact"
            class="text-gray-800 hover:text-blue-600 transition-all duration-300"
            active-class="text-blue-600 font-semibold"
        >
          联系我们
        </router-link>
      </nav>

      <!-- Mobile Navigation -->
      <div class="md:hidden flex items-center">
        <button
            @click="toggleMenu"
            class="text-gray-800 hover:text-blue-600 focus:outline-none"
        >
          <i class="fas fa-bars fa-lg"></i>
        </button>
      </div>
    </div>

    <!-- Mobile Menu (Responsive) -->
    <div v-if="isMenuOpen" class="bg-white shadow-md md:hidden">
      <div class="container mx-auto py-4 px-6">
        <router-link
            to="/"
            class="block text-gray-800 hover:text-blue-600 py-2"
            @click="toggleMenu"
        >
          首页
        </router-link>
        <router-link
            to="/about"
            class="block text-gray-800 hover:text-blue-600 py-2"
            @click="toggleMenu"
        >
          关于我们
        </router-link>
        <router-link
            to="/products"
            class="block text-gray-800 hover:text-blue-600 py-2"
            @click="toggleMenu"
        >
          产品与服务
        </router-link>
        <router-link
            to="/news"
            class="block text-gray-800 hover:text-blue-600 py-2"
            @click="toggleMenu"
        >
          新闻资讯
        </router-link>
        <router-link
            to="/contact"
            class="block text-gray-800 hover:text-blue-600 py-2"
            @click="toggleMenu"
        >
          联系我们
        </router-link>
      </div>
    </div>
  </header>
</template>

<script setup>
import { ref } from 'vue'
const companyName = import.meta.env.VITE_COMPANY_NAME;

// 控制移动端菜单的显示/隐藏
const isMenuOpen = ref(false)

// 切换菜单状态
const toggleMenu = () => {
  isMenuOpen.value = !isMenuOpen.value
}
</script>

<style scoped>
/* 样式自定义 */
.container {
  max-width: 1200px;
  margin: 0 auto;
}

nav a {
  text-decoration: none;
  font-size: 1rem;
  font-weight: 500;
}

nav a:hover {
  color: #2d72d9;
}

/* mobile menu */
.md\:hidden {
  display: none;
}

@media (max-width: 768px) {
  .md\:hidden {
    display: block;
  }

  .md\:flex {
    display: none;
  }
}
</style>
