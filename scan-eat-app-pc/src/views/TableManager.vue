<template>
  <PageLayout title="餐桌管理（含预约）">
    <div class="table-manager">
      <!-- 筛选与操作区域 -->
      <el-card>
        <div class="filters flex items-center flex-wrap gap-3 mb-4">
          <div class="search-bar">
            <!-- 选择租户 -->
            <el-select v-model="selectedTenant" placeholder="选择租户" @change="loadStores" style="width: 180px">
              <el-option v-for="tenant in tenants" :key="tenant.id" :label="tenant.name" :value="tenant.id"/>
            </el-select>

            <!-- 选择门店 -->
            <el-select v-model="selectedStore" placeholder="选择门店" :disabled="!selectedTenant" style="width: 180px">
              <el-option v-for="store in filteredStores" :key="store.id" :label="store.name" :value="store.id"/>
            </el-select>

            <!-- 选择状态 -->
            <el-select v-model="selectedStatus" placeholder="选择状态" clearable style="width: 160px">
              <el-option label="空闲" value="idle"/>
              <el-option label="使用中" value="busy"/>
              <el-option label="维护中" value="disabled"/>
            </el-select>

            <!-- 新增按钮 -->
            <el-button type="primary" @click="openForm" :disabled="!selectedStore">新增餐桌</el-button>
          </div>
        </div>

        <!-- 餐桌表格 -->
        <el-table :data="filteredTables" style="width: 100%">
          <el-table-column prop="name" label="餐桌号"/>
          <el-table-column prop="seats" label="座位数"/>
          <el-table-column prop="status" label="状态">
            <template #default="{ row }">
              <el-tag :type="statusTagType(row.status)">
                {{ statusLabel(row.status) }}
              </el-tag>
            </template>
          </el-table-column>
          <el-table-column label="预约" width="120">
            <template #default="{ row }">
              <el-button size="small" type="primary" @click="openReservation(row)">预约设置</el-button>
            </template>
          </el-table-column>
          <el-table-column label="二维码">
            <template #default="{ row }">
              <el-button size="small" @click="viewQrCode(row)">查看</el-button>
            </template>
          </el-table-column>
          <el-table-column label="预约记录" width="120">
            <template #default="{ row }">
              <el-button size="small" @click="showReservationList(row)">查看</el-button>
            </template>
          </el-table-column>
          <el-table-column label="操作" width="180">
            <template #default="{ row }">
              <el-button size="small" @click="openForm(row)">编辑</el-button>
              <el-button size="small" type="danger" @click="deleteTable(row.id)">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-card>

      <!-- 餐桌表单 -->
      <el-dialog v-model="formVisible" :title="form.id ? '编辑餐桌' : '新增餐桌'">
        <el-form :model="form" label-width="100px">
          <el-form-item label="餐桌号">
            <el-input v-model="form.name"/>
          </el-form-item>
          <el-form-item label="座位数">
            <el-input-number v-model="form.seats" :min="1"/>
          </el-form-item>
          <el-form-item label="状态">
            <el-select v-model="form.status">
              <el-option label="空闲" value="idle"/>
              <el-option label="使用中" value="busy"/>
              <el-option label="维护中" value="disabled"/>
            </el-select>
          </el-form-item>
        </el-form>
        <template #footer>
          <el-button @click="formVisible = false">取消</el-button>
          <el-button type="primary" @click="submitForm">保存</el-button>
        </template>
      </el-dialog>

      <!-- 预约对话框 -->
      <el-dialog v-model="reservationVisible" title="设置预约">
        <el-form :model="reservationForm" label-width="100px">
          <el-form-item label="姓名">
            <el-input v-model="reservationForm.name"/>
          </el-form-item>
          <el-form-item label="联系方式">
            <el-input v-model="reservationForm.phone"/>
          </el-form-item>
          <el-form-item label="预约时间">
            <el-date-picker v-model="reservationForm.time" type="datetime" placeholder="选择时间"/>
          </el-form-item>
          <el-form-item label="人数">
            <el-input-number v-model="reservationForm.people" :min="1"/>
          </el-form-item>
          <el-form-item label="状态">
            <el-select v-model="reservationForm.status">
              <el-option label="已预约" value="booked"/>
              <el-option label="已取消" value="cancelled"/>
            </el-select>
          </el-form-item>
        </el-form>
        <template #footer>
          <el-button @click="reservationVisible = false">取消</el-button>
          <el-button type="primary" @click="saveReservation">保存</el-button>
        </template>
      </el-dialog>

      <!-- 预约表单 -->
      <el-dialog v-model="reservationListVisible" title="预约列表" width="600px">
        <el-table :data="currentReservations" style="width: 100%">
          <el-table-column prop="name" label="姓名"/>
          <el-table-column prop="phone" label="手机号"/>
          <el-table-column prop="people" label="人数"/>
          <el-table-column prop="time" label="预约时间"/>
          <el-table-column prop="status" label="状态">
            <template #default="{ row }">
              <el-tag :type="row.status === 'booked' ? 'success' : 'info'">
                {{ row.status === 'booked' ? '已预约' : '已取消' }}
              </el-tag>
            </template>
          </el-table-column>
          <el-table-column label="操作" width="120">
            <template #default="{ row }">
              <el-button
                  size="small"
                  type="danger"
                  @click="cancelReservation(row)"
                  :disabled="row.status !== 'booked'"
              >
                取消预约
              </el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-dialog>

    </div>
  </PageLayout>
</template>

<script setup>
import {ref, computed} from 'vue'
import {ElMessage, ElMessageBox} from 'element-plus'
import PageLayout from '@/components/layout/PageLayout.vue'

// 模拟租户与门店
const tenants = ref([
  {id: 1, name: '张记餐厅'},
  {id: 2, name: '老王烧烤'}
])

const stores = ref([
  {id: 101, tenantId: 1, name: '张记 - 总店'},
  {id: 201, tenantId: 2, name: '老王烧烤 - 总店'}
])

// 餐桌数据
const tables = ref([
  {id: 1, storeId: 101, name: 'A1', seats: 4, status: 'idle', qrCode: 'https://qrcode.com/a1'},
  {id: 2, storeId: 101, name: 'A2', seats: 2, status: 'busy', qrCode: 'https://qrcode.com/a2'}
])

// ✅ 模拟预约数据
const reservations = ref([
  {tableId: 1, name: '张三', phone: '13888888888', time: '2025-07-01 18:00', people: 4, status: 'booked'},
  {tableId: 2, name: '李四', phone: '13999999999', time: '2025-07-02 12:30', people: 2, status: 'booked'}
])

// 表单数据
const selectedTenant = ref(null)
const selectedStore = ref(null)
const selectedStatus = ref(null)
const formVisible = ref(false)
const reservationVisible = ref(false)

const form = ref({
  id: null, name: '', seats: 4, status: 'idle', storeId: null, qrCode: ''
})

const reservationForm = ref({
  tableId: null, name: '', phone: '', time: '', people: 2, status: 'booked'
})

const filteredStores = computed(() => stores.value.filter(s => s.tenantId === selectedTenant.value))

const filteredTables = computed(() => {
  return tables.value.filter(t =>
      t.storeId === selectedStore.value &&
      (!selectedStatus.value || t.status === selectedStatus.value)
  )
})

function loadStores() {
  selectedStore.value = null
}

function openForm(row) {
  if (row) {
    form.value = {...row}
  } else {
    form.value = {
      id: null, name: '', seats: 4, status: 'idle',
      storeId: selectedStore.value, qrCode: ''
    }
  }
  formVisible.value = true
}

function submitForm() {
  if (!form.value.name) return ElMessage.warning('请填写餐桌号')

  if (form.value.id) {
    const index = tables.value.findIndex(t => t.id === form.value.id)
    tables.value[index] = {...form.value}
    ElMessage.success('更新成功')
  } else {
    form.value.id = Date.now()
    form.value.qrCode = `https://qrcode.com/${form.value.name}`
    tables.value.push({...form.value})
    ElMessage.success('添加成功')
  }
  formVisible.value = false
}

function deleteTable(id) {
  ElMessageBox.confirm('确定删除该餐桌？', '提示', {type: 'warning'}).then(() => {
    tables.value = tables.value.filter(t => t.id !== id)
    reservations.value = reservations.value.filter(r => r.tableId !== id)
    ElMessage.success('删除成功')
  })
}

function viewQrCode(row) {
  window.open(row.qrCode, '_blank')
}

function statusLabel(status) {
  return status === 'idle' ? '空闲' : status === 'busy' ? '使用中' : '维护中'
}

function statusTagType(status) {
  return status === 'idle' ? 'success' : status === 'busy' ? 'warning' : 'info'
}

function openReservation(table) {
  const existing = reservations.value.find(r => r.tableId === table.id)
  reservationForm.value = existing ? {...existing} : {
    tableId: table.id, name: '', phone: '', time: '', people: 2, status: 'booked'
  }
  reservationVisible.value = true
}

function saveReservation() {
  const idx = reservations.value.findIndex(r => r.tableId === reservationForm.value.tableId)
  if (idx > -1) {
    reservations.value[idx] = {...reservationForm.value}
  } else {
    reservations.value.push({...reservationForm.value})
  }
  ElMessage.success('预约信息已保存')
  reservationVisible.value = false
}

const reservationListVisible = ref(false)
const currentReservations = ref([])

function showReservationList(table) {
  currentReservations.value = reservations.value.filter(r => r.tableId === table.id)
  reservationListVisible.value = true
}

function cancelReservation(row) {
  ElMessageBox.confirm(`确认取消预约：${row.name}？`, '提示', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning',
  }).then(() => {
    row.status = 'cancelled'
    ElMessage.success('已取消预约')
  }).catch(() => {
    // 用户取消操作，无需处理
  })
}

</script>

<style scoped>
.table-manager {
  padding: 0px;
}

.filters {
  margin-bottom: 15px;
}

.search-bar {
  display: flex;
  gap: 20px;
  align-items: center;
}

/* 加在 <style scoped> 里 */
.el-dialog .el-table {
  margin-top: 10px;
}
</style>
