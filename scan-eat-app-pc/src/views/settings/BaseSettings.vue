<template>
  <el-form :model="form" label-width="120px">
    <el-form-item label="系统名称">
      <el-input v-model="form.systemName" />
    </el-form-item>

    <el-form-item label="系统Logo">
      <el-upload :auto-upload="false">
        <el-button>上传 Logo</el-button>
      </el-upload>
    </el-form-item>

    <el-form-item label="主题色">
      <el-color-picker v-model="form.themeColor" />
    </el-form-item>

    <el-form-item>
      <el-button type="primary" @click="save">保存</el-button>
    </el-form-item>
  </el-form>
</template>

<script setup>
import { ref } from 'vue'
import { ElMessage } from 'element-plus'

const form = ref({
  systemName: '扫码点餐系统',
  themeColor: '#409EFF'
})

function save() {
  ElMessage.success('基础配置已保存')
}
</script>
