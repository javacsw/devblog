<script setup>
import {onMounted, reactive} from "vue";
import {get} from "@/net/index.js";
import {useUserStore} from "@/stores/userStore.js";
import router from "@/router/index.js";

const options = reactive({
  blogs: [],
  currentUsername: ''
})

const defaultAvatar = 'https://api.dicebear.com/9.x/micah/svg?seed=Oreo&backgroundColor=f0db4f,ffdfbf'
const handleImageError = (e) => {
  e.target.src = defaultAvatar
}

const initUserData = () => {
  get('api/user/myinfo', {}, (message, data) => {
    useUserStore().login(data);
    options.currentUsername = data.username;
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
  <div class="max-w-[1200px] mx-auto px-4 lg:px-8 py-10 mt-2">
    <div class="grid grid-cols-1 md:grid-cols-[320px_1fr] gap-8">
      
      <!-- User Profile Sidebar -->
      <div class="bg-white dark:bg-[#1f222b] border border-gray-100 dark:border-gray-800 rounded-2xl p-8 shadow-sm flex flex-col items-center">
        <!-- Avatar Section -->
        <div class="relative w-40 h-40 mb-6 group cursor-pointer">
           <img class="w-full h-full rounded-full object-cover border-4 border-white dark:border-gray-700 shadow-lg bg-gray-50 dark:bg-gray-800 transition-transform duration-300 group-hover:scale-[1.03]" 
                :src="useUserStore().user?.avator || defaultAvatar" 
                @error="handleImageError" 
                alt="User Avatar" />
           <div class="absolute inset-0 bg-black/50 rounded-full flex flex-col items-center justify-center opacity-0 group-hover:opacity-100 transition-opacity duration-300">
             <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="w-6 h-6 text-white mb-1">
               <path stroke-linecap="round" stroke-linejoin="round" d="M6.827 6.175A2.31 2.31 0 0 1 5.186 7.23c-.38.054-.757.112-1.134.175C2.999 7.58 2.25 8.507 2.25 9.574V18a2.25 2.25 0 0 0 2.25 2.25h15A2.25 2.25 0 0 0 21.75 18V9.574c0-1.067-.75-1.994-1.802-2.169a47.865 47.865 0 0 0-1.134-.175 2.31 2.31 0 0 1-1.64-1.055l-.822-1.316a2.192 2.192 0 0 0-1.736-1.039 48.774 48.774 0 0 0-5.232 0 2.192 2.192 0 0 0-1.736 1.039l-.821 1.316Z" />
               <path stroke-linecap="round" stroke-linejoin="round" d="M16.5 12.75a4.5 4.5 0 1 1-9 0 4.5 4.5 0 0 1 9 0ZM18.75 10.5h.008v.008h-.008V10.5Z" />
             </svg>
             <span class="text-white text-sm font-medium">更换头像</span>
           </div>
        </div>
        
        <h2 class="text-2xl font-bold text-gray-900 dark:text-gray-100 mb-2 truncate max-w-full">
          {{ options.currentUsername || useUserStore().user?.username || '未登录用户' }}
        </h2>
        
        <div class="flex items-center gap-2 mb-6 text-sm text-gray-500 dark:text-gray-400">
          <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="w-4 h-4">
            <path stroke-linecap="round" stroke-linejoin="round" d="M9 12.75 11.25 15 15 9.75M21 12a9 9 0 1 1-18 0 9 9 0 0 1 18 0Z" />
          </svg>
          <span v-if="useUserStore().user?.role === 'admin'">管理员</span>
          <span v-else>普通创作者</span>
        </div>

        <div class="w-full h-px bg-gray-100 dark:bg-gray-800 my-2"></div>

        <button class="w-full py-3 mt-4 bg-blue-50 text-blue-600 dark:bg-blue-900/40 dark:text-blue-400 font-semibold rounded-xl hover:bg-blue-100 dark:hover:bg-blue-800/60 active:scale-95 transition-all flex items-center justify-center gap-2">
          <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="w-5 h-5">
            <path stroke-linecap="round" stroke-linejoin="round" d="m16.862 4.487 1.687-1.688a1.875 1.875 0 1 1 2.652 2.652L10.582 16.07a4.5 4.5 0 0 1-1.897 1.13L6 18l.8-2.685a4.5 4.5 0 0 1 1.13-1.897l8.932-8.931Zm0 0L19.5 7.125M18 14v4.75A2.25 2.25 0 0 1 15.75 21H5.25A2.25 2.25 0 0 1 3 18.75V8.25A2.25 2.25 0 0 1 5.25 6H10" />
          </svg>
          编辑个人资料
        </button>
      </div>

      <!-- User Info Main Section -->
      <div class="bg-white dark:bg-[#1f222b] border border-gray-100 dark:border-gray-800 rounded-2xl p-8 shadow-sm">
        <h1 class="text-2xl font-bold text-gray-900 dark:text-gray-100 mb-8 flex items-center gap-3">
          <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="2" stroke="currentColor" class="w-7 h-7 text-blue-500">
            <path stroke-linecap="round" stroke-linejoin="round" d="M15.75 6a3.75 3.75 0 1 1-7.5 0 3.75 3.75 0 0 1 7.5 0ZM4.501 20.118a7.5 7.5 0 0 1 14.998 0A17.933 17.933 0 0 1 12 21.75c-2.676 0-5.216-.584-7.499-1.632Z" />
          </svg>
          个人信息
        </h1>
        
        <div class="space-y-6">
          <div class="grid grid-cols-1 md:grid-cols-2 gap-6">
            
            <!-- User Name Info Block -->
            <div class="bg-gray-50 dark:bg-gray-800/40 rounded-2xl p-6 border border-gray-100 dark:border-gray-700/50 hover:border-blue-100 dark:hover:border-gray-600 transition-colors">
              <span class="text-sm text-gray-500 dark:text-gray-400 font-medium block mb-2 flex items-center gap-2">
                <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="w-4 h-4">
                  <path stroke-linecap="round" stroke-linejoin="round" d="M15.75 6a3.75 3.75 0 1 1-7.5 0 3.75 3.75 0 0 1 7.5 0ZM4.501 20.118a7.5 7.5 0 0 1 14.998 0A17.933 17.933 0 0 1 12 21.75c-2.676 0-5.216-.584-7.499-1.632Z" />
                </svg>
                显示名称
              </span>
              <div class="text-[1.1rem] font-semibold text-gray-900 dark:text-gray-100">
                {{ options.currentUsername || useUserStore().user?.username || '未设置' }}
              </div>
            </div>

            <!-- Email Info Block -->
            <div class="bg-gray-50 dark:bg-gray-800/40 rounded-2xl p-6 border border-gray-100 dark:border-gray-700/50 hover:border-blue-100 dark:hover:border-gray-600 transition-colors">
              <span class="text-sm text-gray-500 dark:text-gray-400 font-medium block mb-2 flex items-center gap-2">
                <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="w-4 h-4">
                  <path stroke-linecap="round" stroke-linejoin="round" d="M21.75 6.75v10.5a2.25 2.25 0 0 1-2.25 2.25h-15a2.25 2.25 0 0 1-2.25-2.25V6.75m19.5 0A2.25 2.25 0 0 0 19.5 4.5h-15a2.25 2.25 0 0 0-2.25 2.25m19.5 0v.243a2.25 2.25 0 0 1-1.07 1.916l-7.5 4.615a2.25 2.25 0 0 1-2.36 0L3.32 8.91a2.25 2.25 0 0 1-1.07-1.916V6.75" />
                </svg>
                联系邮箱
              </span>
              <div class="text-[1.1rem] font-semibold text-gray-900 dark:text-gray-100">
                {{ useUserStore().user?.email || '暂无填写' }}
              </div>
            </div>
            
            <!-- Bio Info Block -->
            <div class="bg-gray-50 dark:bg-gray-800/40 rounded-2xl p-6 border border-gray-100 dark:border-gray-700/50 hover:border-blue-100 dark:hover:border-gray-600 transition-colors md:col-span-2">
              <span class="text-sm text-gray-500 dark:text-gray-400 font-medium block mb-3 flex items-center gap-2">
                <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="w-4 h-4">
                  <path stroke-linecap="round" stroke-linejoin="round" d="M3.75 6.75h16.5M3.75 12h16.5m-16.5 5.25H12" />
                </svg>
                个人介绍
              </span>
              <div class="text-base text-gray-700 dark:text-gray-300 leading-relaxed min-h-[80px]">
                {{ useUserStore().user?.intro || '这个人很酷，什么都没有留下。' }}
              </div>
            </div>
            
            <!-- Organization Info Block -->
            <div class="bg-gray-50 dark:bg-gray-800/40 rounded-2xl p-6 border border-gray-100 dark:border-gray-700/50 hover:border-blue-100 dark:hover:border-gray-600 transition-colors md:col-span-2">
              <span class="text-sm text-gray-500 dark:text-gray-400 font-medium block mb-2 flex items-center gap-2">
                <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="w-4 h-4">
                  <path stroke-linecap="round" stroke-linejoin="round" d="M2.25 21h19.5m-18-18v18m10.5-18v18m6-13.5V21M6.75 6.75h.75m-.75 3h.75m-.75 3h.75m3-6h.75m-.75 3h.75m-.75 3h.75M6.75 21v-3.375c0-.621.504-1.125 1.125-1.125h2.25c.621 0 1.125.504 1.125 1.125V21M3 3h12m-.75 4.5H21m-3.75 3.75h.008v.008h-.008v-.008Zm0 3h.008v.008h-.008v-.008Zm0 3h.008v.008h-.008v-.008Z" />
                </svg>
                组织
              </span>
              <div class="text-[1.1rem] font-semibold text-gray-900 dark:text-gray-100">
                {{ useUserStore().user?.org || '独立开发者' }}
              </div>
            </div>

          </div>
        </div>
      </div>
      
    </div>
  </div>
</template>

<style scoped>
/* 样式通过 Tailwind CSS 处理，保持文件极简与现代 */
</style>