<template>
  <PageLayout title="系统设置">
    <div class="system-settings">
      <el-card>
        <el-tabs v-model="activeTab" tab-position="top">
          <el-tab-pane label="基础配置" name="base">
            <BaseSettings/>
          </el-tab-pane>
          <el-tab-pane label="登录与安全" name="security">
            <SecuritySettings/>
          </el-tab-pane>
          <el-tab-pane label="业务设置" name="business">
            <BusinessSettings/>
          </el-tab-pane>
          <el-tab-pane label="打印与硬件" name="hardware">
            <HardwareSettings/>
          </el-tab-pane>
          <el-tab-pane label="消息通知" name="notify">
            <NotifySettings/>
          </el-tab-pane>
          <el-tab-pane label="维护与公告" name="maintenance">
            <MaintenanceSettings/>
          </el-tab-pane>
        </el-tabs>
      </el-card>
    </div>
  </PageLayout>
</template>

<script setup>
import {ref} from 'vue'
import BaseSettings from './settings/BaseSettings.vue'
import PageLayout from "@/components/layout/PageLayout.vue";
// import SecuritySettings from './settings/SecuritySettings.vue'
// import BusinessSettings from './settings/BusinessSettings.vue'
// import HardwareSettings from './settings/HardwareSettings.vue'
// import NotifySettings from './settings/NotifySettings.vue'
// import MaintenanceSettings from './settings/MaintenanceSettings.vue'

const activeTab = ref('base')
</script>

<style scoped>
.system-settings {
  padding: 0px;
}
</style>
