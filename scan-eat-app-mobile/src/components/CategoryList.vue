<template>
  <!-- 使用 v-bind 来传递 activeId -->
  <van-sidebar :active="activeId" @change="updateActiveId">
    <van-sidebar-item
        v-for="(c, index) in categories"
        :key="c.id"
        :title="c.name"
    />
  </van-sidebar>
</template>

<script setup>
import { defineProps, defineEmits } from 'vue'

const props = defineProps({
  activeId: {
    type: [String, Number],
    required: true
  },
  categories: {
    type: Array,
    required: true
  }
})

const emit = defineEmits(['update:activeId', 'select'])

function updateActiveId(newActiveId) {
  // 通过 $emit 来更新父组件中的 activeId
  emit('update:activeId', newActiveId)
  emit('select', newActiveId) // 传递新值给父组件
}
</script>
