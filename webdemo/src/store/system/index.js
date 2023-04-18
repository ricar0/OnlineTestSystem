
//role模块小仓库

import { reqGetSystemInfo, } from "@/api";

const state={
    systeminfo: ''
};
const mutations = {
    SYSTEMINFO(state, systeminfo) {
        state.systeminfo = systeminfo
    }
};
const actions = {
    async getSystemInfo({commit}, obj) {
        let {data} = await reqGetSystemInfo(obj);
        commit("SYSTEMINFO", data.data)
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