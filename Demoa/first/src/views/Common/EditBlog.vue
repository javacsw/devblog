<script setup>
import {reactive, ref} from 'vue';
import { MdEditor } from 'md-editor-v3';
import 'md-editor-v3/lib/style.css';
import {useThemeStore} from "@/stores/theme.js";
import {post} from "@/net/index.js";
import {message} from "ant-design-vue";
const [messageApi, contextHolder] = message.useMessage();

const themeStore=useThemeStore();
const blogForm=reactive({
  title:'',
  content:''
})
const submitHandler = () => {
  post('/api/blog/addNew', {
    title: blogForm.title,
    content: blogForm.content
  }, (message) => {
    messageApi.success(message);
  }, (message) => {
    messageApi.warning(message)
  }, (message) => {
    messageApi.error(message)
  })
}

</script>

<template>
  <contextHolder/>
  <div class="w-11/12 h-screen mx-auto">
    <div>
      <span>标题:</span>
      <input v-model="blogForm.title" class="animatedInput w-full">
    </div>
    <MdEditor height="900px" v-model="blogForm.content" :theme="themeStore.currentTheme" :toolbarsExclude="['github']"/>
    <div class="w-full flex justify-center text-center mt-6 mb-12">
      <button @click="submitHandler" class="bg-blue-600 dark:bg-blue-500 flex items-center gap-2 px-8 py-2.5 rounded-full text-white hover:bg-blue-700 dark:hover:bg-blue-600 active:bg-blue-800 transition-all shadow-md hover:shadow-lg font-medium tracking-wide">
        <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="2" stroke="currentColor" class="w-5 h-5">
          <path stroke-linecap="round" stroke-linejoin="round" d="m16.862 4.487 1.687-1.688a1.875 1.875 0 1 1 2.652 2.652L10.582 16.07a4.5 4.5 0 0 1-1.897 1.13L6 18l.8-2.685a4.5 4.5 0 0 1 1.13-1.897l8.932-8.931Zm0 0L19.5 7.125M18 14v4.75A2.25 2.25 0 0 1 15.75 21H5.25A2.25 2.25 0 0 1 3 18.75V8.25A2.25 2.25 0 0 1 5.25 6H10" />
        </svg>
        提交发布
      </button>
    </div>
  </div>
</template>

<style scoped>

</style>