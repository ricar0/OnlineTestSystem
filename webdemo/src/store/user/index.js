//user模块小仓库
import {reqUserLogin} from '@/api'
const state={
    token:'',
};
const mutations = {
    USERLOGIN(state, token) {
        state.token = token;
    }
};
const actions = {
    //登录业务
    async userLogin({commit}, obj) {
        let {data} = await reqUserLogin(obj);
        console.log(data.msg);
        if (data.code == 200) {
            commit("USERLOGIN", data.msg);
            return "ok";
        } else {
            return Promise.reject(new Error("failure"));
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