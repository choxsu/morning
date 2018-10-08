<template>
  <div>
    <Header></Header>
    <div>
      中间, {{msg}}
    </div>
    <div>
      底部DIV
    </div>
  </div>
</template>

<script>
  import Header from "../components/home/Header";

  export default {
    name: "home",
    components: {Header},
    data() {
      return {
        msg: 'hello'
      }
    }
  }
</script>

<style scoped>

</style>
