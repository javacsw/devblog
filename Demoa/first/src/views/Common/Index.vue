<script lang="ts" setup>
import {onMounted, reactive, ref, computed} from "vue";
import {get} from "@/net/index.js";
import router from "@/router/index.js";
import { ElButton, ElDrawer, ElInput, ElIcon } from 'element-plus'
import { CircleCloseFilled, Search } from '@element-plus/icons-vue'

const visible = ref(false)
const inputText = ref('')
const searchQuery = ref('')

const defaultAvatar = 'https://api.dicebear.com/9.x/micah/svg?seed=Oreo&backgroundColor=f0db4f,ffdfbf'
const handleImageError = (e: Event) => {
  const target = e.target as HTMLImageElement
  target.src = defaultAvatar
}

const options = reactive({
  blogs: []
})

const initData = () => {
  get('/api/blog/getInroduce', {}, (message, data) => {
    options.blogs = data
  })
}

const filteredBlogs = computed(() => {
  if (!searchQuery.value) return options.blogs;
  const keyword = searchQuery.value.toLowerCase();
  return options.blogs.filter((blog: any) => {
    const titleMatch = blog.blog.title?.toLowerCase().includes(keyword);
    const contentMatch = blog.blog.content?.toLowerCase().includes(keyword);
    return titleMatch || contentMatch;
  });
})

const sendMessage = () => {
  console.log('Sending message:', inputText.value)
}

onMounted(() => {
  initData()
})

</script>

<template>
  <div class="max-w-[1650px] mx-auto px-4 lg:px-12 py-8">
    <div class="grid grid-cols-1 md:grid-cols-[360px_1fr] gap-12">
      
      <!-- Left Sidebar: Navigation & Controls -->
      <div class="flex flex-col gap-4 pt-2">
        
        <!-- 搜索框 -->
        <div class="basic-color rounded-xl p-3 mb-2 shadow-sm border border-gray-100 dark:border-gray-800 transition-colors">
          <el-input
            v-model="searchQuery"
            placeholder="搜索文章标题或内容..."
            clearable
            :prefix-icon="Search"
            size="large"
            style="--el-input-border-radius: 8px;"
          />
        </div>

        <div class="basic-color flex items-center gap-4 clickEffect1 rounded-xl p-4 transition-colors" @click="router.push('/')">
          <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="w-7 h-7 text-blue-500">
            <path stroke-linecap="round" stroke-linejoin="round" d="M9.813 15.904 9 18.75l-.813-2.846a4.5 4.5 0 0 0-3.09-3.09L2.25 12l2.846-.813a4.5 4.5 0 0 0 3.09-3.09L9 5.25l.813 2.846a4.5 4.5 0 0 0 3.09 3.09L15.75 12l-2.846.813a4.5 4.5 0 0 0-3.09 3.09ZM18.259 8.715 18 9.75l-.259-1.035a3.375 3.375 0 0 0-2.455-2.456L14.25 6l1.036-.259a3.375 3.375 0 0 0 2.455-2.456L18 2.25l.259 1.035a3.375 3.375 0 0 0 2.456 2.456L21.75 6l-1.035.259a3.375 3.375 0 0 0-2.456 2.456ZM16.894 20.567 16.5 21.75l-.394-1.183a2.25 2.25 0 0 0-1.423-1.423L13.5 18.75l1.183-.394a2.25 2.25 0 0 0 1.423-1.423l.394-1.183.394 1.183a2.25 2.25 0 0 0 1.423 1.423l1.183.394-1.183.394a2.25 2.25 0 0 0-1.423 1.423Z" />
          </svg>
          <span class="text-lg font-bold">精选</span>
        </div>
        
        <div class="basic-color flex items-center gap-4 clickEffect1 rounded-xl p-4 transition-colors">
          <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="w-7 h-7 text-indigo-500">
            <path stroke-linecap="round" stroke-linejoin="round" d="M12.75 19.5v-.75a7.5 7.5 0 0 0-7.5-7.5H4.5m0-6.75h.75c7.87 0 14.25 6.38 14.25 14.25v.75M6 18.75a.75.75 0 1 1-1.5 0 .75.75 0 0 1 1.5 0Z" />
          </svg>
          <span class="text-lg font-bold">关注</span>
        </div>
        
        <div class="basic-color flex items-center gap-4 clickEffect1 rounded-xl p-4 transition-colors">
          <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="w-7 h-7 text-green-500">
            <path stroke-linecap="round" stroke-linejoin="round" d="M8.625 12a.375.375 0 1 1-.75 0 .375.375 0 0 1 .75 0Zm0 0H8.25m4.125 0a.375.375 0 1 1-.75 0 .375.375 0 0 1 .75 0Zm0 0H12m4.125 0a.375.375 0 1 1-.75 0 .375.375 0 0 1 .75 0Zm0 0h-.375M21 12c0 4.556-4.03 8.25-9 8.25a9.764 9.764 0 0 1-2.555-.337A5.972 5.972 0 0 1 5.41 20.97a5.969 5.969 0 0 1-.474-.065 4.48 4.48 0 0 0 .978-2.025c.09-.457-.133-.901-.467-1.226C3.93 16.178 3 14.189 3 12c0-4.556 4.03-8.25 9-8.25s9 3.694 9 8.25Z" />
          </svg>
          <span class="text-lg font-bold">评论</span>
        </div>
        
        <div @click="router.push('/mycentent')" class="basic-color flex items-center gap-4 clickEffect1 rounded-xl p-4 transition-colors">
          <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="w-7 h-7 text-purple-500">
            <path stroke-linecap="round" stroke-linejoin="round" d="M15.75 6a3.75 3.75 0 1 1-7.5 0 3.75 3.75 0 0 1 7.5 0ZM4.501 20.118a7.5 7.5 0 0 1 14.998 0A17.933 17.933 0 0 1 12 21.75c-2.676 0-5.216-.584-7.499-1.632Z" />
          </svg>
          <span class="text-lg font-bold">我的</span>
        </div>

        <div class="mt-8">
          <el-button color="#3b82f6" class="w-full text-white" @click="visible = true">
            <span class="font-medium tracking-wide">AI 模型助手</span>
          </el-button>
        </div>

        <el-drawer v-model="visible" :show-close="false" direction="rtl" size="400px" style="background-color: var(--el-bg-color);">
          <template #header="{ close, titleId, titleClass }">
            <div class="flex items-center justify-between w-full">
              <h4 :id="titleId" :class="titleClass" class="text-gray-900 dark:text-gray-100 font-bold text-lg m-0">AI助手</h4>
              <el-button link type="danger" @click="close" class="text-gray-500 hover:text-red-500 transition-colors">
                <el-icon class="text-xl"><CircleCloseFilled /></el-icon>
              </el-button>
            </div>
          </template>

          <div class="flex flex-col h-full bg-gray-50/50 dark:bg-gray-800/50 rounded-lg p-3">
            <div class="flex-1 overflow-y-auto p-2 text-gray-700 dark:text-gray-300 text-sm space-y-3">
              <div class="bg-white dark:bg-gray-800 p-3 rounded-xl shadow-sm border border-gray-100 dark:border-gray-700 w-fit">
                <p class="m-0">欢迎您的到来！我是智能助手，有什么可以帮您的吗？</p>
              </div>
            </div>
            <div class="pt-4 mt-2 flex gap-2">
              <el-input v-model="inputText" placeholder="请输入您的问题..." class="flex-1"></el-input>
              <el-button type="primary" @click="sendMessage">发送</el-button>
            </div>
          </div>
        </el-drawer>
      </div>

      <!-- Main Content: Blog Feed -->
      <div class="flex flex-col gap-5">
        <div v-for="blog in filteredBlogs" :key="blog.blog.id" 
             @click="router.push('/view/'+blog.blog.id)" 
             class="bg-white dark:bg-[#1f222b] border border-gray-100 dark:border-gray-800 rounded-xl p-5 shadow-sm hover:shadow-md hover:border-blue-100 dark:hover:border-gray-600 transition-all cursor-pointer">
             
          <h1 class="text-xl font-bold text-gray-900 dark:text-gray-100 mb-3 hover:text-blue-600 dark:hover:text-blue-400 transition-colors">
            {{ blog.blog.title }}
          </h1>
          
          <div class="flex gap-4">
            <div class="flex-shrink-0">
               <img class="w-16 h-16 rounded-xl object-cover border border-gray-100 dark:border-gray-700 shadow-sm bg-gray-50 dark:bg-gray-800" 
                    :src="blog.avator || defaultAvatar" 
                    @error="handleImageError" 
                    alt="avatar" />
            </div>
            <div class="flex-1 pt-1">
              <p class="text-gray-600 dark:text-gray-400 text-sm line-clamp-2 leading-relaxed m-0">
                {{ blog.blog.content }}
              </p>
            </div>
          </div>
          
          <div class="mt-4 flex items-center justify-between text-sm">
            <div class="flex items-center gap-2">
              <span class="bg-blue-50 dark:bg-blue-900/30 text-blue-600 dark:text-blue-400 px-2.5 py-1 rounded-md font-medium text-xs">
                @{{ blog.username }}
              </span>
            </div>
          </div>
        </div>
        
        <!-- Empty State mapping (Optional fallback if no blogs) -->
        <div v-if="!filteredBlogs || filteredBlogs.length === 0" class="text-center py-12 text-gray-500 dark:text-gray-400">
          暂无匹配的文章~
        </div>
      </div>
      
    </div>
  </div>
</template>

<style scoped>
/* Scoped styles can stay clean because Tailwind handles most styling */
</style>