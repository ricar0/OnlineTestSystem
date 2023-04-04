import Vue from 'vue'
import App from './App.vue'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import router from '@/router';
import store from '@/store'
import Cookies from 'js-cookie'

Vue.config.productionTip = false
Vue.use(ElementUI);
Vue.use(Cookies)
ElementUI.TableColumn.props.showOverflowTooltip ={type:Boolean, default: true}

new Vue({
  render: h => h(App),
  router,
  store,
}).$mount('#app')

import {getToken} from '@/utils/token'
router.beforeEach((to,from,next)=>{
  const isAuthenticated = getToken()
  console.log(to)
  if (to.meta.requiresAuth && !isAuthenticated) {
    next('/login')
  } else {
    next()
  }
})
