import Vue from 'vue';
import VueRouter from 'vue-router';

Vue.use(VueRouter);
import Home from '@/pages/Home'
import Login from '@/pages/Login'
import Register from '@/pages/Register'
import Search from '@/pages/Search'
export default new VueRouter ({
    //配置路由
    routes:[
        {
            path: "/",
            component: Home,
            meta: {show:true}
        },
        {
            path: "/login",
            component: Login,
            meta: {show:false}
        },
        {
            path: "/register",
            component: Register,
            meta: {show:false}
        },
        {
            path: "/search/:keywords",
            component: Search,
            meta: {show:true},
            name: 'search'
        },
    ]
})
