import { reqAddProblemToWrongBook, reqDeleteWrongProblem, reqGetWrongProblemByUserId, reqGetWrongProblemNumber } from "@/api";

//state模块小仓库
const state={
    wrongproblemAll: [],
    number: 0
};
const mutations = {
    GETWRONGPROBLEMBYUSERID(state, wrongproblem) {
        state.wrongproblemAll = wrongproblem;
    },
    GETWRONGROBLEMNUMBER(state, number) {
        state.number = number;
    }
};
const actions = {
    async addProblemToWrongBook({commit}, obj) {
        let {data} = await reqAddProblemToWrongBook(obj)
        if (data.code == 200) {
            return "ok";
        } else {
            return 'error';
        }
    },
    async getWrongProblemByUserId({commit}, obj) {
        let {data} = await reqGetWrongProblemByUserId(obj)
        commit("GETWRONGPROBLEMBYUSERID", data.data)
        if (data.code == 200) {
            return "ok";
        } else {
            return 'error';
        }
    },
    async deleteWrongProblem({commit}, obj) {
        let {data} = await reqDeleteWrongProblem(obj)
        if (data.code == 200) {
            return "ok";
        } else {
            return 'error';
        }
    },
    async getWrongProblemNumber({commit}, obj) {
        let {data} = await reqGetWrongProblemNumber(obj)
        commit("GETWRONGROBLEMNUMBER", data.data)
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