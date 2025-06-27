<template>
  <PageLayout title="订单管理">

    <div class="order-manager">

      <el-card>
        <div class="filters mb-3 flex space-x-3">
          <div class="search-bar">
            <!-- 租户选择下拉 -->
            <el-select v-model="selectedTenant" placeholder="选择租户" @change="loadStores" style="width: 250px">
              <el-option v-for="tenant in tenants" :key="tenant.id" :label="tenant.name" :value="tenant.id"/>
            </el-select>

            <!-- 门店选择下拉 -->
            <el-select v-model="selectedStore" placeholder="选择门店" @change="loadOrders" style="width: 250px"
                       :disabled="!selectedTenant">
              <el-option v-for="store in filteredStores" :key="store.id" :label="store.name" :value="store.id"/>
            </el-select>

            <!-- 订单状态选择下拉 -->
            <el-select v-model="orderStatus" placeholder="选择订单状态" style="width: 250px">
              <el-option label="待支付" value="pending"/>
              <el-option label="已支付" value="paid"/>
              <el-option label="已完成" value="completed"/>
              <el-option label="已取消" value="cancelled"/>
            </el-select>

            <!-- 搜索框 -->
            <el-input v-model="search" placeholder="搜索订单号或顾客姓名" style="width: 300px"
                      @keyup.enter="loadOrders"/>

            <!-- 导出按钮 -->
            <el-button type="primary" @click="exportOrders">导出订单</el-button>
          </div>
        </div>

        <el-table :data="filteredOrders" style="width: 100%">
          <el-table-column prop="orderNumber" label="订单号"/>
          <el-table-column prop="customerName" label="顾客姓名"/>
          <el-table-column prop="totalPrice" label="总金额"/>
          <el-table-column prop="status" label="状态">
            <template #default="{ row }">
              <el-tag :type="statusTagType(row.status)">
                {{ statusLabel(row.status) }}
              </el-tag>
            </template>
          </el-table-column>
          <el-table-column label="操作" width="200">
            <template #default="{ row }">
              <el-button size="small" @click="viewOrderDetail(row)">查看详情</el-button>
              <el-button size="small" type="danger" @click="deleteOrder(row.id)">删除</el-button>
            </template>
          </el-table-column>
        </el-table>

        <el-pagination
            :current-page="currentPage"
            :page-size="pageSize"
            :total="orders.length"
            @current-change="handlePageChange"
            layout="total, prev, pager, next"
            style="margin-top: 20px"
        />
      </el-card>

      <!-- 订单详情对话框 -->
      <el-dialog :visible.sync="orderDetailVisible" title="订单详情">
        <el-table :data="orderDetail.items">
          <el-table-column prop="name" label="菜品"/>
          <el-table-column prop="quantity" label="数量"/>
          <el-table-column prop="price" label="单价"/>
          <el-table-column prop="total" label="小计"/>
        </el-table>

        <div class="order-summary">
          <p>总金额：{{ orderDetail.totalPrice }} 元</p>
          <p>订单状态：{{ statusLabel(orderDetail.status) }}</p>
        </div>
        <template #footer>
          <el-button @click="orderDetailVisible = false">关闭</el-button>
        </template>
      </el-dialog>
    </div>
  </PageLayout>
</template>

<script setup>
import {ref, computed} from 'vue'
import {ElMessage, ElMessageBox} from 'element-plus'
import PageLayout from "@/components/layout/PageLayout.vue";

const tenants = ref([
  {id: 1, name: '张记餐厅'},
  {id: 2, name: '老王烧烤'}
])

const stores = ref([
  {id: 101, tenantId: 1, name: '张记 - 总店'},
  {id: 102, tenantId: 1, name: '张记 - 分店A'},
  {id: 201, tenantId: 2, name: '老王烧烤 - 总店'}
])

const orders = ref([
  {id: 1, orderNumber: 'ORD001', customerName: '张三', totalPrice: 78, status: 'pending', storeId: 101},
  {id: 2, orderNumber: 'ORD002', customerName: '李四', totalPrice: 45, status: 'paid', storeId: 101},
  {id: 3, orderNumber: 'ORD003', customerName: '王五', totalPrice: 112, status: 'completed', storeId: 102}
])

const selectedTenant = ref(null)
const selectedStore = ref(null)
const orderStatus = ref('')
const search = ref('')
const currentPage = ref(1)
const pageSize = ref(10)
const orderDetailVisible = ref(false)
const orderDetail = ref({items: [], totalPrice: 0, status: ''})

const filteredStores = computed(() => {
  return stores.value.filter(s => s.tenantId === selectedTenant.value)
})

const filteredOrders = computed(() => {
  return orders.value.filter(order => {
    return (
        (!selectedTenant.value || order.storeId === selectedStore.value) &&
        (!orderStatus.value || order.status === orderStatus.value) &&
        (order.orderNumber.includes(search.value) || order.customerName.includes(search.value))
    )
  })
})

function loadOrders() {
  // 这里可以连接后端，获取订单数据
}

function exportOrders() {
  ElMessage.success('导出订单功能正在开发中')
}

function statusLabel(status) {
  return status === 'pending' ? '待支付' : status === 'paid' ? '已支付' : status === 'completed' ? '已完成' : '已取消'
}

function statusTagType(status) {
  return status === 'pending' ? 'info' : status === 'paid' ? 'success' : status === 'completed' ? 'warning' : 'danger'
}

function viewOrderDetail(order) {
  // 这里展示订单详细内容
  orderDetail.value = {
    items: [
      {name: '宫保鸡丁', quantity: 1, price: 18, total: 18},
      {name: '可乐', quantity: 2, price: 6, total: 12}
    ],
    totalPrice: 78,
    status: order.status
  }
  orderDetailVisible.value = true
}

function handlePageChange(page) {
  currentPage.value = page
}

function deleteOrder(id) {
  ElMessageBox.confirm('确定删除该订单？', '提示', {
    type: 'warning'
  }).then(() => {
    orders.value = orders.value.filter(order => order.id !== id)
    ElMessage.success('删除成功')
  })
}
</script>

<style scoped>
.order-manager {
  padding: 0px;
}

.filters {
  margin-bottom: 20px;
}

.order-summary {
  margin-top: 20px;
}

.search-bar {
  display: flex;
  gap: 20px; /* 控制左右间距 */
  align-items: center;
}
</style>
