<template>
  <el-dialog :title="model?.id ? '编辑租户' : '新增租户'" v-model="visibleInner" width="500px" @close="$emit('close')">
    <el-form :model="form" :rules="rules" ref="formRef" label-width="100px">
      <el-form-item label="租户名称" prop="name">
        <el-input v-model="form.name" />
      </el-form-item>
      <el-form-item label="联系电话" prop="phone">
        <el-input v-model="form.phone" />
      </el-form-item>
      <el-form-item label="邮箱" prop="contact_email">
        <el-input v-model="form.contact_email" />
      </el-form-item>
    </el-form>

    <template #footer>
      <el-button @click="visibleInner = false">取消</el-button>
      <el-button type="primary" @click="submit">保存</el-button>
    </template>
  </el-dialog>
</template>

<script setup>
import { reactive, watch, ref } from 'vue'
import { createTenant, updateTenant } from '@/api/tenant'

const props = defineProps(['model', 'visible'])
const emit = defineEmits(['close'])

const visibleInner = ref(false)
watch(() => props.visible, v => (visibleInner.value = v))

const formRef = ref()
const form = reactive({
  name: '',
  phone: '',
  contact_email: ''
})

const rules = {
  name: [{ required: true, message: '请输入名称', trigger: 'blur' }]
}

watch(
    () => props.model,
    (val) => {
      if (val) {
        Object.assign(form, val)
      } else {
        Object.assign(form, { name: '', phone: '', contact_email: '' })
      }
    },
    { immediate: true }
)

function submit() {
  formRef.value.validate(async valid => {
    if (!valid) return
    const action = props.model?.id ? updateTenant : createTenant
    await action(form)
    visibleInner.value = false
    emit('close')
  })
}
</script>
