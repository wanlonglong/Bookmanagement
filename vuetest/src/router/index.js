import Vue from 'vue'
import VueRouter from 'vue-router'
import BookManage from '../views/BookManage'
import AddBook from '../views/AddBook'
import Index from '../views/Index'
import BookUpdate from '../views/BookUpdate'
import FindBook from '../views/FindBook'

Vue.use(VueRouter)

const routes = [
  {
    path:"/",
    name:"BookManage",
    component:Index,
    show:true,
    redirect:"/BookManage",
    children:[
      {
        path:"/BookManage",
        name:"Show books",
        component:BookManage
      },
      {
        path:"/AddBook",
        name:"Add book",
        component:AddBook
      },
      {
        path:"/FindBook",
            name:"Find book",
          component:FindBook
      }
    ]
  },
  {
    path:'/update',
    component:BookUpdate,
    show:false
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
