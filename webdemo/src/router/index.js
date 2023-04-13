import Vue from 'vue';
import VueRouter from 'vue-router';

Vue.use(VueRouter);
import Login from '@/pages/Common/Login'
import Register from '@/pages/Common/Register'
import Search from '@/pages/Student/Search'
import ExamPaper from '@/pages/Student/ExamPaper'
import Answer from '@/pages/Student/Answer'
import Practice from '@/pages/Student/Practice'
import WrongBook from '@/pages/Student/WrongBook'
import MyExam from '@/pages/Student/MyExam'
import Phonecheck from '@/pages/Common/Phonecheck' 
import ExamMsg from '@/pages/Student/ExamMsg'
import ScoreTable from '@/pages/Student/ScoreTable'
import AnswerScore from '@/pages/Student/AnswerScore'
import AdminTable from '@/pages/Admin/index'
import StudentManage from '@/pages/Admin/StudentManage'
import ProblemManage from '@/pages/Admin/ProblemManage'
import AddProblem from '@/pages/Admin/AddProblem'
import ProblemSet from '@/pages/Student/ProblemSet'
import Problem from '@/pages/Student/Problem'
import UserInfo from '@/pages/Common/UserInfo'
import AccountInfo from '@/pages/Common/AccountInfo'
import ChangePassword from '@/pages/Common/ChangePassword'
import ChangePhoneNumber from '@/pages/Common/ChangePhoneNumber'
import ChangeEmail from '@/pages/Common/ChangeEmail'
import SearchExam from '@/pages/Admin/SearchExam'
import AddExam from '@/pages/Admin/AddExam'
import TeacherManage from '@/pages/Admin/TeacherManage'
import AddTeacher from '@/pages/Admin/AddTeacher'
export default new VueRouter ({
    //配置路由
    routes:[
        {
            path: "/login",
            component: Login,
            meta: {show:true}
        },
        {
            path: "/register",
            component: Register,
            meta: {show:true}
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
            meta: {requiresAuth: true, show:true},
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
        {
            path: "/phonecheck",
            component: Phonecheck,
            meta: {show:true}
        }, 
        {
            path: "/examMsg",
            component: ExamMsg,
            meta: {requiresAuth: true, show:true}
        }, 
        {
            path: "/scoreTable",
            component: ScoreTable,
            meta: {requiresAuth: true, show:true}
        },
        {
            path: "/answerScore",
            component: AnswerScore,
            meta: {requiresAuth: true, show:true}
        },
        {
            path: "/problemSet",
            component: ProblemSet,
            meta: {show:true}
        },
        {
            path: "/problem",
            component: Problem,
            meta: {requiresAuth: true, show:true}
        },
        {
            path: "/adminTable",
            component: AdminTable,
            meta: {requiresAuth: true, show:false},
            children: [
                {
                    path: "/studentManage",
                    component: StudentManage,
                    meta: {requiresAuth: true, show:false}
                },
                {
                    path: "/teacherManage",
                    component: TeacherManage,
                    meta: {requiresAuth: true, show:false}
                },
                {
                    path: "/problemManage",
                    component: ProblemManage,
                    meta: {requiresAuth: true, show:false}
                },
                {
                    path: "/addProblem",
                    component: AddProblem,
                    meta: {requiresAuth: true, show:false}
                },
                {
                    path: "/searchExam",
                    component: SearchExam,
                    meta: {requiresAuth: true, show:false}
                },
                {
                    path: "/addExam",
                    component: AddExam,
                    meta: {requiresAuth: true, show:false}
                },
                {
                    path: "/addTeacher",
                    component: AddTeacher,
                    meta: {requiresAuth: true, show:false}
                },
            ]
        },
        {
            path: "/userInfo",
            component: UserInfo,
            mate: {requiresAuth: true, show:true}
        },
        {
            path: "/accountInfo",
            component: AccountInfo,
            mate: {requiresAuth: true, show:true}
        },
        {
            path: "/accountInfo/password",
            component: ChangePassword,
            mate: {requiresAuth: true, show:true}
        },
        {
            path: "/accountInfo/phoneNumber",
            component: ChangePhoneNumber,
            mate: {requiresAuth: true, show:true}
        },
        {
            path: "/accountInfo/email",
            component: ChangeEmail,
            mate: {requiresAuth: true, show:true}
        },
    ]
})
