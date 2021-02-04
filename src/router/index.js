import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import Register from "../views/Register";
import Accounts from "../views/Accounts";
import Transactions from "../views/Transactions";
import Reports from "../views/Reports";

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
//  {
//    path: '/about',
//    name: 'About',
//    // route level code-splitting
//    // this generates a separate chunk (about.[hash].js) for this route
//    // which is lazy-loaded when the route is visited.
//   component: function () {
//    return import(/* webpackChunkName: "about" */ '../views/About.vue')
//  }
//  }    ,
{
    path:'/register',
    name:'Register',
    component: Register
  },{
    path:'/accounts',
    name:'Accounts',
    component: Accounts
  },{
    path:'/transactions',
    name:'Transactions',
    component: Transactions
  },{
    path:'/reports',
    name:'Reports',
    component: Reports
  }
]

const router = new VueRouter({
  routes
})

export default router
