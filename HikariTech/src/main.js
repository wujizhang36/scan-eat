/*main.js*/
import { createApp } from 'vue'
import App from './App.vue'
import { router } from './router'
// import './styles/base.scss'
import './styles/index.css';  // 引入 Tailwind 样式
import 'animate.css';

const app = createApp(App)

app.use(router)
app.mount('#app')
