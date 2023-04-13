//search模块小仓库

import { reqGetSubjectAll } from "@/api";

const state={
    subject: []
};
const mutations = {
    GETSUBJECTALL(state, subject) {
        state.subject = subject;
    }
};
const actions = {
    async getSubjectAll({commit}, obj) {
        let {data} = await reqGetSubjectAll(obj);
        if (data.code == 200) {
            commit("GETSUBJECTALL", data.data);
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