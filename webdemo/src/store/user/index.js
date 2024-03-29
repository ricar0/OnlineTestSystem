//user模块小仓库
import {reqUserLogin, reqUserInfo, reqUserLogout, reqSendCode, reqGetCode, reqGetStudentAll, 
    reqGetUserInfoById, reqUpdateUserInfo, reqUpdatePassword, reqSendEmail, reqVerifyEmail, reqGetTeacherAll, reqVerifyEmailByCode, reqUpdatePasswordByEmail, reqAddUser} from '@/api'
import {setToken, getToken, removeToken} from '@/utils/token'
const state={
    token: getToken(),
    userinfo:{},
    phone:'',
    student: [],
    user: '',
    teacher: []
};
const mutations = {
    USERLOGIN(state, token) {
        state.token = token;
    },
    GETUSERINFO(state, userinfo) {
        state.userinfo = userinfo;
    },
    USERLOGOUT(state) {
        state.token = '';
        state.userinfo = {};
        removeToken();
    },
    SENDCODE(state, phone) {
        state.phone = phone;
    },
    GETSTUDENTALL(state, student) {
        state.student = student;
    },
    GETUSERINFOBYID(state, user) {
        state.user = user;
    },
    GETTEACHERALL(state, teacher) {
        state.teacher = teacher
    }
};
const actions = {
    //登录业务
    async userLogin({commit}, obj) {
        removeToken()
        let {data} = await reqUserLogin(obj);
        if (data.code == 200) {
            commit("USERLOGIN", data.data.token);
            setToken(data.data.token);
            return "ok";
        } else {
            return data.msg;
        }
    },
    //获取用户信息
    async getUserInfo({commit}) {
        let {data} = await reqUserInfo();
        if (data.code == 200) {
            commit("GETUSERINFO", data.data.user);
            return "ok";
        } else {
            return Promise.reject(new Error("failure"));
        }
    },
    //退出登录
    async userLogout({commit}) {
        let {data} = await reqUserLogout();
        if (data.code == 200) {
            commit("USERLOGOUT");
            removeToken()
        } else {
            return data.msg;
        }
    },
    //获取验证码
    async sendCode({commit}, obj) {
        let {data} = await reqSendCode(obj);
        if (data.code == 200) {
            commit("SENDCODE", obj.phone);
            return "ok";
        } else {
            return data.msg;
        }
    },
    //提交验证码
    async getCode({commit}, obj) {
        let {data} = await reqGetCode(obj);
        if (data.code == 200) {
            return "ok";
        } else {
            return data.msg;
        }
    },
    async getStudentAll({commit}, obj) {
        let {data} = await reqGetStudentAll(obj);
        commit("GETSTUDENTALL", data.data);
        if (data.code == 200) {
            return "ok";
        } else {
            return data.msg;
        }
    },
    async getTeacherAll({commit}, obj) {
        let {data} = await reqGetTeacherAll(obj);
        commit("GETTEACHERALL", data.data);
        if (data.code == 200) {
            return "ok";
        } else {
            return data.msg;
        }
    },
    async getUserInfoById({commit}, obj) {
        let {data} = await reqGetUserInfoById(obj);
        commit("GETUSERINFOBYID", data.data);
        if (data.code == 200) {
            return "ok";
        } else {
            return data.msg;
        }
    },
    async updateUserInfo({commit}, obj) {
        let {data} = await reqUpdateUserInfo(obj);
        if (data.code == 200) {
            return "ok";
        } else {
            return data.msg;
        }
    },
    async updatePassword({commit}, obj) {
        let {data} = await reqUpdatePassword(obj);
        if (data.code == 200) {
            return "ok";
        } else {
            return data.msg;
        }
    },
    async sendEmail({commit}, obj) {
        let {data} = await reqSendEmail(obj);
        if (data.code == 200) {
            return "ok";
        } else {
            return data.msg;
        }
    },
    async verifyEmail({commit}, obj) {
        let {data} = await reqVerifyEmail(obj);
        if (data.code == 200) {
            return "ok";
        } else {
            return 'error';
        }
    },
    async verifyEmailByCode({commit}, obj) {
        let {data} = await reqVerifyEmailByCode(obj);
        if (data.code == 200) {
            return "ok";
        } else {
            return 'error';
        }
    },
    async updatePasswordByEmail({commit}, obj) {
        let {data} = await reqUpdatePasswordByEmail(obj);
        if (data.code == 200) {
            return "ok";
        } else {
            return 'error';
        }
    },
    async addUser({commit}, obj) {
        let {data} = await reqAddUser(obj);
        if (data.code == 200) {
            return "ok";
        } else {
            return 'error';
        }
    },
};
const getters = {};
export default {
    state,
    mutations,
    actions,
    getters
}