// main.js
import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'

// 如果使用图标（可选）
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
import { createI18n } from 'vue-i18n'
import zhCN from './locales/zh-CN'
import en from './locales/en'
import './css/index.css'

// 如果使用状态管理
// import { createPinia } from 'pinia'

// router.beforeEach((to, from, next) => {
//     const isLoggedIn = !!localStorage.getItem('token'); // 或 sessionStorage.getItem('token') / cookie
//
//     if (to.meta.requiresAuth && !isLoggedIn) {
//         next({ path: '/login' });
//         console.log('1234')
//     } else {
//         next();
//     }
// });

const app = createApp(App)

// 注册 Element Plus 图标为全局组件（可选）
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
}

const i18n = createI18n({
    locale: 'zh-CN',
    fallbackLocale: 'en',
    messages: {
        'zh-CN': zhCN,
        en
    }
})


// 注册插件
app.use(i18n)
app.use(router)
app.use(ElementPlus)
// app.use(createPinia())

// 挂载
app.mount('#app')
