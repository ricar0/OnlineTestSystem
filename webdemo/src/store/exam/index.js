import { reqGetMyExam } from "@/api";

const state = {
    myexam: []
}

const mutations = {
    GETMYEXAM(state, myexam) {
        state.myexam = myexam;
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
    }
}
const getters = {};
export default {
    state,
    mutations,
    actions,
    getters
}