<script setup>
import { ref, computed, onMounted } from 'vue'
import { showToast } from 'vant'
import DishCard from '@/components/DishCard.vue'
import FlavorDialog from '@/components/FlavorDialog.vue'
import { useCartStore } from '@/store/cart'
import request from '@/utils/request'

const cart = useCartStore()

const categories = ref([])
const dishes = ref([])
const categoryIndex = ref(0)
const showDialog = ref(false)
const selectedDish = ref(null)

const defaultImage = 'https://img.yzcdn.cn/vant/leaf.jpg'

onMounted(() => {
  categories.value = [
    { id: 1, name: '热菜' },
    { id: 2, name: '凉菜' },
    { id: 3, name: '饮料' }
  ]

  dishes.value = [
    { id: 101, name: '宫保鸡丁', price: 28, categoryId: 1 },
    { id: 102, name: '鱼香肉丝', price: 26, categoryId: 1 },
    { id: 201, name: '拍黄瓜', price: 12, categoryId: 2 },
    { id: 202, name: '凉拌木耳', price: 14, categoryId: 2 },
    { id: 301, name: '可乐', price: 6, categoryId: 3 },
    { id: 302, name: '雪碧', price: 6, categoryId: 3 }
  ]
})


onMounted(async () => {
  try {
    const [categoryRes, dishRes] = await Promise.all([
      request.get('/menu/categories'),
      request.get('/menu/dishes')
    ])

    categories.value = categoryRes.data.data
    dishes.value = dishRes.data.data
  } catch (error) {
    virtualData(),
    console.log('获取菜单数据失败:', error)
  }
})

// function virtualData(){
//   categories.value = [
//     { id: 1, name: '模拟数据1' },
//     { id: 2, name: '模拟数据2' }
//   ]
//
//   dishes.value = [
//     { id: 101, name: '模拟数据1-2', price: 28, categoryId: 1 }
//   ]
// }

const filteredDishes = computed(() => {
  const currentId = categories.value[categoryIndex.value]?.id
  return dishes.value.filter(d => d.categoryId === currentId)
})

function onCategoryChange(index) {
  categoryIndex.value = index
}

function openDialog(dish) {
  selectedDish.value = dish
  showDialog.value = true
}

function onConfirm(options) {
  cart.addItem({
    ...selectedDish.value,
    ...options // { spiceLevel: '中辣', coriander: true, count: 2 }
  })
  showToast('已加入购物车')
  showDialog.value = false
}

function submitOrder() {
  if (cart.totalCount === 0) {
    showToast('请先选择菜品')
    return
  }
  showToast('提交订单功能开发中...')
}
</script>

<template>
  <div class="menu-page">
    <div class="content-area">
      <van-row class="flex-row">
        <van-col span="6" class="category-list">
          <van-sidebar v-model="categoryIndex" @change="onCategoryChange">
            <van-sidebar-item
                v-for="(cat, index) in categories"
                :key="cat.id"
                :title="cat.name"
            />
          </van-sidebar>
        </van-col>

        <van-col span="18" class="dish-list">
          <DishCard
              v-for="dish in filteredDishes"
              :key="dish.id"
              :dish="dish"
              @select="openDialog"
          />
        </van-col>
      </van-row>
    </div>

    <van-submit-bar
        :price="cart.totalPrice * 100"
        button-text="去结算"
        @submit="submitOrder"
    >
      已选 {{ cart.totalCount }} 件
    </van-submit-bar>

    <FlavorDialog v-model:show="showDialog" @confirm="onConfirm" />
  </div>
</template>
