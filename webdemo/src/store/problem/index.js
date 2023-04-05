import {reqAccept, reqGetAllNumber, reqGetProblemAll, reqGetProblemByFilter, 
    reqGetProblemById, reqWrongAnswer} from "@/api";

const state = {
    problem: [],
    count: 0,
    probleminfo: []
}

const mutations = {
    GETPROBLEMALL(state, problem) {
        state.problem = problem;
    },
    GETPROBLEMBYFILTER(state, problem) {
        state.problem = problem;
    },
    GETALLNUMBER(state, count) {
        state.count = count;
    },
    GETPROBELMBYID(state, probleminfo) {
        state.probleminfo = probleminfo;
    },
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
    },
    async getProblemByFilter({commit}, obj) {
        let {data} = await reqGetProblemByFilter(obj);
        console.log(data)
        if (data.code == 200) {
            commit("GETPROBLEMBYFILTER", data.data);
            return "ok";
        } else {
            return Promise.reject(new Error("failure"));
        }
    },
    async getAllNumber({commit}, obj) {
        let {data} = await reqGetAllNumber(obj);
        console.log(data)
        if (data.code == 200) {
            commit("GETALLNUMBER", data.data);
            return "ok";
        } else {
            return Promise.reject(new Error("failure"));
        }
    },
    async getProblemById({commit}, obj) {
        let {data} = await reqGetProblemById(obj);
        if (data.code == 200) {
            commit("GETPROBELMBYID", data.data);
            return "ok";
        } else {
            return "error"
        }
    },
    async accept({commit}, obj) {
        let {data} = await reqAccept(obj);
        if (data.code == 200) {
            return "ok";
        } else {
            return Promise.reject(new Error("failure"));
        }
    },
    async wrongAnswer({commit}, obj) {
        let {data} = await reqWrongAnswer(obj);
        if (data.code == 200) {
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