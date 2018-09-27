import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import NotFoundComponent from '@/components/NotFoundComponent'

Vue.use(Router);

export default new Router({
  mode: 'history',
  routes: [
    {path: '/', name: 'HelloWorld', component: HelloWorld},
    {path: '*', component: NotFoundComponent}
  ]
})
