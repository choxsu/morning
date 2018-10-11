<template>
  <div>
    <s_header></s_header>
    <div>
      <Layout>
        <Content :style="{padding: '0 50px'}">
          <Breadcrumb :style="{margin: '20px 0'}">
            <BreadcrumbItem>Home</BreadcrumbItem>
            <BreadcrumbItem>Components</BreadcrumbItem>
            <BreadcrumbItem>Layout</BreadcrumbItem>
          </Breadcrumb>
          <Card>
            <div style="min-height: 200px;">
              Content
              <br>
              中间, {{msg}}
            </div>
          </Card>
        </Content>
      </Layout>
    </div>
    <s_footer></s_footer>
  </div>
</template>

<script>
  import s_header from "../components/home/s_header";
  import S_footer from "../components/home/s_footer";

  export default {
    name: "home",
    components: {S_footer, s_header},
    data() {
      return {
        msg: 'hello'
      }
    }
  }
</script>

<style scoped>

</style>
