//user模块小仓库
import {reqUserLogin, reqUserInfo, reqUserLogout, reqSendCode, reqGetCode} from '@/api'
import {setToken, getToken, removeToken} from '@/utils/token'
const state={
    token: getToken(),
    userinfo:{},
    phone:'',
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
    }
};
const actions = {
    //登录业务
    async userLogin({commit}, obj) {
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
};
const getters = {};
export default {
    state,
    mutations,
    actions,
    getters
}