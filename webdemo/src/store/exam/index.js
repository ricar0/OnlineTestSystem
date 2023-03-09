import { reqGetMyExam, reqGetExamById, reqGetProblemById, reqGetPaperInfoById, 
    reqInitExamCookies, reqGetExamCookies, reqSetExamCookies, reqStartExam, reqEndExam } from "@/api";

const state = {
    myexam: [],
    examinfo: [],
    probleminfo: [],
    paperinfo:[],
    examcookies: null
}

const mutations = {
    GETMYEXAM(state, myexam) {
        state.myexam = myexam;
    },
    GETEXAMBYID(state, examinfo) {
        state.examinfo = examinfo;
    },
    GETPEOBELMBYID(state, probleminfo) {
        state.probleminfo = probleminfo;
    },
    GETPAPERINFOBYID(state, paperinfo) {
        state.paperinfo = paperinfo;
    },
    GETEXAMCOOKIES(state, examcookies) {
        state.examcookies = examcookies;
    }
}

const actions = {
    async getMyExam({commit}) {
        let {data} = await reqGetMyExam();
        console.log(data)
        if (data.code == 200) {
            commit("GETMYEXAM", data.data);
            return "ok";
        } else {
            return Promise.reject(new Error("failure"));
        }
    },
    async getExamById({commit}, obj) {
        let {data} = await reqGetExamById(obj);
        if (data.code == 200) {
            commit("GETEXAMBYID", data.data);
            return "ok";
        } else {
            return Promise.reject(new Error("failure"));
        }
    },
    async getProblemById({commit}, obj) {
        let {data} = await reqGetProblemById(obj);
        if (data.code == 200) {
            commit("GETPEOBELMBYID", data.data);
            return "ok";
        } else {
            return Promise.reject(new Error("failure"));
        }
    },
    async getPaperInfoById({commit}, obj) {
        let {data} = await reqGetPaperInfoById(obj);
        if (data.code == 200) {
            commit("GETPAPERINFOBYID", data.data);
            return "ok";
        } else {
            return Promise.reject(new Error("failure"));
        }
    },
    async initExamCookies({commit}, obj) {
        let {data} = await reqInitExamCookies(obj);
        if (data.code == 200) {
            return "ok";
        } else {
            return Promise.reject(new Error("failure"));
        }
    },
    async getExamCookies({commit}, obj) {
        let {data} = await reqGetExamCookies(obj);
        if (data.code == 200) {
            commit("GETEXAMCOOKIES", data.data);
            return "ok";
        } else {
            return Promise.reject(new Error("failure"));
        }
    },
    async setExamCookies({commit}, obj) {
        let {data} = await reqSetExamCookies(obj);
        if (data.code == 200) {
            return "ok";
        } else {
            return Promise.reject(new Error("failure"));
        }
    },
    async startExam({commit}, obj) {
        let {data} = await reqStartExam(obj);
        if (data.code == 200) {
            return "ok";
        } else {
            return Promise.reject(new Error("failure"));
        }
    },
    async endExam({commit}, obj) {
        let {data} = await reqEndExam(obj);
        if (data.code == 200) {
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