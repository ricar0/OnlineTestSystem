import { reqGetAllNumber2, reqGetMyPractice, reqGetPracticeAll, reqGetPracticeByFilter
,reqGetMyPracticeNumber,
reqAddPractice,
reqGetPracticeInfo,
reqGetRegisterState2,
reqRegisterPractice,
reqGetProblemByPracticeId,
reqGetPaperInfoByPracticeId,
reqAddPracticeResult,
reqGetRecentPracticeResult} from "@/api";

const state = {
    mypractice: [],
    practice: [],
    count: 0,
    practiceInfo: '',
    sta: '',
    paperinfo: '',
    result: ''
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
    },
    GETMYPRACTICENUMBER(state, count) {
        state.count = count;
    },
    GETPRACTICEINFO(state, practiceInfo) {
        state.practiceInfo = practiceInfo
    },
    GETREGISTERSTATE(state, sta) {
        state.sta = sta;
    },
    GETPROBLEMBYPRACTICEID(state, paperinfo) {
        state.paperinfo = paperinfo
    },
    GETRECENTPRACTICERESULT(state, result) {
        state.result = result
    }
}

const actions = {
    async getMyPractice({commit},obj) {
        let {data} = await reqGetMyPractice(obj);
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
    async getMyPracticeNumber({commit}, obj) {
        let {data} = await reqGetMyPracticeNumber(obj);
        if (data.code == 200) {
            commit("GETMYPRACTICENUMBER", data.data);
            return "ok";
        } else {
            return Promise.reject(new Error("failure"));
        }
    },
    async getMyPracticeNumber({commit}, obj) {
        let {data} = await reqGetMyPracticeNumber(obj);
        if (data.code == 200) {
            commit("GETMYPRACTICENUMBER", data.data);
            return "ok";
        } else {
            return Promise.reject(new Error("failure"));
        }
    },
    async addPractice({commit}, obj) {
        let {data} = await reqAddPractice(obj);
        if (data.code == 200) {
            return "ok";
        } else {
            return data.msg;
        }
    },
    async getPracticeInfo({commit}, obj) {
        let {data} = await reqGetPracticeInfo(obj);
        commit("GETPRACTICEINFO", data.data)
        if (data.code == 200) {
            return "ok";
        } else {
            return data.msg;
        }
    },
    async getRegisterState2({commit}, obj) {
        let {data} = await reqGetRegisterState2(obj);
        commit("GETREGISTERSTATE", data.data)
        if (data.code == 200) {
            return "ok";
        } else {
            return data.msg;
        }
    },
    async registerPractice({commit}, obj) {
        let {data} = await reqRegisterPractice(obj);
        if (data.code == 200) {
            return "ok";
        } else {
            return data.msg;
        }
    },
    async getPaperInfoByPracticeId({commit}, obj) {
        let {data} = await reqGetPaperInfoByPracticeId(obj);
        commit("GETPROBLEMBYPRACTICEID", data.data)
        if (data.code == 200) {
            return "ok";
        } else {
            return data.msg;
        }
    },
    async addPracticeResult({commit}, obj) {
        let {data} = await reqAddPracticeResult(obj);
        if (data.code == 200) {
            return "ok";
        } else {
            return data.msg;
        }
    },
    async getRecentPracticeResult({commit}, obj) {
        let {data} = await reqGetRecentPracticeResult(obj);
        commit("GETRECENTPRACTICERESULT", data.data)
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