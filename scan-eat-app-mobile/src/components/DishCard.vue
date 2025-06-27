<template>
  <van-card
      v-if="dish"
      :title="dish.name"
      :desc="dish.description || ''"
      :thumb="dish.image || 'https://img.yzcdn.cn/vant/leaf.jpg'"
      :price="dish.price"
      class="dish-card"
  >
    <template #footer>
      <van-button size="small" type="primary" @click="$emit('select', dish)">加入</van-button>
    </template>
  </van-card>
</template>

<script setup>
// 定义 props，确保 dish 是必传的对象
defineProps({
  dish: {
    type: Object,
    required: true
  }
})
</script>

<style scoped>
.dish-card {
  margin-bottom: 10px;
}
</style>
