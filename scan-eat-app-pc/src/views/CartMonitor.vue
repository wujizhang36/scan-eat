<template>

  <PageLayout title="购物车监控">
    <div class="cart-monitor">

      <el-card>
        <div class="filters mb-4 flex space-x-3">
          <div class="search-bar">
            <el-select v-model="selectedTenant" placeholder="选择租户" @change="loadStores" style="width: 200px">
              <el-option v-for="tenant in tenants" :key="tenant.id" :label="tenant.name" :value="tenant.id"/>
            </el-select>

            <el-select v-model="selectedStore" placeholder="选择门店" :disabled="!selectedTenant" style="width: 200px">
              <el-option v-for="store in filteredStores" :key="store.id" :label="store.name" :value="store.id"/>
            </el-select>

            <el-select v-model="selectedTable" placeholder="餐桌号" :disabled="!selectedStore" style="width: 200px">
              <el-option v-for="table in filteredTables" :key="table" :label="table" :value="table"/>
            </el-select>

            <el-switch v-model="onlyActive" active-text="仅显示活跃用户"/>

            <el-button type="primary" @click="exportData">导出数据</el-button>
          </div>
        </div>

        <el-table :data="filteredCarts" style="width: 100%">
          <el-table-column prop="sessionId" label="会话 ID"/>
          <el-table-column prop="userName" label="昵称/临时名"/>
          <el-table-column prop="storeName" label="门店"/>
          <el-table-column prop="tableNo" label="餐桌号"/>
          <el-table-column prop="itemCount" label="商品数量"/>
          <el-table-column prop="totalPrice" label="总金额（¥）"/>
          <el-table-column prop="updatedAt" label="最近活动时间"/>
          <el-table-column label="详情" width="120">
            <template #default="{ row }">
              <el-button size="small" @click="viewCartDetail(row)">查看</el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-card>

      <!-- 购物车详情弹窗 -->
      <el-dialog v-model="dialogVisible" title="购物车详情" width="500px">
        <el-table :data="selectedCart.items">
          <el-table-column prop="name" label="菜品"/>
          <el-table-column prop="quantity" label="数量"/>
          <el-table-column prop="price" label="单价"/>
          <el-table-column prop="total" label="小计"/>
        </el-table>
        <div class="mt-4">总金额：{{ selectedCart.totalPrice }} 元</div>
        <template #footer>
          <el-button @click="dialogVisible = false">关闭</el-button>
        </template>
      </el-dialog>
    </div>
  </PageLayout>
</template>

<script setup>
import {ref, computed} from 'vue'
import {ElMessage} from 'element-plus'
import PageLayout from "@/components/layout/PageLayout.vue";

const tableMap = ref({
  101: ['A1', 'A2', 'A12', 'B1'] // 门店ID => 餐桌号数组
})

const tenants = ref([
  {id: 1, name: '张记餐厅'},
  {id: 2, name: '老王烧烤'}
])

const stores = ref([
  {id: 101, tenantId: 1, name: '张记 - 总店'},
  {id: 201, tenantId: 2, name: '老王烧烤 - 总店'}
])

const carts = ref([
  {
    sessionId: 'sess-001',
    tenantId: 1,
    storeId: 101,
    storeName: '张记 - 总店',
    tableNo: 'A12',
    userName: '游客A',
    updatedAt: '2025-06-24 12:20:00',
    totalPrice: 38,
    itemCount: 2,
    items: [
      {name: '宫保鸡丁', quantity: 1, price: 18, total: 18},
      {name: '饮料', quantity: 2, price: 10, total: 20}
    ]
  },
  {
    sessionId: 'sess-002',
    tenantId: 2,
    storeId: 201,
    storeName: '老王烧烤 - 总店',
    tableNo: 'A1',
    userName: '游客B',
    updatedAt: '2025-06-24 11:45:00',
    totalPrice: 52,
    itemCount: 3,
    items: [
      {name: '羊肉串', quantity: 3, price: 10, total: 30},
      {name: '啤酒', quantity: 2, price: 11, total: 22}
    ]
  }
])

const selectedTenant = ref(null)
const selectedStore = ref(null)
const onlyActive = ref(true)

const selectedTable = ref(null)

const dialogVisible = ref(false)
const selectedCart = ref({items: [], totalPrice: 0})

// const filteredStores = computed(() => {
//   return stores.value.filter(s => s.tenantId === selectedTenant.value)
// })

const filteredStores = computed(() => stores.value.filter(s => s.tenantId === selectedTenant.value))

const filteredTables = computed(() =>
    selectedStore.value ? tableMap.value[selectedStore.value] || [] : []
)

const filteredCarts = computed(() => {
  return carts.value.filter(cart => {
    const matchTenant = !selectedTenant.value || cart.tenantId === selectedTenant.value
    const matchStore = !selectedStore.value || cart.storeId === selectedStore.value
    const matchTable = !selectedTable.value || cart.tableNo === selectedTable.value
    const matchActive = !onlyActive.value || isActive(cart.updatedAt)
    return matchTenant && matchStore && matchActive
  })
})

function isActive(updatedAt) {
  const lastTime = new Date(updatedAt).getTime()
  const now = Date.now()
  return now - lastTime < 10 * 60 * 1000 // 10分钟内活跃
}

function viewCartDetail(cart) {
  selectedCart.value = cart
  dialogVisible.value = true
}

function exportData() {
  ElMessage.success('导出功能开发中...')
}

function loadStores() {
  selectedStore.value = null
  selectedTable.value = null
}

function loadTables() {
  selectedTable.value = null
}
</script>

<style scoped>
.cart-monitor {
  padding: 0px;
}

.search-bar {
  display: flex;
  gap: 20px; /* 控制左右间距 */
  align-items: center;
}
</style>
