//search模块小仓库

import { reqSearchMyExamByKey } from "@/api";

const state={
    myexam: []
};
const mutations = {
    SEARCHMYEXAMBYKEY(state, myexam) {
        state.myexam = myexam;
    }
};
const actions = {
    async searchMyExamByKey({commit}, obj) {
        let {data} = await reqSearchMyExamByKey(obj);
        if (data.code == 200) {
            commit("SEARCHMYEXAMBYKEY", data.data);
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