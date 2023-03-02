import Vue from 'vue';
import VueRouter from 'vue-router';

Vue.use(VueRouter);
import Home from '@/pages/Home'
import Login from '@/pages/Login'
import Register from '@/pages/Register'
import Search from '@/pages/Search'
import ExamPaper from '@/pages/Student/ExamPaper'
import Answer from '@/pages/Student/Answer'
import Practice from '@/pages/Student/Practice'
import WrongBook from '@/pages/Student/WrongBook'
import MyExam from '@/pages/Student/MyExam'
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
        {
            path: "/exampaper",
            component: ExamPaper,
            meta: {show:true},
        },
        {
            path: "/answer",
            component: Answer,
            meta: {show:true},
        },
        {
            path: "/practice",
            component: Practice,
            meta: {show:true}
        },
        {
            path: "/wrongbook",
            component: WrongBook,
            meta: {show:true}
        }, 
        {
            path: "/myexam",
            component: MyExam,
            meta: {show:true}
        }, 
    ]
})
