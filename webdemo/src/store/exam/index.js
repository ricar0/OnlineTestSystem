import { reqGetMyExam, reqGetExamById, reqGetProblemById, reqGetPaperInfoById, 
    reqInitExamCookies, reqGetExamCookies, reqSetExamCookies, reqStartExam, reqEndExam, 
    reqGetExamResult, reqGetExamByFilter, reqGetAllNumber3, reqGetUserNumberByExamId, reqGetMyExamNumber } from "@/api";

const state = {
    myexam: [],
    examinfo: [],
    probleminfo: [],
    paperinfo:[],
    examcookies: null,
    examresult:[],
    exam:[],
    count: null,
    number: 0,
}

const mutations = {
    GETMYEXAM(state, myexam) {
        state.myexam = myexam;
    },
    GETEXAMBYID(state, examinfo) {
        state.examinfo = examinfo;
    },
    GETPAPERINFOBYID(state, paperinfo) {
        state.paperinfo = paperinfo;
    },
    GETEXAMCOOKIES(state, examcookies) {
        state.examcookies = examcookies;
    },
    GETEXAMRESULT(state, examresult) {
        state.examresult = examresult;
    },
    GETEXAMBYFILTER(state, exam) {
        state.exam = exam;
    },
    GETALLNUMBER3(state, count) {
        state.count = count;
    },
    GETUSERNUMBERBYEXAMID(state, number) {
        state.number = number;
    },
    GETMYEXAMNUMBER(state,number) {
        state.number = number;
    }
}

const actions = {
    async getMyExam({commit}, obj) {
        let {data} = await reqGetMyExam(obj);
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
            return "over";
        }
    },
    async endExam({commit}, obj) {
        let {data} = await reqEndExam(obj);
        if (data.code == 200) {
            return "ok";
        } else {
            return Promise.reject(new Error("failure"));
        }
    },
    async getExamResult({commit}, obj) {
        let {data} = await reqGetExamResult(obj);
        commit("GETEXAMRESULT", data.data);
        if (data.code == 200) {
            return "ok";
        } else {
            return Promise.reject(new Error("failure"));
        }
    },
    async getExamByFilter({commit}, obj) {
        let {data} = await reqGetExamByFilter(obj);
        commit("GETEXAMBYFILTER", data.data);
        if (data.code == 200) {
            return "ok"
        } else {
            return Promise.reject(new Error("failure"));
        }
    },
    async getAllNumber3({commit}, obj) {
        let {data} = await reqGetAllNumber3(obj);
        commit("GETALLNUMBER3", data.data);
        if (data.code == 200) {
            return "ok";
        } else {
            return Promise.reject(new Error("failure"));
        }
    },
    async getUserNumberByExamId({commit}, obj) {
        let {data} = await reqGetUserNumberByExamId(obj);
        commit("GETUSERNUMBERBYEXAMID", data.data);
        if (data.code == 200) {
            return "ok";
        } else {
            return Promise.reject(new Error("failure"));
        }
    },
    async getMyExamNumber({commit}, obj) {
        let {data} = await reqGetMyExamNumber(obj);
        commit("GETMYEXAMNUMBER", data.data);
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