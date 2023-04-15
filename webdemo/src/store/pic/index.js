//role模块小仓库

import { reqUploadPic } from "@/api";

const state={
    
};
const mutations = {
    
};
const actions = {
    async UploadPic({commit}, obj) {
        let {data} = await reqUploadPic(obj);
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