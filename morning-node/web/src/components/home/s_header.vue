<template>
  <div class="_header">
    <Layout>
      <Header>
        <Menu mode="horizontal" theme="dark" active-name="1">
          <div class="_header-logo"></div>
          <div class="_header-nav">
            <MenuItem name="1">
              <Icon type="ios-home"></Icon>
              首页
            </MenuItem>
            <MenuItem name="2">
              <Icon type="ios-keypad"></Icon>
              Item 2
            </MenuItem>
            <MenuItem name="3">
              <Icon type="ios-analytics"></Icon>
              Item 3
            </MenuItem>
            <MenuItem name="4">
              <Icon type="ios-paper"></Icon>
              Item 4
            </MenuItem>
          </div>
        </Menu>
      </Header>
    </Layout>
  </div>
</template>

<script>
  export default {
    name: "s_header"
  }
</script>

<style scoped>
  ._header {
    background: #f5f7f9;
    position: relative;
    overflow: hidden;
  }

  ._header-logo{
    width: 100px;
    height: 30px;
    background: #5b6270;
    border-radius: 3px;
    float: left;
    position: relative;
    top: 15px;
    left: 20px;
  }

  ._header-nav{
    width: 420px;
    margin: 0 20px 0 auto;
  }
</style>
