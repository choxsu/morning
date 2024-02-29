<script setup lang="ts">
import { ref } from 'vue'
const smsLoginForm = ref({
  phone: '',
  code: ''
})

const accountLoginForm = ref({
  username: '',
  password: ''
})

const submitSMSLoginForm = () => {
  // 处理短信验证码登录表单提交
}

const submitAccountLoginForm = () => {
  // 处理账号密码登录表单提交
}
</script>
<template>
  <div class="login-container">
    <div class="main">
      <div class="login-methods">
        <div class="sms-login">
          <h2>短信验证码登录</h2>
          <!-- 短信验证码登录表单 -->
          <form @submit.prevent="submitSMSLoginForm">
            <el-form-item label="手机号" prop="phone">
              <el-input v-model="smsLoginForm.phone"/>
            </el-form-item>
            <el-form-item label="验证码" prop="code">
              <el-input v-model="smsLoginForm.code"/>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" native-type="submit">登录</el-button>
            </el-form-item>
          </form>
        </div>
        <div class="account-login">
          <h2>账号密码登录</h2>
          <!-- 账号密码登录表单 -->
          <form @submit.prevent="submitAccountLoginForm">
            <el-form-item label="账号" prop="username">
              <el-input v-model="accountLoginForm.username"/>
            </el-form-item>
            <el-form-item label="密码" prop="password">
              <el-input type="password" v-model="accountLoginForm.password"/>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" native-type="submit">登录</el-button>
            </el-form-item>
          </form>
        </div>
      </div>
      <div class="other-login">
        <h3>其他登录方式</h3>
        <div class="wechat-login">微信登录</div>
        <!-- 其他登录方式 -->
      </div>
    </div>
  </div>
</template>
<style scoped>
.login-container {
  max-width: 600px;
  margin: 0 auto;
  padding: 20px;
}

.header {
  display: flex;
  align-items: center;
  justify-content: space-between;
  margin-bottom: 20px;
}

.logo {
  width: 100px;
}

.header a {
  margin-right: 20px;
}

.login-btn {
  padding: 10px 20px;
  background-color: #409eff;
  color: #fff;
  border-radius: 5px;
  cursor: pointer;
}

.main {
  border: 1px solid #ccc;
  padding: 20px;
  border-radius: 5px;
  margin-bottom: 20px;
}

.login-methods {
  display: flex;
  justify-content: space-between;
  margin-bottom: 20px;
}

.login-methods h2 {
  margin-bottom: 10px;
}

.other-login {
  text-align: center;
}

.footer {
  text-align: center;
  color: #666;
}
</style>
