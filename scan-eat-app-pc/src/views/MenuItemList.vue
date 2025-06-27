<template>
  <PageLayout title="菜品管理">
    <div class="menu-item-list">
      <el-card>
        <!-- 搜索与门店选择 -->
        <div class="mb-3 flex justify-between">
          <div class="search-bar">
            <el-select v-model="selectedStoreId" placeholder="请选择门店" style="width: 200px" @change="loadData">
              <el-option
                  v-for="store in storeOptions"
                  :key="store.id"
                  :label="store.name"
                  :value="store.id"
              />
            </el-select>
            <el-input v-model="search" placeholder="搜索菜品..." style="width: 300px" @keyup.enter="loadData"/>
            <el-button type="primary" @click="openForm()">新增菜品</el-button>
          </div>
        </div>

        <!-- 分类 Tabs -->
        <el-tabs v-model="activeCategory" @tab-click="loadData" class="mb-3">
          <el-tab-pane label="热菜" name="hot"/>
          <el-tab-pane label="凉菜" name="cold"/>
          <el-tab-pane label="饮料" name="drink"/>
        </el-tabs>

        <!-- 菜品表格 -->
        <el-table :data="filteredList" border>
          <el-table-column prop="name" label="菜品名称"/>
          <el-table-column prop="price" label="价格"/>
          <el-table-column prop="category" label="分类"/>
          <el-table-column prop="isRecommended" label="推荐">
            <template #default="{ row }">
              <el-tag :type="row.isRecommended ? 'success' : 'info'">
                {{ row.isRecommended ? '是' : '否' }}
              </el-tag>
            </template>
          </el-table-column>
          <el-table-column prop="status" label="状态">
            <template #default="{ row }">
              <el-switch v-model="row.status" active-value="enabled" inactive-value="disabled"/>
            </template>
          </el-table-column>
          <el-table-column label="操作" width="200">
            <template #default="{ row }">
              <el-button size="small" @click="openForm(row)">编辑</el-button>
              <el-button size="small" type="danger" @click="deleteItem(row.id)">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-card>

      <!-- 菜品表单弹窗 -->
      <el-dialog v-model="formVisible" :title="form.id ? '编辑菜品' : '新增菜品'">
        <el-form :model="form" label-width="100px">
          <el-form-item label="菜品名称">
            <el-input v-model="form.name"/>
          </el-form-item>

          <el-form-item label="价格">
            <el-input-number v-model="form.price" :min="0" :step="0.1"/>
          </el-form-item>

          <el-form-item label="分类">
            <el-select v-model="form.category" placeholder="请选择分类">
              <el-option label="热菜" value="hot"/>
              <el-option label="凉菜" value="cold"/>
              <el-option label="饮品" value="drink"/>
              <el-option label="主食" value="main"/>
            </el-select>
          </el-form-item>

          <el-form-item label="上传图片">
            <el-upload
                class="upload-demo"
                action="#"
                :auto-upload="false"
                :show-file-list="false"
                :on-change="handleImageChange"
            >
              <el-button>上传</el-button>
              <img v-if="form.image" :src="form.image" style="max-width: 80px; margin-left: 10px"/>
            </el-upload>
          </el-form-item>

          <el-form-item label="推荐">
            <el-switch v-model="form.isRecommended"/>
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
import {ref, computed} from 'vue'
import {ElMessage, ElMessageBox} from 'element-plus'
import PageLayout from "@/components/layout/PageLayout.vue"

// 门店选项（演示用）
const storeOptions = ref([
  {id: '1', name: '门店 A'},
  {id: '2', name: '门店 B'}
])

const selectedStoreId = ref('')
const search = ref('')
const activeCategory = ref('hot') // 当前激活分类
const formVisible = ref(false)

const form = ref({
  id: null,
  name: '',
  price: 0,
  category: '',
  image: '',
  isRecommended: false,
  status: 'enabled'
})

// 模拟数据
const itemList = ref([
  {
    id: 1,
    name: '宫保鸡丁',
    price: 18,
    category: 'hot',
    image: '',
    isRecommended: true,
    status: 'enabled',
    storeIds: ['1']
  },
  {
    id: 2,
    name: '可乐',
    price: 6,
    category: 'drink',
    image: '',
    isRecommended: false,
    status: 'enabled',
    storeIds: ['1', '2']
  },
  {
    id: 3,
    name: '拍黄瓜',
    price: 12,
    category: 'cold',
    image: '',
    isRecommended: false,
    status: 'enabled',
    storeIds: ['2']
  }
])

const filteredList = computed(() =>
    itemList.value.filter(item =>
        item.name.includes(search.value) &&
        item.category === activeCategory.value &&
        (!selectedStoreId.value || item.storeIds?.includes?.(selectedStoreId.value))
    )
)

function openForm(row) {
  if (row) {
    form.value = {...row}
  } else {
    form.value = {
      id: null,
      name: '',
      price: 0,
      category: '',
      image: '',
      isRecommended: false,
      status: 'enabled'
    }
  }
  formVisible.value = true
}

function loadData() {
  console.log('加载数据，当前门店：', selectedStoreId.value, '分类：', activeCategory.value)
}

function handleImageChange(file) {
  const reader = new FileReader()
  reader.onload = e => {
    form.value.image = e.target.result
  }
  reader.readAsDataURL(file.raw)
}

function submitForm() {
  if (form.value.id) {
    const index = itemList.value.findIndex(item => item.id === form.value.id)
    itemList.value[index] = {...form.value}
    ElMessage.success('菜品更新成功')
  } else {
    form.value.id = Date.now()
    itemList.value.push({...form.value, storeIds: ['1', '2']}) // 默认添加到所有门店
    ElMessage.success('新增菜品成功')
  }
  formVisible.value = false
}

function deleteItem(id) {
  ElMessageBox.confirm('确定删除该菜品？', '提示', {type: 'warning'}).then(() => {
    itemList.value = itemList.value.filter(item => item.id !== id)
    ElMessage.success('删除成功')
  })
}
</script>

<style scoped>
.menu-item-list {
  padding: 0px;
}

.mb-3 {
  margin-bottom: 15px;
}

.search-bar {
  display: flex;
  gap: 20px; /* 控制左右间距 */
  align-items: center;
}
</style>
