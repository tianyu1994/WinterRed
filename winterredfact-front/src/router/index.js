import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const router = new VueRouter({
  mode: 'hash',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      redirect: '/rumor/list'
    },
    {
      path: '/rumor/list',
      name: 'List',
      component: () => import('@/views/rumor/list/index.vue'),
      meta: {
        title: '辟谣首页'
      }
    },
    {
      path: '/rumor/detail',
      name: 'Detail',
      component: () => import('@/views/rumor/detail/index.vue'),
      meta: {
        title: '辟谣详情'
      }
    },
    {
      path: '/rumor/check',
      name: 'Check',
      component: () => import('@/views/rumor/check/index.vue'),
      meta: {
        title: '核查谣言'
      }
    }
  ]
})

export default router
