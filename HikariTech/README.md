# untitled

This template should help get you started developing with Vue 3 in Vite.

## Recommended IDE Setup

[VSCode](https://code.visualstudio.com/) + [Volar](https://marketplace.visualstudio.com/items?itemName=Vue.volar) (and disable Vetur).

## Customize configuration

See [Vite Configuration Reference](https://vite.dev/config/).

## Project Setup

```sh
npm install
```

### Compile and Hot-Reload for Development

```sh
npm run dev
```

### Compile and Minify for Production

```sh
npm run build
```

# Vue 企业网站目录结构

> 本文档提供一个中大型 Vue 3 企业网站项目推荐目录结构，包含常见功能模块，可按需增删。

---

## 📁 目录结构

```plaintext
src/
  assets/                      # 静态资源
    images/                    # 图片
    fonts/                     # 字体
    icons/                     # 图标
  components/                  # 通用、可复用组件
    common/                    # 基础组件（按钮、表单、弹窗）
    layout/                    # 页面骨架（Header, Footer, Navbar）
    ui/                        # 设计系统组件
  layouts/                     # 页面布局
    DefaultLayout.vue
    NoHeaderLayout.vue
  pages/                       # 路由级页面
    home/                      # 首页
      Index.vue
    about/                     # 关于我们
      Index.vue
    products/                  # 产品
      Index.vue
      Detail.vue
    news/                      # 新闻资讯
      Index.vue
      Detail.vue
    careers/                   # 招聘
      Index.vue
    contact/                   # 联系我们
      Index.vue
    not-found/                 # 404 页面
      NotFound.vue
  router/                      # vue-router 配置
    index.js
    routes.js
  store/                       # Pinia 或 Vuex
    index.js
    modules/                  # 按模块拆分
      user.js
      site.js
  services/                    # API 接口封装
    http.js                    # axios 配置
    productService.js
    newsService.js
  composables/                 # 自定义hooks
    usePagination.js
    useModal.js
  utils/                       # 工具函数
    formatDate.js
    validate.js
  locales/                     # 多语言
    en.json
    zh.json
  styles/                      # 样式
    base.scss
    variables.scss
    mixins.scss
  directives/                  # 自定义指令
    lazyload.js
  plugins/                     # Vue 插件
    axios.js
    dayjs.js
  mocks/                       # mock 数据 (如果需要)
    products.json
    news.json
  constants/                   # 枚举/常量
    siteConfig.js
  types/                       # TypeScript 类型
    product.d.ts
  App.vue                      # 根组件
  main.js                      # 入口
  vite.config.js               # Vite 配置
  env/                         # 环境变量
    .env.development
    .env.production
```
# 👍 总结

👉 **pages = 路由页面**
👉 **components = 可复用区块**
👉 **services = 接口逻辑**
👉 **store = 状态管理**
👉 **assets = 静态文件**
👉 **utils / composables = 复用逻辑**