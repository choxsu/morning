import Vue from 'vue'
import Router from 'vue-router'
import NotFoundComponent from '@/components/NotFoundComponent'
import home from '@/view/home'


Vue.use(Router);

export default new Router({
  mode: 'history',
  routes: [
    {path: '/', name: 'home', component: home},
    {path: '*', component: NotFoundComponent},
  ]
})
