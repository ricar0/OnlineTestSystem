import Vue from 'vue'
import App from './App.vue'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import router from '@/router';
import store from '@/store'
import Cookies from 'js-cookie'
import axios from 'axios'
import  * as  echarts from 'echarts'
Vue.prototype.$echarts = echarts;

Vue.prototype.$axios = axios
Vue.config.productionTip = false
Vue.use(ElementUI);
Vue.use(Cookies)
ElementUI.TableColumn.props.showOverflowTooltip ={type:Boolean, default: true}

new Vue({
  render: h => h(App),
  router,
  store,
}).$mount('#app')

import {getToken,getRole} from '@/utils/token'
router.beforeEach((to,from,next)=>{
  const isAuthenticated = getToken()
  const role = getRole()
  if (to.meta.requiresAuth && !isAuthenticated) {
    next('/login')
  } else if (to.meta.role == 'teacher' && role == 'student') {
    next('/forbidden')
  } else if (to.meta.role == 'admin' && role != 'admin') {
    next('/forbidden')
  } else {
    next()
  }
})
