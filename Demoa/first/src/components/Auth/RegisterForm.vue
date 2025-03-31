<script setup>
import {reactive, ref} from "vue";
import {ElMessage} from "element-plus";
const props=defineProps(['isLoginComponent'])
const emits=defineEmits(["update:isLoginComponent"]);
const goAnotherForm=()=>{
  emits("update:isLoginComponent",true)
}
const a=ref('123')

const loginForm=reactive({
  username:'',
  password:''
})

const loginHandler=()=>{
  if (loginForm.username==='123'&&loginForm.password==='456'){
    ElMessage.success('登入成功')
  }else {
    ElMessage.warning('密码错了')
  }
}
</script>
<template>
  <div class="min-h-screen flex items-center justify-center bg-gray-100 loginbg">
    <div class="bg-white p-8 rounded-lg shadow-md w-full max-w-md">
      <h2 class="text-2xl font-semibold mb-6 text-center">注册</h2>
      <div class="space-y-6">
        <div class="mb-4">
          <label for="username" class="block text-sm font-medium text-gray-700">账号</label>
          <input
              type="email"
              id="username"
              v-model="loginForm.username"
              class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm"
              placeholder="请输入账号"
              required
          />
        </div>
        <div class="mb-6">
          <label for="password" class="block text-sm font-medium text-gray-700">密码</label>
          <input
              type="password"
              id="password"
              v-model="loginForm.password"
              class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm"
              placeholder="请输入密码"
              required
          />
        </div>
        <div class="flex items-center justify-between">
          <button @click="loginHandler(loginForm.username,loginForm.password)"
                  class="w-full bg-indigo-600 text-white py-2 px-4 rounded-md hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500">
            注册
          </button>
        </div>
        <a class="select-none" style="font-family:Simsun">已有账号</a>
        &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp
        <a @click='goAnotherForm' class="text-gray-700 hover:text-blue-600 hover:underline cursor-pointer" style="font-family:Simsun">马上登录</a>
      </div>
    </div>
  </div>
</template>

<style scoped>
/* 你可以在这里添加一些自定义样式 */
.loginbg {
  background-image: url('@/assets/images/3.jpg'); /* 替换为你的图片路径 */
  background-size: cover;
  background-position: center; /* 将背景图片居中 */
  background-repeat: no-repeat; /* 防止图片重复 */
  min-height: 100vh; /* 确保背景图片覆盖整个视口高度 */
}
</style>