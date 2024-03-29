import { reqGetMyExam, reqGetExamById, reqGetProblemById, reqGetPaperInfoById, 
    reqInitExamCookies, reqGetExamCookies, reqSetExamCookies, reqStartExam, reqEndExam, 
    reqGetExamResult, reqGetExamByFilter, reqGetAllNumber3, reqGetUserNumberByExamId, reqGetMyExamNumber,
     reqDeleteExam, reqAddExam, reqAddExamByRand, reqUpdateExamInfo, 
     reqAddExamByGeneticAlgorithm, 
     reqRegisterExam,
     reqGetRegisterState,
     reqGetRecentExamResult,
     reqGetExamScoreResult,
     reqGetRecentExamScore,
     reqGetExamRank} from "@/api";

const state = {
    myexam: [],
    examinfo: [],
    probleminfo: [],
    paperinfo:null,
    examcookies: null,
    examresult:[],
    exam:[],
    count: null,
    number: 0,
    sta: 0,
    result: '',
    examresult: '',
    examrank:''
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
    },
    GETREGISTERSTATE(state, sta) {
        state.sta = sta;
    },
    GETRECENTEXAMRESULT(state, result) {
        state.result = result
    },
    GETEXAMSCORERESULT(state, examresult) {
        state.examresult = examresult
    },
    GETRECENTEXAMSCORE(state, examresult) {
        state.examresult = examresult
    },
    GETEXAMRANK(state, examrank) {
        state.examrank = examrank
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
    async deleteExam({commit}, obj) {
        let {data} = await reqDeleteExam(obj);
        if (data.code == 200) {
            return "ok";
        } else {
            return Promise.reject(new Error("failure"));
        }
    },
    async addExam({commit}, obj) {
        let {data} = await reqAddExam(obj);
        if (data.code == 200) {
            return "ok";
        } else {
            return Promise.reject(new Error("failure"));
        }
    },
    async addExamByRand({commit}, obj) {
        let {data} = await reqAddExamByRand(obj);
        if (data.code == 200) {
            return "ok";
        } else {
            return data.msg;
        }
    },
    async updateExamInfo({commit}, obj) {
        let {data} = await reqUpdateExamInfo(obj);
        if (data.code == 200) {
            return "ok";
        } else {
            return data.msg;
        }
    },
    async addExamByGeneticAlgorithm({commit}, obj) {
        let {data} = await reqAddExamByGeneticAlgorithm(obj);
        if (data.code == 200) {
            return "ok";
        } else {
            return data.msg;
        }
    },
    async registerExam({commit}, obj) {
        let {data} = await reqRegisterExam(obj);
        if (data.code == 200) {
            return "ok";
        } else {
            return data.msg;
        }
    },
    async getRegisterState({commit}, obj) {
        let {data} = await reqGetRegisterState(obj);
        commit("GETREGISTERSTATE", data.data)
        if (data.code == 200) {
            return "ok";
        } else {
            return data.msg;
        }
    },
    async getRecentExamResult({commit}, obj) {
        let {data} = await reqGetRecentExamResult(obj);
        commit("GETRECENTEXAMRESULT", data.data)
        if (data.code == 200) {
            return "ok";
        } else {
            return data.msg;
        }
    },
    async getExamScoreResult({commit}, obj) {
        let {data} = await reqGetExamScoreResult(obj);
        commit("GETEXAMSCORERESULT", data.data)
        if (data.code == 200) {
            return "ok";
        } else {
            return data.msg;
        }
    },
    async getRecentExamScore({commit}, obj) {
        let {data} = await reqGetRecentExamScore(obj);
        commit("GETRECENTEXAMSCORE", data.data)
        if (data.code == 200) {
            return "ok";
        } else {
            return data.msg;
        }
    },
    async getExamRank({commit}, obj) {
        let {data} = await reqGetExamRank(obj);
        commit("GETEXAMRANK", data.data)
        if (data.code == 200) {
            return "ok";
        } else {
            return data.msg;
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