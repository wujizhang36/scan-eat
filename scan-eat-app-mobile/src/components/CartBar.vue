<template>
  <van-submit-bar
      :price="totalPrice * 100"
      button-text="去结算"
      @submit="submitOrder"
  >
    已选 {{ totalCount }} 件
  </van-submit-bar>
</template>

<script setup>
import { computed } from 'vue'
import { useCartStore } from '@/store/cart'
const cart = useCartStore()

const totalCount = computed(() => cart.totalCount)
const totalPrice = computed(() => cart.totalPrice)

function submitOrder() {
  cart.submit()
}
</script>
