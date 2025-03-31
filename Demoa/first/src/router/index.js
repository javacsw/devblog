import { createRouter, createWebHistory } from 'vue-router'
import {useUserStore} from "@/stores/userStore.js";
import {get} from "@/net/index.js"

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      name:'登入',
      path:'/auth',
      meta:{
        title:'验证页面',
      },
      component:()=>import('@/views/Auth/Auth.vue')
    },
    {
      name:'framework',
      path:'/',
      component:()=>import('@/views/Framework.vue'),
      children: [
        {
          name: 'index',
          path: '/',
          component: () => import('@/views/Common/Index.vue')
        },
        {
          name:'editBlog',
          path:'/new',
          component:()=> import('@/views/Common/EditBlog.vue')
        } ,{
          name:'ViewBlog',
          path:'view/:id',
          component:()=> import('@/views/Common/ViewBlog.vue')
        }, {
          name:'notification',
          path:'/notification',
          component:()=> import('@/views/Common/Notification.vue')
        },{
          name:'mycentent',
          path:'mycentent',
          component:()=>import('@/views/Common/Mycentent.vue')
        },{
          name:'collection',
          path:'collection',
          component:()=>import('@/views/Common/Collection.vue')
        }
      ]
    }
  ]
})

router.beforeEach((to,from,next)=>{
  const userStore = useUserStore()
  const publicMap = new Map()
  publicMap.set('/', 1)
  publicMap.set('/auth', 2)
  publicMap.set('/test', 3)
  publicMap.set('/error/401', 4)
  publicMap.set('/error/404', 5)
  // 检查要访问的路径是否是根路径
  if (to.matched.length === 0) next('error/404')
  if (to.meta.title) {
    document.title = 'MetaMind'
  } else {
    document.title = to.meta.title
  }
  if (!publicMap.has(to.path)) {
    // 不是访问根路径，检查用户状态
    const user = userStore.user; // 假设你的用户状态保存在Vuex的`user`状态中
    if (user === null) {
      get('api/user/myinfo', {},
          (message,data) => {
            userStore.login(data);
            next();
          }, () => {
            next('/auth'); // 3秒后重定向到根路径
          }, () => {
            next('/auth'); // 3秒后重定向到根路径
          }
      )
    } else {
      // 用户已登录，允许路由继续
      next();
    }
  } else {
    // 访问的是根路径，直接放行
    next();
  }

})

export default router
