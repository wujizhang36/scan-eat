<template>
  <PageLayout title="外卖管理">
    <div class="takeout-manager">
      <!-- 筛选区域 -->
      <div class="filters mb-4 flex gap-4">
        <div class="search-bar">
          <el-select v-model="selectedTenant" placeholder="选择租户" @change="loadStores" style="width: 180px">
            <el-option v-for="tenant in tenants" :key="tenant.id" :label="tenant.name" :value="tenant.id"/>
          </el-select>

          <el-select v-model="selectedStore" placeholder="选择门店" :disabled="!selectedTenant" style="width: 180px">
            <el-option v-for="store in filteredStores" :key="store.id" :label="store.name" :value="store.id"/>
          </el-select>

          <el-select v-model="selectedStatus" placeholder="订单状态" clearable style="width: 160px">
            <el-option label="待接单" value="pending"/>
            <el-option label="已接单" value="accepted"/>
            <el-option label="配送中" value="delivering"/>
            <el-option label="已完成" value="completed"/>
            <el-option label="已取消" value="cancelled"/>
          </el-select>
        </div>
      </div>

      <!-- 外卖订单列表 -->
      <el-table :data="filteredOrders" style="width: 100%">
        <el-table-column label="客户" prop="customer"/>
        <el-table-column label="电话" prop="phone"/>
        <el-table-column label="地址" prop="address"/>
        <el-table-column label="订单内容">
          <template #default="{ row }">
            <el-button type="text" @click="viewItems(row)">查看</el-button>
          </template>
        </el-table-column>

        <el-table-column label="状态">
          <template #default="{ row }">
            <el-tag :type="statusTagType(row.status)">
              {{ statusText(row.status) }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column label="下单时间" prop="createdAt"/>
        <el-table-column label="操作" width="240">
          <template #default="{ row }">
            <el-button size="small" type="primary" @click="acceptOrder(row)" v-if="row.status === 'pending'">接单
            </el-button>
            <el-button size="small" type="warning" @click="startDelivery(row)" v-if="row.status === 'accepted'">配送中
            </el-button>
            <el-button size="small" type="success" @click="completeOrder(row)" v-if="row.status === 'delivering'">完成
            </el-button>
            <el-button size="small" type="danger" @click="cancelOrder(row)"
                       v-if="row.status !== 'completed' && row.status !== 'cancelled'">取消
            </el-button>
          </template>
        </el-table-column>
      </el-table>

      <el-dialog v-model="dialogVisible" title="订单详情" width="500px">
        <el-table :data="dialogItems" border>
          <el-table-column label="菜品名称" prop="name" />
          <el-table-column label="数量" prop="qty" width="60" />
          <el-table-column label="口味" prop="options" />
          <el-table-column label="备注" prop="remark" />
        </el-table>
        <template #footer>
          <el-button @click="dialogVisible = false">关闭</el-button>
        </template>
      </el-dialog>

    </div>
  </PageLayout>
</template>

<script setup>
import {ref, computed} from 'vue'
import {ElMessageBox, ElMessage} from 'element-plus'
import PageLayout from '@/components/layout/PageLayout.vue'

// 模拟租户、门店、订单数据
const tenants = ref([
  {id: 1, name: '张记餐厅'},
  {id: 2, name: '老王烧烤'}
])

const stores = ref([
  {id: 101, tenantId: 1, name: '张记 - 总店'},
  {id: 102, tenantId: 1, name: '张记 - 分店'},
  {id: 201, tenantId: 2, name: '老王烧烤 - 总店'}
])

const orders = ref([
  {
    id: 1,
    tenantId: 1,
    storeId: 101,
    customer: '李四',
    phone: '1234567890',
    address: '北京市朝阳区xx路',
    items: [{name: '宫保鸡丁', qty: 1}, {name: '米饭', qty: 2}],
    status: 'pending',
    createdAt: '2025-06-24 14:20'
  },
  {
    id: 2,
    tenantId: 1,
    storeId: 102,
    customer: '王五',
    phone: '13812345678',
    address: '上海市浦东新区xx街道',
    items: [{name: '烤鱼', qty: 1}],
    status: 'accepted',
    createdAt: '2025-06-24 15:10'
  }
])

const selectedTenant = ref(null)
const selectedStore = ref(null)
const selectedStatus = ref(null)

const filteredStores = computed(() => {
  return stores.value.filter(s => s.tenantId === selectedTenant.value)
})

const filteredOrders = computed(() => {
  return orders.value.filter(order => {
    const tenantMatch = !selectedTenant.value || order.tenantId === selectedTenant.value
    const storeMatch = !selectedStore.value || order.storeId === selectedStore.value
    const statusMatch = !selectedStatus.value || order.status === selectedStatus.value
    return tenantMatch && storeMatch && statusMatch
  })
})

function loadStores() {
  selectedStore.value = null
}

function acceptOrder(order) {
  order.status = 'accepted'
  ElMessage.success('已接单')
}

function startDelivery(order) {
  order.status = 'delivering'
  ElMessage.success('配送中')
}

function completeOrder(order) {
  order.status = 'completed'
  ElMessage.success('已完成')
}

function cancelOrder(order) {
  ElMessageBox.confirm('确认取消此订单？', '提示', {
    type: 'warning'
  }).then(() => {
    order.status = 'cancelled'
    ElMessage.success('订单已取消')
  })
}

function statusText(status) {
  return {
    pending: '待接单',
    accepted: '已接单',
    delivering: '配送中',
    completed: '已完成',
    cancelled: '已取消'
  }[status]
}

function statusTagType(status) {
  return {
    pending: 'info',
    accepted: 'primary',
    delivering: 'warning',
    completed: 'success',
    cancelled: 'danger'
  }[status]
}

const dialogVisible = ref(false)
const dialogItems = ref([])

function viewItems(row) {
  dialogItems.value = row.items
  dialogVisible.value = true
}


</script>

<style scoped>
.takeout-manager {
  padding: 0;
}

.filters {
  margin-bottom: 16px;
}

.search-bar {
  display: flex;
  gap: 20px;
  align-items: center;
}
</style>
