<template>
  <PageLayout title="欢迎使用扫码点餐管理系统">
    <div class="dashboard">
      <el-row :gutter="20" class="overview">
        <el-col :span="6">
          <el-card>
            <div class="card-title">今日订单</div>
            <div class="card-value">{{ overview.todayOrders }}</div>
          </el-card>
        </el-col>
        <el-col :span="6">
          <el-card>
            <div class="card-title">总收入</div>
            <div class="card-value">¥{{ overview.totalRevenue }}</div>
          </el-card>
        </el-col>
        <el-col :span="6">
          <el-card>
            <div class="card-title">租户总数</div>
            <div class="card-value">{{ overview.tenantCount }}</div>
          </el-card>
        </el-col>
        <el-col :span="6">
          <el-card>
            <div class="card-title">菜品数量</div>
            <div class="card-value">{{ overview.menuItemCount }}</div>
          </el-card>
        </el-col>
      </el-row>

      <el-card class="chart-card">
        <template #header>近 7 天订单趋势</template>
        <v-chart :option="chartOptions" autoresize/>
      </el-card>
    </div>
  </PageLayout>
</template>

<script setup>
import {ref, onMounted} from 'vue'
import {ElMessage} from 'element-plus'
import {use} from 'echarts/core'
import VChart from 'vue-echarts'
import {
  CanvasRenderer
} from 'echarts/renderers'
import {
  LineChart
} from 'echarts/charts'
import {
  TitleComponent,
  TooltipComponent,
  LegendComponent,
  GridComponent
} from 'echarts/components'
import PageLayout from "@/components/layout/PageLayout.vue";

use([
  CanvasRenderer,
  LineChart,
  TitleComponent,
  TooltipComponent,
  LegendComponent,
  GridComponent
])

const overview = ref({
  todayOrders: 0,
  totalRevenue: 0,
  tenantCount: 0,
  menuItemCount: 0
})

const chartOptions = ref({
  tooltip: {
    trigger: 'axis'
  },
  xAxis: {
    type: 'category',
    data: []
  },
  yAxis: {
    type: 'value'
  },
  series: [
    {
      name: '订单数',
      type: 'line',
      data: []
    }
  ]
})

onMounted(() => {
  // 模拟异步数据
  setTimeout(() => {
    overview.value = {
      todayOrders: 168,
      totalRevenue: 8423,
      tenantCount: 23,
      menuItemCount: 126
    }

    chartOptions.value.xAxis.data = ['周一', '周二', '周三', '周四', '周五', '周六', '周日']
    chartOptions.value.series[0].data = [120, 132, 101, 134, 90, 230, 210]
  }, 300)
})
</script>

<style scoped>
.dashboard {
  padding: 0px;
}

.overview {
  margin-top: 0px;
  margin-bottom: 20px;
}

.card-title {
  font-size: 14px;
  color: #666;
}

.card-value {
  font-size: 28px;
  font-weight: bold;
  margin-top: 10px;
  color: #333;
}

.chart-card {
  margin-top: 20px;
}
</style>
