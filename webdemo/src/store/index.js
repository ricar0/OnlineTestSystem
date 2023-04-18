import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex);

//引入小仓库
import problem from './problem'
import user from './user'
import search from './search'
import exam from './exam'
import practice from './practice';
import wrongbook from './wrongbook'
import subject from './subject'
import role from './role'
import pic from './pic'
import system from './system'

//对外暴露Store类的一个实例
export default new Vuex.Store({
    //实现Vuex仓库模块式开发存储数据
    modules: {
        problem,
        search,
        user,
        exam,
        practice,
        wrongbook,
        subject,
        role,
        pic,
        system
    }
});
