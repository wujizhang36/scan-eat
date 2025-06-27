<template>
  <van-popup :show="show" round position="bottom" :style="{ height: '60%' }" @update:show="updateShow">
    <div class="flavor-dialog">
      <h3 style="margin: 16px;">选择口味</h3>

      <van-cell title="辣度" />
      <van-radio-group v-model="spiceLevel">
        <van-radio name="不辣">不辣</van-radio>
        <van-radio name="中辣">中辣</van-radio>
        <van-radio name="重辣">重辣</van-radio>
      </van-radio-group>

      <van-cell title="是否加香菜">
        <template #right-icon>
          <van-switch v-model="coriander" />
        </template>
      </van-cell>

      <van-cell title="是否加蒜">
        <template #right-icon>
          <van-switch v-model="garlic" />
        </template>
      </van-cell>

      <van-cell title="份数">
        <template #right-icon>
          <van-stepper v-model="count" min="1" />
        </template>
      </van-cell>

      <div style="margin: 20px 16px 0;">
        <van-button type="primary" block @click="confirm">确定</van-button>
      </div>
    </div>
  </van-popup>
</template>

<script setup>
import { ref, watch } from 'vue'

const props = defineProps({ show: Boolean })  // 控制弹窗显示
const emit = defineEmits(['update:show', 'confirm'])

const spiceLevel = ref('中辣')  // 默认辣度
const coriander = ref(true)    // 默认有香菜
const garlic = ref(true)    // 默认有香菜
const count = ref(1)           // 默认份数

// 弹窗关闭时重置选项
watch(
    () => props.show,
    val => {
      if (!val) {
        count.value = 1
        spiceLevel.value = '中辣'
        coriander.value = true
      }
    }
)

// 确定按钮确认
function confirm() {
  emit('confirm', {
    spiceLevel: spiceLevel.value,
    coriander: coriander.value,
    count: count.value
  })
  emit('update:show', false)  // 更新 show 的值并关闭弹窗
}

// 更新 show 的值
function updateShow(val) {
  emit('update:show', val)
}
</script>

<style scoped>
.flavor-dialog {
  padding: 10px 16px;
}
</style>
