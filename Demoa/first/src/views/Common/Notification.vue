<script setup>
import {onMounted, reactive} from "vue";
import {get,post} from "@/net/index.js"
import {formatDate} from "../../util/DateFormattor.js";
import {message} from "ant-design-vue";
onMounted(() => {
  fetchNotificationsByType(0)
})
const [messageApi,contextHolder]=message.useMessage();
const readNotification=(id)=>{
  post('/api/user/readNotification',{
    id:id
  },(message)=>{
      messageApi.success(message);
  },(message)=>{
      messageApi.warning(message);
  })
}
const clearNotification=(type)=>{
  post('/api/user/clearNotification',{
    type:type
  },(message)=>{
    messageApi.success(message);
  },(message)=>{
    messageApi.warning(message);
  })
}

const options = reactive({
  selected:{
    notificationType:1
  },currentNotifications:[]
})

const buttonClickHandler=(buttonId)=>{
  options.selected.notificationType = buttonId
  fetchNotificationsByType(buttonId-1)
}
const fetchNotificationsByType=(type)=>{
  get('/api/user/getNotifications',{
    type:type
  },(message,data)=>{
    options.currentNotifications=data
  })
}
const sideBarObjects=[
  {
    typeId: 0,
    name:"点赞",
    svg:`<svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="size-7">
  <path stroke-linecap="round" stroke-linejoin="round" d="M6.633 10.25c.806 0 1.533-.446 2.031-1.08a9.041 9.041 0 0 1 2.861-2.4c.723-.384 1.35-.956 1.653-1.715a4.498 4.498 0 0 0 .322-1.672V2.75a.75.75 0 0 1 .75-.75 2.25 2.25 0 0 1 2.25 2.25c0 1.152-.26 2.243-.723 3.218-.266.558.107 1.282.725 1.282m0 0h3.126c1.026 0 1.945.694 2.054 1.715.045.422.068.85.068 1.285a11.95 11.95 0 0 1-2.649 7.521c-.388.482-.987.729-1.605.729H13.48c-.483 0-.964-.078-1.423-.23l-3.114-1.04a4.501 4.501 0 0 0-1.423-.23H5.904m10.598-9.75H14.25M5.904 18.5c.083.205.173.405.27.602.197.4-.078.898-.523.898h-.908c-.889 0-1.713-.518-1.972-1.368a12 12 0 0 1-.521-3.507c0-1.553.295-3.036.831-4.398C3.387 9.953 4.167 9.5 5 9.5h1.053c.472 0 .745.556.5.96a8.958 8.958 0 0 0-1.302 4.665c0 1.194.232 2.333.654 3.375Z" />
</svg>
`, id : 1
  },  {
    typeId: 1,
    name:"关注",
    svg:`<svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="size-7">
  <path stroke-linecap="round" stroke-linejoin="round" d="M18 7.5v3m0 0v3m0-3h3m-3 0h-3m-2.25-4.125a3.375 3.375 0 1 1-6.75 0 3.375 3.375 0 0 1 6.75 0ZM3 19.235v-.11a6.375 6.375 0 0 1 12.75 0v.109A12.318 12.318 0 0 1 9.374 21c-2.331 0-4.512-.645-6.374-1.766Z" />
</svg>
`, id : 2
  },  {
    typeId: 2,
    name:"评论",
    svg:`<svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="size-7">
  <path stroke-linecap="round" stroke-linejoin="round" d="M7.5 8.25h9m-9 3H12m-9.75 1.51c0 1.6 1.123 2.994 2.707 3.227 1.129.166 2.27.293 3.423.379.35.026.67.21.865.501L12 21l2.755-4.133a1.14 1.14 0 0 1 .865-.501 48.172 48.172 0 0 0 3.423-.379c1.584-.233 2.707-1.626 2.707-3.228V6.741c0-1.602-1.123-2.995-2.707-3.228A48.394 48.394 0 0 0 12 3c-2.392 0-4.744.175-7.043.513C3.373 3.746 2.25 5.14 2.25 6.741v6.018Z" />
</svg>
`, id : 3
  },  {
    typeId: 3,
    name:"系统",
    svg:`<svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="size-7">
  <path stroke-linecap="round" stroke-linejoin="round" d="M9 17.25v1.007a3 3 0 0 1-.879 2.122L7.5 21h9l-.621-.621A3 3 0 0 1 15 18.257V17.25m6-12V15a2.25 2.25 0 0 1-2.25 2.25H5.25A2.25 2.25 0 0 1 3 15V5.25m18 0A2.25 2.25 0 0 0 18.75 3H5.25A2.25 2.25 0 0 0 3 5.25m18 0V12a2.25 2.25 0 0 1-2.25 2.25H5.25A2.25 2.25 0 0 1 3 12V5.25" />
</svg>
`, id : 4
  }
]

</script>

<template>
  <contextHolder/>
  <br><br>
<div class="h-screen w-3/4 mx-auto  grid grid-cols-[1.5fr,6fr] gap-2 ">
  <div class="flex-col p-2 bg-gray-50 rounded-xl basic-color">
    <div class="flex flex-nowrap select-none">
      <div style="width: 350px; height: 50px; line-height: 50px; border: 2px; text-align: center; display: flex; justify-content: center; align-items: center;">
        <span class="text-2xl" style="font-family:Simsun">
          消息中心
        </span>
        <button class="ml-2" @click="clearNotification(options.selected.notificationType-1)">
          <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="size-6">
            <path stroke-linecap="round" stroke-linejoin="round" d="M6.72 13.829c-.24.03-.48.062-.72.096m.72-.096a42.415 42.415 0 0 1 10.56 0m-10.56 0L6.34 18m10.94-4.171c.24.03.48.062.72.096m-.72-.096L17.66 18m0 0 .229 2.523a1.125 1.125 0 0 1-1.12 1.227H7.231c-.662 0-1.18-.568-1.12-1.227L6.34 18m11.318 0h1.091A2.25 2.25 0 0 0 21 15.75V9.456c0-1.081-.768-2.015-1.837-2.175a48.055 48.055 0 0 0-1.913-.247M6.34 18H5.25A2.25 2.25 0 0 1 3 15.75V9.456c0-1.081.768-2.015 1.837-2.175a48.041 48.041 0 0 1 1.913-.247m10.5 0a48.536 48.536 0 0 0-10.5 0m10.5 0V3.375c0-.621-.504-1.125-1.125-1.125h-8.25c-.621 0-1.125.504-1.125 1.125v3.659M18 10.5h.008v.008H18V10.5Zm-3 0h.008v.008H15V10.5Z" />
          </svg>
        </button>
      </div>
    </div>

    <br>
    <div :class="options.selected.notificationType === sbo.id?'bg-[rgb(230,231,235)] dark:bg-[rgb(25,24,20)]':''"
        @click="buttonClickHandler(sbo.id)" v-for="sbo in sideBarObjects" class="flex flex-nowrap clickEffect1 p-2 rounded-xl mx-auto w-full select-none cursor-pointer justify-center items-center " >
      <div v-html="sbo.svg" class="mr-2"></div>
      <span class="text-2xl" style="font-family:Simsun">{{ sbo.name }}</span>
    </div>
  </div>

  <div class="bg-gray-50 rounded-xl basic-color">
    <div v-for="noti in options.currentNotifications"
    class="border-[1px] mt-2 p-2 rounded-xl select-none border-gray-400">
      <h1 class="text-xl" style="font-family:Simsun">{{noti.title}}</h1>
      <br>
      <span style="font-family:Simsun">{{noti.content}}</span>
      <br>
      <div  style="font-family:Simsun;color:gray">{{formatDate(noti.createTime)}}</div>
      <br>
      <span @click="readNotification(noti.id);noti.isRead=true" v-if="!noti.isRead" class="hover:underline cursor-pointer hover:text-blue-500 font-bold" style="font-family:Simsun">
        点击已读
      </span>
    </div>
  </div>

</div>
</template>

<style scoped>

</style>