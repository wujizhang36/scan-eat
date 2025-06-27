import {createRouter, createWebHashHistory, createWebHistory} from 'vue-router'
import MobileOrder from '@/views/MobileOrder.vue'  // 确保路径正确

const routes = [
    {
        path: '/',
        redirect: '/mobileOrder'  // 启动时重定向到菜单页
    },
    {
        path: '/mobileOrder',
        component: MobileOrder  // 确保 Menu 组件正确加载
    }
]

const router = createRouter({
    // history: createWebHistory(),
    history: createWebHashHistory(),
    routes
})

export default router
