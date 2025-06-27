<template>
  <div class="dish-list-page">
    <el-card>
      <div class="header">
        <el-input v-model="search" placeholder="搜索菜品名称..." prefix-icon="el-icon-search" @input="fetchDishes" />
        <el-button type="primary" @click="openAddDialog">新增菜品</el-button>
      </div>

      <el-table :data="filteredDishes" stripe style="width: 100%; margin-top: 16px">
        <el-table-column label="图片" width="100">
          <template #default="{ row }">
            <el-image :src="row.image_url" style="width: 60px; height: 60px" fit="cover" />
          </template>
        </el-table-column>
        <el-table-column prop="name" label="菜品名称" />
        <el-table-column prop="price" label="价格" width="100" />
        <el-table-column prop="status" label="状态" width="100">
          <template #default="{ row }">
            <el-tag :type="row.status === 1 ? 'success' : 'info'">
              {{ row.status === 1 ? '在售' : '停售' }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column label="操作" width="150">
          <template #default="{ row }">
            <el-button type="primary" link @click="editDish(row)">编辑</el-button>
            <el-button type="danger" link @click="deleteDish(row.id)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>

    <!-- 新增/编辑弹窗 -->
    <el-dialog v-model="dialogVisible" title="{{ isEdit ? '编辑菜品' : '新增菜品' }}" width="500px">
      <el-form :model="form" label-width="80px">
        <el-form-item label="名称">
          <el-input v-model="form.name" />
        </el-form-item>
        <el-form-item label="价格">
          <el-input-number v-model="form.price" :min="0" />
        </el-form-item>
        <el-form-item label="图片">
          <el-input v-model="form.image_url" placeholder="图片 URL" />
        </el-form-item>
        <el-form-item label="描述">
          <el-input type="textarea" v-model="form.description" />
        </el-form-item>
        <el-form-item label="状态">
          <el-switch v-model="form.status" :active-value="1" :inactive-value="0" />
        </el-form-item>
      </el-form>

      <template #footer>
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="saveDish">保存</el-button>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import { ref, computed, onMounted } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import axios from 'axios'

const search = ref('')
const dishes = ref([])
const dialogVisible = ref(false)
const isEdit = ref(false)
const form = ref({ name: '', price: 0, image_url: '', description: '', status: 1, id: null })

const fetchDishes = async () => {
  const res = await axios.get('/api/dishes')
  dishes.value = res.data
}

const filteredDishes = computed(() => {
  if (!search.value) return dishes.value
  return dishes.value.filter(d => d.name.includes(search.value))
})

const openAddDialog = () => {
  isEdit.value = false
  form.value = { name: '', price: 0, image_url: '', description: '', status: 1, id: null }
  dialogVisible.value = true
}

const editDish = (dish) => {
  isEdit.value = true
  form.value = { ...dish }
  dialogVisible.value = true
}

const saveDish = async () => {
  if (isEdit.value) {
    await axios.put(`/api/dishes/${form.value.id}`, form.value)
    ElMessage.success('修改成功')
  } else {
    await axios.post('/api/dishes', form.value)
    ElMessage.success('新增成功')
  }
  dialogVisible.value = false
  fetchDishes()
}

const deleteDish = (id) => {
  ElMessageBox.confirm('确定删除该菜品吗？', '提示', {
    type: 'warning',
  }).then(async () => {
    await axios.delete(`/api/dishes/${id}`)
    ElMessage.success('删除成功')
    fetchDishes()
  })
}

onMounted(fetchDishes)
</script>

<style scoped>
.dish-list-page {
  padding: 16px;
}
.header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 16px;
}
</style>