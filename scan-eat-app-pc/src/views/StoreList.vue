<template>
  <PageLayout title="门店管理">
    <div class="store-list">

      <el-card>
        <el-button type="primary" @click="openForm()">新增门店</el-button>

        <el-table :data="storeList" style="width: 100%" class="mt-3">
          <el-table-column prop="name" label="门店名称"/>
          <el-table-column prop="address" label="地址"/>
          <el-table-column prop="phone" label="电话"/>
          <el-table-column prop="status" label="状态">
            <template #default="{ row }">
              <el-tag :type="row.status === 'enabled' ? 'success' : 'danger'">
                {{ row.status === 'enabled' ? '启用' : '禁用' }}
              </el-tag>
            </template>
          </el-table-column>
          <el-table-column label="操作" width="200">
            <template #default="{ row }">
              <el-button size="small" @click="openForm(row)">编辑</el-button>
              <el-button size="small" type="danger" @click="deleteStore(row.id)">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-card>

      <el-dialog v-model="formVisible" :title="form.id ? '编辑门店' : '新增门店'">
        <el-form :model="form" label-width="100px">
          <el-form-item label="门店名称">
            <el-input v-model="form.name"/>
          </el-form-item>
          <el-form-item label="地址">
            <el-input v-model="form.address"/>
          </el-form-item>
          <el-form-item label="电话">
            <el-input v-model="form.phone"/>
          </el-form-item>
          <el-form-item label="状态">
            <el-switch v-model="form.status" active-value="enabled" inactive-value="disabled"/>
          </el-form-item>
        </el-form>
        <template #footer>
          <el-button @click="formVisible = false">取消</el-button>
          <el-button type="primary" @click="submitForm">保存</el-button>
        </template>
      </el-dialog>
    </div>
  </PageLayout>
</template>

<script setup>
import {ref} from 'vue'
import {ElMessage, ElMessageBox} from 'element-plus'
import PageLayout from "@/components/layout/PageLayout.vue";

const storeList = ref([
  {id: 1, name: '本店', address: '东京涩谷', phone: '123456789', status: 'enabled'},
  {id: 2, name: '分店A', address: '大阪梅田', phone: '987654321', status: 'disabled'}
])

const formVisible = ref(false)
const form = ref({
  id: null,
  name: '',
  address: '',
  phone: '',
  status: 'enabled'
})

function openForm(row) {
  if (row) {
    form.value = {...row}
  } else {
    form.value = {id: null, name: '', address: '', phone: '', status: 'enabled'}
  }
  formVisible.value = true
}

function submitForm() {
  if (form.value.id) {
    const index = storeList.value.findIndex(s => s.id === form.value.id)
    storeList.value[index] = {...form.value}
    ElMessage.success('门店更新成功')
  } else {
    form.value.id = Date.now()
    storeList.value.push({...form.value})
    ElMessage.success('新增门店成功')
  }
  formVisible.value = false
}

function deleteStore(id) {
  ElMessageBox.confirm('确认删除该门店？', '提示', {
    type: 'warning'
  }).then(() => {
    storeList.value = storeList.value.filter(s => s.id !== id)
    ElMessage.success('删除成功')
  })
}
</script>

<style scoped>
.store-list {
  padding: 0px;
}

.mt-3 {
  margin-top: 0px;
}
</style>
