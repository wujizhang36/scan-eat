import { createApp } from 'vue'
import App from './App.vue'
import router from './router'

import 'vant/lib/index.css'
import Vant from 'vant'

const app = createApp(App)

app.use(router)  // 确保路由被注册
app.use(Vant)    // 注册 Vant 组件库

app.mount('#app')  // 确保挂载到正确的 DOM 元素
