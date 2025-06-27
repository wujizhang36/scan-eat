<!-- src/components/layout/PageLayout.vue -->
<template>
  <div class="page-layout">
    <el-page-header
        v-if="showHeader"
        :content="title"
        :icon="backIcon"
        @back="onBack"
    />
    <div class="page-body">
      <slot />
    </div>
  </div>
</template>

<script setup>
import { useRouter } from 'vue-router'
import { computed } from 'vue'
import { ArrowLeft } from '@element-plus/icons-vue'

const props = defineProps({
  title: String,
  showHeader: {
    type: Boolean,
    default: true
  },
  back: {
    type: Function
  }
})

const router = useRouter()

const onBack = () => {
  if (props.back) {
    props.back()
  } else {
    router.back()
  }
}

const backIcon = computed(() => ArrowLeft)
</script>

<style scoped>
.page-layout {
  padding: 10px;
}

.el-page-header {
  margin-bottom: 20px;
}

.page-body {
  background-color: #fff;
  border-radius: 8px;
  padding: 20px;
}
</style>
