// // router/index.js
// import { createRouter, createWebHistory } from 'vue-router'
//
// const routes = [
//     {
//         path: '/',
//         redirect: '/dashboard'
//     },
//     {
//         path: '/dashboard',
//         name: 'Dashboard',
//         component: () => import('@/views/Dashboard.vue'),
//         meta: { title: '首页概览', icon: 'HomeFilled', requiresAuth: true }
//     },
//     {
//         path: '/tenants',
//         name: 'TenantManagement',
//         component: () => import('@/views/tenant/TenantList.vue'),
//         meta: { title: '租户管理', icon: 'OfficeBuilding', requiresAuth: true }
//     },
//     {
//         path: '/stores',
//         name: 'StoreList',
//         component: () => import('@/views/StoreList.vue'),
//         meta: { title: '门店管理', icon: 'Shop', requiresAuth: true }
//     },
//     {
//         path: '/menu-items',
//         name: 'MenuItemList',
//         component: () => import('@/views/MenuItemList.vue'),
//         meta: { title: '菜品管理', icon: 'ForkSpoon', requiresAuth: true }
//     },
//     {
//         path: '/tables',
//         name: 'TableManager',
//         component: () => import('@/views/TableManager.vue'),
//         meta: { title: '餐桌管理', icon: 'Grid', requiresAuth: true }
//     },
//     {
//         path: '/orders',
//         name: 'OrderManager',
//         component: () => import('@/views/OrderManager.vue'),
//         meta: { title: '订单管理', icon: 'Document', requiresAuth: true }
//     },
//     {
//         path: '/cart-monitor',
//         name: 'CartMonitor',
//         component: () => import('@/views/CartMonitor.vue'),
//         meta: { title: '购物车监控', icon: 'ShoppingCart', requiresAuth: true }
//     },
//     {
//         path: '/employees',
//         name: 'EmployeeManager',
//         component: () => import('@/views/EmployeeManager.vue'),
//         meta: { title: '员工管理', icon: 'User', requiresAuth: true }
//     },
//     {
//         path: '/reviews',
//         name: 'ReviewManager',
//         component: () => import('@/views/ReviewManager.vue'),
//         meta: { title: '评价管理', icon: 'Message', requiresAuth: true }
//     },
//     {
//         path: '/settings',
//         name: 'SystemSettings',
//         component: () => import('@/views/SystemSettings.vue'),
//         meta: { title: '系统设置', icon: 'Setting', roles: ['admin'], requiresAuth: true }
//     },
//     {
//         path: '/login',
//         name: 'Login',
//         component: () => import('@/views/Login.vue'),
//         meta: { title: '登录', public: true }
//     },
//     {
//         path: '/profile',
//         name: 'Profile',
//         component: () => import('@/views/Profile.vue'),
//         meta: { title: '个人信息', hidden: false }
//     },
//     // 可添加更多模块路由
// ]
//
// const router = createRouter({
//     history: createWebHistory(),
//     routes
// })
//
// // 路由守卫
// router.beforeEach((to, from, next) => {
//     const token = localStorage.getItem('token')
//     const isPublic = to.meta?.public // 登录页等开放页面
//     const requiresAuth = to.meta?.requiresAuth // 需要认证的路由
//     const userRoles = localStorage.getItem('roles') ? JSON.parse(localStorage.getItem('roles')) : []
//
//     // 如果是需要认证的路由且没有token，重定向到登录页面
//     if (requiresAuth && !token) {
//         return next('/login')
//     }
//
//     // 如果已经登录并访问的是登录页面，跳转到首页
//     if (to.path === '/login' && token) {
//         return next('/dashboard')
//     }
//
//     // 如果该路由有角色要求且用户角色不匹配，跳转到首页或其他页面
//     if (to.meta?.roles && !to.meta.roles.some(role => userRoles.includes(role))) {
//         return next('/dashboard') // 如果没有权限，重定向到首页或权限页面
//     }
//
//     next()
// })
//
// export default router



import {createRouter, createWebHashHistory, createWebHistory} from 'vue-router'
import routes from './routes'

const router = createRouter({
    // history: createWebHistory(),
    history: createWebHashHistory(),
    routes
})

// 登录验证守卫
router.beforeEach((to, from, next) => {
    const isLoggedIn = !!localStorage.getItem('token')
    if (to.meta.public || isLoggedIn) {
        next()
    } else {
        next('/login')
    }
})

export default router

