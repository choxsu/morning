import Vue from 'vue';
import VueRouter from 'vue-router';

Vue.use(VueRouter);

const List = resolve => require(['../view/List.vue'], resolve);
const Article = resolve => require(['../view/Article.vue'], resolve);
const NotFoundComponent = resolve => require(['../components/NotFoundComponent.vue'], resolve);

export function createRouter() {
  const router = new VueRouter({
    mode: 'history',
    scrollBehavior: (to, from, savedPosition) => {
      if (savedPosition) {
        return savedPosition;
      } else {
        return {x: 0, y: 0};
      }
    },
    routes: [
      {path: '/', component: List},
      {path: '/article/:id', component: Article, meta: {scrollToTop: true}},
      {path: '/page/:page', component: List},
      {path: '*', component: NotFoundComponent},
    ],
  });
  if (typeof window !== 'undefined') {
    router.afterEach((to, from) => {
      if (document && !(/\/article\//g.test(to.path))) {
        document.querySelector('title').innerText = 'ChoxSu\'s Blog';
      }
    });
  }
  return router;
}
