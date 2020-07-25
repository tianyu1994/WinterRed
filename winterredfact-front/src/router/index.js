import Vue from 'vue'
import VueRouter from 'vue-router'

import rumorRouter from './modules/rumor'

Vue.use(VueRouter)

export const constantRoutes = [
  rumorRouter
]

const router = new VueRouter({
  mode: 'hash',
  base: process.env.BASE_URL,
  constantRoutes
})

export default router
