export default [
    // ✅ 登录页（公开访问）
    {
        path: '/login',
        name: 'Login',
        component: () => import('@/views/Login.vue'),
        meta: { public: true, title: '登录' }
    },

    // ✅ 主布局（需要登录）
    {
        path: '/',
        component: () => import('@/layouts/MainLayout.vue'),
        children: [
            {
                path: '/',
                redirect: '/dashboard'
            },
            {
                path: '/dashboard',
                name: 'Dashboard',
                component: () => import('@/views/Dashboard.vue'),
                meta: {title: '首页概览', icon: 'HomeFilled', requiresAuth: true}
            },
            {
                path: '/tenants',
                name: 'TenantManagement',
                component: () => import('@/views/tenant/TenantList.vue'),
                meta: {title: '租户管理', icon: 'OfficeBuilding', requiresAuth: true}
            },
            {
                path: '/stores',
                name: 'StoreList',
                component: () => import('@/views/StoreList.vue'),
                meta: {title: '门店管理', icon: 'Shop', requiresAuth: true}
            },
            {
                path: '/menu-items',
                name: 'MenuItemList',
                component: () => import('@/views/MenuItemList.vue'),
                meta: {title: '菜品管理', icon: 'ForkSpoon', requiresAuth: true}
            },
            {
                path: '/tables',
                name: 'TableManager',
                component: () => import('@/views/TableManager.vue'),
                meta: {title: '餐桌管理', icon: 'Grid', requiresAuth: true}
            },
            {
                path: '/orders',
                name: 'OrderManager',
                component: () => import('@/views/OrderManager.vue'),
                meta: {title: '订单管理', icon: 'Document', requiresAuth: true}
            },
            {
                path: '/cart-monitor',
                name: 'CartMonitor',
                component: () => import('@/views/CartMonitor.vue'),
                meta: {title: '购物车监控', icon: 'ShoppingCart', requiresAuth: true}
            },
            {
                path: '/employees',
                name: 'EmployeeManager',
                component: () => import('@/views/EmployeeManager.vue'),
                meta: {title: '员工管理', icon: 'User', requiresAuth: true}
            },
            {
                path: '/takeout',
                name: 'takeout',
                component: () => import('@/views/TakeoutManager.vue'),
                meta: {title: '外卖管理', icon: 'Bicycle'}
            },
            {
                path: '/reviews',
                name: 'ReviewManager',
                component: () => import('@/views/ReviewManager.vue'),
                meta: {title: '评价管理', icon: 'Message', requiresAuth: true}
            },
            {
                path: '/settings',
                name: 'SystemSettings',
                component: () => import('@/views/SystemSettings.vue'),
                meta: {title: '系统设置', icon: 'Setting', roles: ['admin'], requiresAuth: true}
            },
            {
                path: '/about',
                name: 'About',
                component: () => import('@/views/About.vue'),
                meta: { title: '关于我们', icon: 'InfoFilled' }
            },
            {
                path: '/profile',
                name: 'Profile',
                component: () => import('@/views/Profile.vue'),
                meta: {title: '个人信息', hidden: true}
            }
        ]
    },

    // ✅ 404 页面（未匹配到路由）
    {
        path: '/:pathMatch(.*)*',
        name: 'NotFound',
        component: () => import('@/views/NotFound.vue'),
        meta: { title: '404 - 页面不存在', public: true }
    }
]
