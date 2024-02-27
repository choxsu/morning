import { createRouter, createWebHistory } from 'vue-router'
import FrontLayout from '@/layout/FrontLayout.vue'
const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'Front',
      component: FrontLayout,
      children: [
        {
          path: '/',
          name: 'home',
          component: () => import('@/views/home/HomeView.vue')
        },
        {
          path: '/about',
          name: 'about',
          component: () => import('@/views/about/AboutView.vue')
        }
      ]
    },
    {
      path: '/login',
      name: 'Login',
      component: () => import('@/views/login/Login.vue')
    }
  ]
})

export default router
