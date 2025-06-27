<template>

  <PageLayout title="评价管理">
    <div class="review-manager">

      <el-card>
        <div class="filters mb-4 flex space-x-3">
          <div class="search-bar">
            <el-select v-model="selectedTenant" placeholder="选择租户" @change="loadStores" style="width: 200px">
              <el-option v-for="tenant in tenants" :key="tenant.id" :label="tenant.name" :value="tenant.id"/>
            </el-select>

            <el-select v-model="selectedStore" placeholder="选择门店" :disabled="!selectedTenant" style="width: 200px">
              <el-option v-for="store in filteredStores" :key="store.id" :label="store.name" :value="store.id"/>
            </el-select>

            <el-select v-model="selectedRating" placeholder="评分" style="width: 150px">
              <el-option label="全部" :value="null"/>
              <el-option label="好评（4~5）" :value="'good'"/>
              <el-option label="中评（2~3）" :value="'neutral'"/>
              <el-option label="差评（1）" :value="'bad'"/>
            </el-select>

            <el-date-picker
                v-model="dateRange"
                type="daterange"
                start-placeholder="开始日期"
                end-placeholder="结束日期"
                style="width: 300px"
            />

            <el-input v-model="search" placeholder="搜索评价内容/订单号" style="width: 250px"/>

            <el-button type="primary" @click="exportData">导出评价</el-button>
          </div>
        </div>

        <el-table :data="filteredReviews" style="width: 100%">
          <el-table-column prop="orderId" label="订单号" width="120"/>
          <el-table-column prop="storeName" label="门店" width="160"/>
          <el-table-column prop="userName" label="客户" width="120"/>
          <el-table-column label="评分" width="100">
            <template #default="{ row }">
              <el-rate v-model="row.rating" disabled/>
            </template>
          </el-table-column>
          <el-table-column prop="content" label="评价内容"/>
          <el-table-column prop="createdAt" label="时间" width="180"/>
          <el-table-column label="操作" width="160">
            <template #default="{ row }">
              <el-button size="small" @click="reply(row)">回复</el-button>
              <el-button size="small" type="danger" @click="remove(row.id)">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-card>

      <!-- 回复弹窗 -->
      <el-dialog v-model="dialogVisible" title="回复评价" width="500px">
        <el-input
            type="textarea"
            rows="5"
            v-model="replyContent"
            placeholder="输入你的回复内容"
        />
        <template #footer>
          <el-button @click="dialogVisible = false">取消</el-button>
          <el-button type="primary" @click="submitReply">提交</el-button>
        </template>
      </el-dialog>
    </div>
  </PageLayout>
</template>

<script setup>
import {ref, computed} from 'vue'
import {ElMessage} from 'element-plus'
import PageLayout from "@/components/layout/PageLayout.vue";

const tenants = ref([
  {id: 1, name: '张记餐厅'},
  {id: 2, name: '老王烧烤'}
])

const stores = ref([
  {id: 101, tenantId: 1, name: '张记 - 总店'},
  {id: 201, tenantId: 2, name: '老王烧烤 - 总店'}
])

const reviews = ref([
  {
    id: 1,
    tenantId: 1,
    storeId: 101,
    storeName: '张记 - 总店',
    orderId: 'A10001',
    userName: '用户A',
    rating: 5,
    content: '菜很好吃，服务也很快',
    createdAt: '2025-06-24 12:10:00'
  },
  {
    id: 2,
    tenantId: 2,
    storeId: 201,
    storeName: '老王烧烤 - 总店',
    orderId: 'B20002',
    userName: '用户B',
    rating: 2,
    content: '菜上得太慢了',
    createdAt: '2025-06-24 11:30:00'
  }
])

const selectedTenant = ref(null)
const selectedStore = ref(null)
const selectedRating = ref(null)
const search = ref('')
const dateRange = ref([])
const dialogVisible = ref(false)
const replyContent = ref('')
const replyTarget = ref(null)

const filteredStores = computed(() => {
  return stores.value.filter(s => s.tenantId === selectedTenant.value)
})

const filteredReviews = computed(() => {
  return reviews.value.filter(r => {
    const matchTenant = !selectedTenant.value || r.tenantId === selectedTenant.value
    const matchStore = !selectedStore.value || r.storeId === selectedStore.value
    const matchRating =
        !selectedRating.value ||
        (selectedRating.value === 'good' && r.rating >= 4) ||
        (selectedRating.value === 'neutral' && r.rating >= 2 && r.rating <= 3) ||
        (selectedRating.value === 'bad' && r.rating === 1)
    const matchSearch = !search.value || r.content.includes(search.value) || r.orderId.includes(search.value)
    const matchDate =
        dateRange.value.length === 0 ||
        (new Date(r.createdAt) >= new Date(dateRange.value[0]) &&
            new Date(r.createdAt) <= new Date(dateRange.value[1]))
    return matchTenant && matchStore && matchRating && matchSearch && matchDate
  })
})

function loadStores() {
  selectedStore.value = null
}

function exportData() {
  ElMessage.success('导出成功（模拟）')
}

function reply(row) {
  replyTarget.value = row
  replyContent.value = ''
  dialogVisible.value = true
}

function submitReply() {
  if (replyContent.value.trim()) {
    ElMessage.success(`已回复客户：${replyContent.value}`)
    dialogVisible.value = false
  } else {
    ElMessage.warning('请输入回复内容')
  }
}

function remove(id) {
  reviews.value = reviews.value.filter(r => r.id !== id)
  ElMessage.success('已删除评价')
}
</script>

<style scoped>
.review-manager {
  padding: 0px;
}

.search-bar {
  display: flex;
  gap: 20px; /* 控制左右间距 */
  align-items: center;
}
</style>
