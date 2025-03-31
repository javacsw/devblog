<script lang="ts" setup>
import {onMounted, reactive} from "vue";
import {get} from "@/net/index.js";
import router from "@/router/index.js";

import { ref } from 'vue'
import { ElButton, ElDrawer } from 'element-plus'
import { CircleCloseFilled } from '@element-plus/icons-vue'

import _default from "ant-design-vue/es/vc-slick/inner-slider";
import mounted = _default.mounted;
const visible = ref(false)

const options = reactive({
  blogs: []
})
const initData = () => {
  get('/api/blog/getInroduce', {}, (message, data) => {
    options.blogs = data
  })
}

onMounted(() => {
  initData()
})


</script>
<template>
  <div class="grid grid-cols-[1fr,5fr,1fr]">
    <div>
      <br><br>
      <div class="basic-color flex flex-nowrap gap-4 clickEffect1 rounded justify-center p-4">
        <div style="display: flex; align-items: center;">
          <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="size-6">
            <path stroke-linecap="round" stroke-linejoin="round" d="M9.813 15.904 9 18.75l-.813-2.846a4.5 4.5 0 0 0-3.09-3.09L2.25 12l2.846-.813a4.5 4.5 0 0 0 3.09-3.09L9 5.25l.813 2.846a4.5 4.5 0 0 0 3.09 3.09L15.75 12l-2.846.813a4.5 4.5 0 0 0-3.09 3.09ZM18.259 8.715 18 9.75l-.259-1.035a3.375 3.375 0 0 0-2.455-2.456L14.25 6l1.036-.259a3.375 3.375 0 0 0 2.455-2.456L18 2.25l.259 1.035a3.375 3.375 0 0 0 2.456 2.456L21.75 6l-1.035.259a3.375 3.375 0 0 0-2.456 2.456ZM16.894 20.567 16.5 21.75l-.394-1.183a2.25 2.25 0 0 0-1.423-1.423L13.5 18.75l1.183-.394a2.25 2.25 0 0 0 1.423-1.423l.394-1.183.394 1.183a2.25 2.25 0 0 0 1.423 1.423l1.183.394-1.183.394a2.25 2.25 0 0 0-1.423 1.423Z" />
          </svg>
          <h1 style="font-family:Simsun; margin: 0;">精选</h1>
        </div>
      </div>
      <div class="basic-color flex flex-nowrap gap-4 clickEffect1 rounded justify-center p-4">
        <div style="display: flex; align-items: center;">
          <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="size-6">
            <path stroke-linecap="round" stroke-linejoin="round" d="M12.75 19.5v-.75a7.5 7.5 0 0 0-7.5-7.5H4.5m0-6.75h.75c7.87 0 14.25 6.38 14.25 14.25v.75M6 18.75a.75.75 0 1 1-1.5 0 .75.75 0 0 1 1.5 0Z" />
          </svg>
          <h1 style="font-family:Simsun; margin: 0;">关注</h1>
        </div>
      </div>
      <div class="basic-color flex flex-nowrap gap-4 clickEffect1 rounded justify-center p-4">
        <div style="display: flex; align-items: center;">
          <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="size-6">
            <path stroke-linecap="round" stroke-linejoin="round" d="M8.625 12a.375.375 0 1 1-.75 0 .375.375 0 0 1 .75 0Zm0 0H8.25m4.125 0a.375.375 0 1 1-.75 0 .375.375 0 0 1 .75 0Zm0 0H12m4.125 0a.375.375 0 1 1-.75 0 .375.375 0 0 1 .75 0Zm0 0h-.375M21 12c0 4.556-4.03 8.25-9 8.25a9.764 9.764 0 0 1-2.555-.337A5.972 5.972 0 0 1 5.41 20.97a5.969 5.969 0 0 1-.474-.065 4.48 4.48 0 0 0 .978-2.025c.09-.457-.133-.901-.467-1.226C3.93 16.178 3 14.189 3 12c0-4.556 4.03-8.25 9-8.25s9 3.694 9 8.25Z" />
          </svg>
          <h1 style="font-family:Simsun; margin: 0;">评论</h1>
        </div>
      </div>
      <div @click="router.push('/mycentent')" class="basic-color flex flex-nowrap gap-4 clickEffect1 rounded justify-center p-4">
        <div style="display: flex; align-items: center;">
          <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="size-6">
            <path stroke-linecap="round" stroke-linejoin="round" d="M15.75 6a3.75 3.75 0 1 1-7.5 0 3.75 3.75 0 0 1 7.5 0ZM4.501 20.118a7.5 7.5 0 0 1 14.998 0A17.933 17.933 0 0 1 12 21.75c-2.676 0-5.216-.584-7.499-1.632Z" />
          </svg>
          <h1 style="font-family:Simsun; margin: 0;">我的</h1>
        </div>
      </div>

      <div style="text-align: center">
        <div class="flex flex-nowrap gap-4 clickEffect1 rounded justify-center p-4">
          <a href="https://xinghuo.xfyun.cn/desk">讯飞星火大模型</a>
        </div>
      </div>

      <div style="text-align: center">
        <el-button @click="visible = true">
          讯飞星火大模型
        </el-button>
      </div>

      <el-drawer v-model="visible" :show-close="false">
        <template #header="{ close, titleId, titleClass }">
          <h4 :id="titleId" :class="titleClass">我是人机孟凡业，有什么可以帮您的吗？</h4>
          <el-button type="danger" @click="close">
            <el-icon class="el-icon--left"><CircleCloseFilled /></el-icon>
          </el-button>
        </template>

        欢迎您的到来！

        <!-- 输入框 -->
        <div style="margin-top: 800px;">
          <el-input v-model="inputText" placeholder="请输入您的问题" style="width: 500px;"></el-input>
          <el-button type="primary" @click="sendMessage" style="margin-left: 10px;">发送</el-button>
        </div>
      </el-drawer>

    </div>

    <div>
      <div class="mx-auto  rounded-xl p-4 clickEffect1 border-2 mt-2" v-for="blog in options.blogs" @click="router.push('/view/'+blog.blog.id)" style="font-family:Simsun">
        <h1 class="text-[#265ca1]">{{ blog.blog.title }}</h1>
        <div class="ddd grid grid-cols-[1fr,14fr]">
          <a-avatar shape="square" class="w-full h-auto"
                    :src="blog.avator"/>
          <div style="font-family:Simsun"> <h2 class="text-sm">{{ blog.blog.content }}</h2></div>

        </div>

        <h3 class="text-blue-500">{{ blog.username }}</h3>
      </div>
    </div>

    <div>

    </div>
  </div>
</template>

<style scoped>
.ddd{
  background-color: transparent !important;
}

</style>