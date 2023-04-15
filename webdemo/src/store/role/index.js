//role模块小仓库

import { reqAcceptCheck, reqGetCheckList, reqGetCheckListSize, reqGetCheckStateById, reqRefuseCheck } from "@/api";

const state={
    checkList: [],
    size: 0,
    sta: null
};
const mutations = {
    GETCHECKLIST(state, checkList) {
        state.checkList = checkList;
    },
    GETCHECKLISTSIZE(state, size) {
        state.size = size;
    },
    GETCHECKSTATEBYID(state, sta) {
        state.sta = sta
    }
};
const actions = {
    async getCheckList({commit}, obj) {
        let {data} = await reqGetCheckList(obj);
        if (data.code == 200) {
            commit("GETCHECKLIST", data.data);
            return "ok";
        } else {
            return data.msg;
        }
    },
    async getCheckListSize({commit}, obj) {
        let {data} = await reqGetCheckListSize(obj);
        if (data.code == 200) {
            commit("GETCHECKLISTSIZE", data.data);
            return "ok";
        } else {
            return data.msg;
        }
    },
    async acceptCheck({commit}, obj) {
        let {data} = await reqAcceptCheck(obj);
        if (data.code == 200) {
            return "ok";
        } else {
            return data.msg;
        }
    },
    async refuseCheck({commit}, obj) {
        let {data} = await reqRefuseCheck(obj);
        if (data.code == 200) {
            return "ok";
        } else {
            return data.msg;
        }
    },
    async getCheckStateById({commit}, obj) {
        let {data} = await reqGetCheckStateById(obj);
        commit('GETCHECKSTATEBYID', data.data)
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