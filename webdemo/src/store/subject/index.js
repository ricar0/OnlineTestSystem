//search模块小仓库

import { reqDeleteSubject, reqGetSubjectAll, reqGetSubjectProblemNumber
,reqAddSubject} from "@/api";

const state={
    subject: [],
    subjectInfo: ''
};
const mutations = {
    GETSUBJECTALL(state, subject) {
        state.subject = subject;
    },
    GETSUBJECTPROBLEMNUMBER(state, subjectInfo) {
        state.subjectInfo = subjectInfo;
    }
};
const actions = {
    async getSubjectAll({commit}, obj) {
        let {data} = await reqGetSubjectAll(obj);
        if (data.code == 200) {
            commit("GETSUBJECTALL", data.data);
            return "ok";
        } else {
            return data.msg;
        }
    },
    async getSubjectProblemNumber({commit}, obj) {
        let {data} = await reqGetSubjectProblemNumber(obj);
        if (data.code == 200) {
            commit("GETSUBJECTPROBLEMNUMBER", data.data);
            return "ok";
        } else {
            return data.msg;
        }
    },
    async addSubject({commit}, obj) {
        let {data} = await reqAddSubject(obj);
        if (data.code == 200) {
            return "ok";
        } else {
            return data.msg;
        }
    },
    async deleteSubject({commit}, obj) {
        let {data} = await reqDeleteSubject(obj);
        if (data.code == 200) {
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