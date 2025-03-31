<script setup>
import {onMounted, reactive} from "vue";
import {get} from "@/net/index.js";
import {useUserStore} from "@/stores/userStore.js";
import router from "@/router/index.js";

const options = reactive({
  blogs: [],
  currentUsername: '' // 用于存储当前用户的 username
})

const initUserData = () => {
  get('api/user/myinfo', {}, (message, data) => {
    useUserStore().login(data);
    options.currentUsername = data.username; // 获取当前用户的 username
  })
}

const initData = () => {
  get('/api/blog/getInroduce', {}, (message, data) => {
    options.blogs = data
  })
}

onMounted(() => {
  initData()
  initUserData()
})
</script>

<template>
  <div class="grid grid-cols-[1fr,2fr,5fr,1fr] bgcolor">
    <div></div>
    <div class="bg-gray-50 rounded-xl p-20 border-2 mt-2">
      <div>
        <img v-if="useUserStore().user !== null" :src="useUserStore().user.avator" alt="User Avatar" class="h-64"/>
        <br>
        <hr>
        <br>
        <div class="text-center rounded-xl clickEffect1 p-4 border-2">
          <button>更换头像</button>
        </div>
      </div>
    </div>

    <div class="bg-gray-50 rounded-xl p-4 border-2 mt-2">
      <br>
      <h1 style="font-size: 24px">个人信息</h1>
      <br>
      <hr>
      <br>
      <div>
        <h2>用户名: {{ options.currentUsername }}</h2>
      </div>
      <br>
      <div>
        <h2>邮箱地址:</h2>
      </div>
      <br>
      <div>
        <h2>个人简介:</h2>
      </div>
      <br>
      <div>
        <h2>组织:</h2>
      </div>
    </div>

    <div></div>
  </div>
</template>

<style scoped>
.bgcolor {
  background-color: #f0f0f0; /* 灰色背景 */
  min-height: 100vh; /* 确保背景颜色覆盖整个视口高度 */
}
</style>