<template>
  <PageLayout title="租户管理">
    <div class="tenant-page">
      <el-card>
        <div class="toolbar">
          <el-input v-model="search" placeholder="搜索租户名称" style="width: 200px" clearable @input="fetchData"/>
          <el-button type="primary" @click="openForm()">新增租户</el-button>
        </div>

        <el-table :data="tenantList" stripe style="width: 100%">
          <el-table-column prop="id" label="ID" width="80"/>
          <el-table-column prop="name" label="租户名称"/>
          <el-table-column prop="contact_email" label="邮箱"/>
          <el-table-column prop="phone" label="电话"/>
          <el-table-column prop="status" label="状态" width="100">
            <template #default="{ row }">
              <el-tag :type="row.status ? 'success' : 'danger'">
                {{ row.status ? '启用' : '禁用' }}
              </el-tag>
            </template>
          </el-table-column>
          <el-table-column label="操作" width="160">
            <template #default="{ row }">
              <el-button size="small" @click="openForm(row)">编辑</el-button>
              <el-button size="small" type="danger" @click="toggleStatus(row)">
                {{ row.status ? '禁用' : '启用' }}
              </el-button>
            </template>
          </el-table-column>
        </el-table>

        <el-pagination
            background
            layout="prev, pager, next"
            :total="total"
            :page-size="pageSize"
            :current-page="page"
            @current-change="handlePageChange"
            class="pagination"
        />
      </el-card>

      <TenantForm :model="formModel" :visible="formVisible" @close="formVisible = false; fetchData()"/>
    </div>
  </PageLayout>
</template>

<script setup>
import {ref, onMounted} from 'vue'
import {getTenantList, updateTenantStatus} from '@/api/tenant'
import TenantForm from './TenantForm.vue'
import PageLayout from '@/components/layout/PageLayout.vue'

const search = ref('')
const tenantList = ref([])
const page = ref(1)
const pageSize = 10
const total = ref(0)

const formVisible = ref(false)
const formModel = ref(null)

function fetchData() {
  getTenantList({page: page.value, pageSize, search: search.value}).then(res => {
    tenantList.value = res.data.list
    total.value = res.data.total
  })
}

function openForm(row = null) {
  formModel.value = row
  formVisible.value = true
}

function toggleStatus(row) {
  const status = row.status ? 0 : 1
  updateTenantStatus(row.id, status).then(fetchData)
}

function handlePageChange(p) {
  page.value = p
  fetchData()
}

onMounted(fetchData)
</script>

<style scoped>
.toolbar {
  display: flex;
  justify-content: space-between;
  margin-bottom: 16px;
}

.pagination {
  margin-top: 16px;
  text-align: right;
}
</style>
