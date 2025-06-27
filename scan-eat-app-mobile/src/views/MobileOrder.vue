<template>
  <div class="order-page">
    <!-- 顶部图片轮播 -->
    <van-swipe class="image-carousel" autoplay="3000" indicator-color="white">
      <van-swipe-item v-for="(img, index) in carouselImages" :key="index">
        <img :src="img" class="carousel-img" />
      </van-swipe-item>
    </van-swipe>

    <!-- 主区域 -->
    <div class="main-content">
      <!-- 左侧分类 -->
      <div class="category-menu">
        <div
            v-for="(cat, index) in categories"
            :key="cat.id"
            class="category-item"
            :class="{ active: activeCategory === index }"
            @click="activeCategory = index"
        >
          <div class="category-icon">🍽️</div>
          <div class="category-text">{{ cat.name }}</div>
        </div>
      </div>

      <!-- 右侧菜品 -->
      <div class="dish-list">
        <van-card
            v-for="dish in dishesByCategory(categories[activeCategory]?.id)"
            :key="dish.id"
            :title="dish.name"
            :desc="dish.description"
            :thumb="dish.image"
            class="dish-card"
        >
          <template #tags>
            <van-tag plain type="danger" v-for="opt in dish.options.slice(0, 2)" :key="opt">{{ opt }}</van-tag>
          </template>
          <template #footer>
            <div class="flex items-center justify-between w-full">
              <div class="text-red-600 font-bold text-base">¥{{ dish.price }}</div>
              <van-button
                  size="small"
                  color="#ee0a24"
                  round
                  @click="selectDish(dish)"
              >
                加入
              </van-button>
            </div>
          </template>
        </van-card>
      </div>
    </div>

    <!-- 购物车 -->
    <van-submit-bar
        :price="totalPrice * 100"
        button-text="去结算"
        @submit="goCheckout"
        v-if="totalItems > 0"
    >
      已选 {{ totalItems }} 件
    </van-submit-bar>

    <!-- 口味弹窗 -->
    <van-popup v-model:show="showFlavorPopup" position="bottom" round>
      <div class="popup-container">
        <h3 class="popup-title">请选择口味</h3>
        <van-checkbox-group v-model="selectedFlavor">
          <van-checkbox
              v-for="(opt, idx) in selectedDish?.options || []"
              :key="idx"
              :name="opt"
          >
            {{ opt }}
          </van-checkbox>
        </van-checkbox-group>
        <div class="popup-actions">
          <van-button block plain type="primary" @click="confirmFlavor">确认加入</van-button>
        </div>
      </div>
    </van-popup>
  </div>
</template>

<script setup>
import { ref, computed } from 'vue'
import { showToast } from 'vant'

// 分类
const categories = ref([
  { id: 1, name: '招牌菜' },
  { id: 2, name: '热菜' },
  { id: 3, name: '凉菜' },
])

// 菜品
const dishes = ref([
  {
    id: 101,
    categoryId: 1,
    name: '红烧排骨',
    price: 28,
    description: '酱香浓郁，肉质软烂',
    image: '/vueapp/scan-eat-app-mobile/img/94_360_360.png',
    options: ['正常', '加辣', '不放香菜']
  },
  {
    id: 102,
    categoryId: 2,
    name: '宫保鸡丁',
    price: 22,
    description: '经典川味，微辣',
    image: '/vueapp/scan-eat-app-mobile/img/342f6da0ee08416ab35a61f44733834a.png',
    options: ['微辣', '不辣', '加花生']
  },
  {
    id: 103,
    categoryId: 3,
    name: '拍黄瓜',
    price: 10,
    description: '清爽开胃，酸甜可口',
    image: '/vueapp/scan-eat-app-mobile/img/v2-42271465a09a729e514f7b8d960c2827_1440w.png',
    options: ['蒜泥', '少蒜', '不加醋']
  }
])

// 顶部轮播
const carouselImages = ref([
  '/vueapp/scan-eat-app-mobile/img/center.png',
  '/vueapp/scan-eat-app-mobile/img/center.png',
  '/vueapp/scan-eat-app-mobile/img/center.png'
])

const activeCategory = ref(0)
const cart = ref({})
const showFlavorPopup = ref(false)
const selectedDish = ref(null)
const selectedFlavor = ref([])

function dishesByCategory(catId) {
  return dishes.value.filter(d => d.categoryId === catId)
}

function selectDish(dish) {
  selectedDish.value = dish
  selectedFlavor.value = []
  showFlavorPopup.value = true
}

function confirmFlavor() {
  if (!selectedDish.value) return
  const key = `${selectedDish.value.id}_${selectedFlavor.value.join(',') || '默认'}`
  if (cart.value[key]) {
    cart.value[key].count += 1
  } else {
    cart.value[key] = {
      dish: selectedDish.value,
      count: 1,
      flavor: [...selectedFlavor.value]
    }
  }
  showFlavorPopup.value = false
  showToast('已加入购物车')
}

const totalItems = computed(() =>
    Object.values(cart.value).reduce((sum, item) => sum + item.count, 0)
)
const totalPrice = computed(() =>
    Object.values(cart.value).reduce((sum, item) => sum + item.count * item.dish.price, 0)
)

function goCheckout() {
  showToast('结算功能开发中...')
}
</script>

<style scoped>
.order-page {
  background: #f7f8fa;
  min-height: 100vh;
  display: flex;
  flex-direction: column;
  color: #333;
  padding-bottom: 60px;
}

/* 顶部轮播 */
.image-carousel {
  width: 100%;
  height: 200px;
}
.carousel-img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

/* 主体 */
.main-content {
  flex: 1;
  display: flex;
  overflow: hidden;
}

/* 分类菜单 */
.category-menu {
  width: 90px;
  background-color: #fff;
  border-right: 1px solid #eee;
  overflow-y: auto;
}
.category-item {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 10px 0;
  cursor: pointer;
  border-bottom: 1px solid #f1f1f1;
  transition: background-color 0.3s;
}
.category-item.active {
  background: #ffeaea;
  color: #ee0a24;
}
.category-icon {
  font-size: 20px;
}
.category-text {
  font-size: 13px;
  margin-top: 4px;
}

/* 菜品列表 */
.dish-list {
  flex: 1;
  overflow-y: auto;
  padding: 8px;
}
.dish-card {
  margin-bottom: 10px;
  border-radius: 8px;
}

/* 口味弹窗 */
.popup-container {
  padding: 16px;
}
.popup-title {
  font-size: 18px;
  font-weight: bold;
  margin-bottom: 12px;
}
.popup-actions {
  margin-top: 16px;
}

/* van-submit-bar 会自动浮动，故无需额外固定 */
</style>
