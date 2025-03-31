import '@/assets/CSS/main.css'
import { createApp } from 'vue'
import { createPinia } from 'pinia'

import App from './App.vue'
import router from './router'


import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import axios from "axios";

//你调用人家的组件库 为啥不引入呢？？？？
import Antd from 'ant-design-vue';
import 'ant-design-vue/dist/reset.css';

const app = createApp(App)

axios.defaults.baseURL = 'http://localhost:8080'

// axios.defaults.baseURL = 'http://101.37.161.247/api'

axios.defaults.withCredentials = true

//后端基础url 之后在请求时只用填写路径 Axios会自动以该url为基础添加路径
app.config.globalProperties.$axios = axios
//应用axios配置

app.use(createPinia())
app.use(router).use(ElementPlus).use(Antd)

app.mount('#app')
