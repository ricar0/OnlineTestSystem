import {reqGetProblemAll } from "@/api";

const state = {
    problem: []
}

const mutations = {
    GETPROBLEMALL(state, problem) {
        state.problem = problem;
    }
}

const actions = {
    async getProblemAll({commit}) {
        let {data} = await reqGetProblemAll();
        if (data.code == 200) {
            commit("GETPROBLEMALL", data.data);
            return "ok";
        } else {
            return Promise.reject(new Error("failure"));
        }
    }
}
const getters = {};
export default {
    state,
    mutations,
    actions,
    getters
}