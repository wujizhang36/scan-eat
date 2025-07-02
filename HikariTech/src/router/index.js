import { createRouter, createWebHistory } from 'vue-router'
import Home from '@/pages/home/Index.vue'
import Contact from '@/pages/contact/Index.vue'
import NotFound from '@/pages/not-found/NotFound.vue'
import About from '@/pages/about/Index.vue'
import Products from '@/pages/products/Index.vue'
import News from '@/pages/news/Index.vue'

const routes = [
    {
        path: '/',
        component: Home,
        meta: { layout: 'default' }  // 使用默认布局
    },
    {
        path: '/contact',
        component: Contact,
        meta: { layout: 'default' }
    },
    {
        path: '/about',
        component: About,
        meta: { layout: 'default' }
    },
    {
        path: '/products',
        component: Products,
        meta: { layout: 'default' }
    },
    {
        path: '/news',
        component: News,
        meta: { layout: 'default' }
    },
    {
        path: '/custom-solution',
        name: 'CustomSolution',
        component: () => import('@/pages/getquote/GetQuote.vue')
    },
    {
        path: '/404',
        component: NotFound,
        meta: { layout: 'no-header' } // 使用不带Header的布局
    },
    {
        path: '/:pathMatch(.*)*',
        redirect: '/404'
    }
]

export const router = createRouter({
    history: createWebHistory(),
    routes
})
