<template>
  <PageLayout title="员工管理">
    <div class="employee-manager">

      <el-card>
        <div class="filters mb-4 flex space-x-3">
          <div class="search-bar">
            <el-select v-model="selectedTenant" placeholder="选择租户" @change="loadStores" style="width: 200px">
              <el-option v-for="tenant in tenants" :key="tenant.id" :label="tenant.name" :value="tenant.id"/>
            </el-select>

            <el-select v-model="selectedStore" placeholder="选择门店" :disabled="!selectedTenant" style="width: 200px">
              <el-option v-for="store in filteredStores" :key="store.id" :label="store.name" :value="store.id"/>
            </el-select>

            <el-input v-model="search" placeholder="搜索姓名/手机号" style="width: 250px"
                      @keyup.enter="filterEmployees"/>
            <el-button type="primary" @click="openAddDialog">新增员工</el-button>
          </div>
        </div>

        <el-table :data="filteredEmployees" style="width: 100%">
          <el-table-column prop="name" label="姓名"/>
          <el-table-column prop="phone" label="手机号"/>
          <el-table-column prop="role" label="岗位"/>
          <el-table-column prop="storeName" label="所属门店"/>
          <el-table-column prop="status" label="状态">
            <template #default="{ row }">
              <el-tag :type="row.status === 'active' ? 'success' : 'info'">
                {{ row.status === 'active' ? '启用' : '禁用' }}
              </el-tag>
            </template>
          </el-table-column>
          <el-table-column label="操作" width="180">
            <template #default="{ row }">
              <el-button size="small" @click="editEmployee(row)">编辑</el-button>
              <el-button size="small" type="danger" @click="deleteEmployee(row.id)">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-card>

      <!-- 新增/编辑员工 -->
      <el-dialog v-model="dialogVisible" :title="dialogTitle">
        <el-form :model="form" label-width="80px" :rules="rules" ref="formRef">
          <el-form-item label="姓名" prop="name">
            <el-input v-model="form.name"/>
          </el-form-item>
          <el-form-item label="手机号" prop="phone">
            <el-input v-model="form.phone"/>
          </el-form-item>
          <el-form-item label="岗位" prop="role">
            <el-select v-model="form.role">
              <el-option label="店长" value="manager"/>
              <el-option label="服务员" value="waiter"/>
              <el-option label="后厨" value="kitchen"/>
            </el-select>
          </el-form-item>
          <el-form-item label="状态" prop="status">
            <el-switch v-model="form.status" active-value="active" inactive-value="disabled"/>
          </el-form-item>
        </el-form>
        <template #footer>
          <el-button @click="dialogVisible = false">取消</el-button>
          <el-button type="primary" @click="submitForm">提交</el-button>
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
  {id: 102, tenantId: 1, name: '张记 - 分店'},
  {id: 201, tenantId: 2, name: '老王烧烤 - 总店'}
])

const employees = ref([
  {
    id: 1,
    tenantId: 1,
    storeId: 101,
    storeName: '张记 - 总店',
    name: '张三',
    phone: '18888888888',
    role: 'manager',
    status: 'active'
  },
  {
    id: 2,
    tenantId: 1,
    storeId: 102,
    storeName: '张记 - 分店',
    name: '李四',
    phone: '19999999999',
    role: 'waiter',
    status: 'disabled'
  }
])

const selectedTenant = ref(null)
const selectedStore = ref(null)
const search = ref('')
const dialogVisible = ref(false)
const dialogTitle = ref('新增员工')

const formRef = ref()
const form = ref({
  id: null,
  tenantId: null,
  storeId: null,
  storeName: '',
  name: '',
  phone: '',
  role: '',
  status: 'active'
})

const rules = {
  name: [{required: true, message: '请输入姓名', trigger: 'blur'}],
  phone: [{required: true, message: '请输入手机号', trigger: 'blur'}]
}

const filteredStores = computed(() => {
  return stores.value.filter(s => s.tenantId === selectedTenant.value)
})

const filteredEmployees = computed(() => {
  return employees.value.filter(emp => {
    const matchTenant = !selectedTenant.value || emp.tenantId === selectedTenant.value
    const matchStore = !selectedStore.value || emp.storeId === selectedStore.value
    const matchSearch = emp.name.includes(search.value) || emp.phone.includes(search.value)
    return matchTenant && matchStore && matchSearch
  })
})

function filterEmployees() {
  // 可以对接后端查询
}

function openAddDialog() {
  dialogTitle.value = '新增员工'
  form.value = {
    id: null,
    tenantId: selectedTenant.value,
    storeId: selectedStore.value,
    storeName: '',
    name: '',
    phone: '',
    role: '',
    status: 'active'
  }
  dialogVisible.value = true
}

function editEmployee(row) {
  dialogTitle.value = '编辑员工'
  form.value = {...row}
  dialogVisible.value = true
}

function deleteEmployee(id) {
  ElMessageBox.confirm('确认删除该员工？', '提示', {type: 'warning'}).then(() => {
    employees.value = employees.value.filter(e => e.id !== id)
    ElMessage.success('删除成功')
  })
}

function submitForm() {
  formRef.value.validate(valid => {
    if (!valid) return

    if (form.value.id) {
      const index = employees.value.findIndex(e => e.id === form.value.id)
      if (index !== -1) employees.value[index] = {...form.value}
      ElMessage.success('修改成功')
    } else {
      form.value.id = Date.now()
      const store = stores.value.find(s => s.id === form.value.storeId)
      form.value.storeName = store?.name || ''
      employees.value.push({...form.value})
      ElMessage.success('添加成功')
    }
    dialogVisible.value = false
  })
}

function loadStores() {
  selectedStore.value = null
}
</script>

<style scoped>
.employee-manager {
  padding: 0px;
}

.filters {
  margin-bottom: 20px;
}

.search-bar {
  display: flex;
  gap: 20px; /* 控制左右间距 */
  align-items: center;
}
</style>
