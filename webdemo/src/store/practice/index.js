import { reqGetMyPractice } from "@/api";

const state = {
    mypractice: []
}

const mutations = {
    GETMYPRACTICE(state, mypractice) {
        state.mypractice = mypractice;
    }
}

const actions = {
    async getMyPractice({commit}) {
        let {data} = await reqGetMyPractice();
        console.log(data)
        if (data.code == 200) {
            commit("GETMYPRACTICE", data.data);
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