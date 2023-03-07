import { reqGetMyExam, reqGetExamById } from "@/api";

const state = {
    myexam: [],
    examinfo: []
}

const mutations = {
    GETMYEXAM(state, myexam) {
        state.myexam = myexam;
    },
    GETEXAMBYID(state, examinfo) {
        state.examinfo = examinfo;
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
    }
}
const getters = {};
export default {
    state,
    mutations,
    actions,
    getters
}