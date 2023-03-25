import { reqGetAllNumber2, reqGetMyPractice, reqGetPracticeAll, reqGetPracticeByFilter } from "@/api";

const state = {
    mypractice: [],
    practice: [],
    count: 0
}

const mutations = {
    GETMYPRACTICE(state, mypractice) {
        state.mypractice = mypractice;
    },
    GETRACTICEALL(state, practice) {
        state.practice = practice;
    },
    GETPRACTICEBYFILTER(state, practice) {
        state.practice = practice;
    },
    GETALLNUMBER(state, count) {
        state.count = count;
    }
}

const actions = {
    async getMyPractice({commit}) {
        let {data} = await reqGetMyPractice();
        if (data.code == 200) {
            commit("GETMYPRACTICE", data.data);
            return "ok";
        } else {
            return Promise.reject(new Error("failure"));
        }
    },
    async getPracticeAll({commit}) {
        let {data} = await reqGetPracticeAll();
        if (data.code == 200) {
            commit("GETRACTICEALL", data.data);
            return "ok";
        } else {
            return Promise.reject(new Error("failure"));
        }
    },
    async getPracticeByFilter({commit}, obj) {
        let {data} = await reqGetPracticeByFilter(obj);
        if (data.code == 200) {
            commit("GETPRACTICEBYFILTER", data.data);
            return "ok";
        } else {
            return Promise.reject(new Error("failure"));
        }
    },
    async getAllNumber2({commit}, obj) {
        let {data} = await reqGetAllNumber2(obj);
        if (data.code == 200) {
            commit("GETALLNUMBER", data.data);
            return "ok";
        } else {
            return Promise.reject(new Error("failure"));
        }
    },
}
const getters = {};
export default {
    state,
    mutations,
    actions,
    getters
}