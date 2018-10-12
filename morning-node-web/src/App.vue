<template>
  <div id="app">
    <div class="wrap">
      <!--<Top></Top>-->
      <transition name="fade" mode="out-in">
        <!-- keep-alive排除article -->
        <keep-alive exclude="article_component">
          <router-view></router-view>
        </keep-alive>
      </transition>
    </div>
    <footer class="footer">
      <p>Copyright © <a href='https://github.com/ChoxSu'>ChoxSu</a>&nbsp;&nbsp;2018 </p>
      <p>Created by <a href="https://github.com/ChoxSu/morning.git">morning</a></p>
    </footer>
    <!-- 防止disqus导致首页报错 -->
    <div id="disqus_thread"></div>
  </div>
</template>

<script>
  //import Top from './components/common/Top.vue';

  export default {
    name: 'app',
    components: {
      //Top,
    },
  };
</script>

