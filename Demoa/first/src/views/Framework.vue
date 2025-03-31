<script setup>
import ThemeSwitch from "@/components/Auth/ThemeSwitch.vue";
import router from "@/router/index.js";
import {useUserStore} from "@/stores/userStore.js";
import {onMounted} from "vue";
import {get} from "@/net/index.js";
const initUserData = () => {
  get('api/user/myinfo', {},
      (message,data) => {
        useUserStore().login(data);
      }
  )
}
onMounted(() => {
  initUserData()
})
</script>

<template>
  <nav class="basic-color fixed top-0 left-0 w-full shadow-md z-10 shadow-gray-200 dark:bg-gray-800">
    <div class="container mx-auto flex justify-between items-center py-4 px-6">
      <!-- 左侧：网站logo -->
      <div @click="router.push('/')" class="flex items-center cursor-pointer">
        <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="size-6">
          <path stroke-linecap="round" stroke-linejoin="round" d="m2.25 12 8.954-8.955c.44-.439 1.152-.439 1.591 0L21.75 12M4.5 9.75v10.125c0 .621.504 1.125 1.125 1.125H9.75v-4.875c0-.621.504-1.125 1.125-1.125h2.25c.621 0 1.125.504 1.125 1.125V21h4.125c.621 0 1.125-.504 1.125-1.125V9.75M8.25 21h8.25" />
        </svg>
      </div>

      <!-- 右侧：用户头像 -->
      <div class="flex items-center gap-2">
        <!--  加上了 :  -->
        <ThemeSwitch/>
<!--        改成不固定的会找不到-->
        <div @click="router.push('/mycentent')" class="cursor-pointer">
          <img v-if="useUserStore().user!== null" :src="useUserStore().user.avator" alt="User Avatar" class="h-8 rounded-full"/>
        </div>
        <div @click="router.push('/auth')" class="flex items-center cursor-pointer">
          <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="size-6">
            <path stroke-linecap="round" stroke-linejoin="round" d="M15.75 9V5.25A2.25 2.25 0 0 0 13.5 3h-6a2.25 2.25 0 0 0-2.25 2.25v13.5A2.25 2.25 0 0 0 7.5 21h6a2.25 2.25 0 0 0 2.25-2.25V15M12 9l-3 3m0 0 3 3m-3-3h12.75" />
          </svg>
        </div>
      </div>

    </div>
  </nav>

  <main class="basic-color mt-16">
    <router-view/>
  </main>
</template>


<style scoped>
/* 你可以在这里添加一些自定义样式 */
</style>