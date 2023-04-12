import axios from "axios"
import nProgress from "nprogress";
import "nprogress/nprogress.css"
import {getToken} from '@/utils/token'
//利用axios对象的方法create，去创建一个axios实例
const requests = axios.create({
    //配置对象
    //基础路径，发请求的时候，路径中会出现api
    baseURL: "/api",
    //请求超时的时间
    // timeout: 5000,
});

//请求拦截器：在发请求之前，请求拦截器可以检测到，可以在请求发出去之前做一些事情
requests.interceptors.request.use((config)=>{
    //config: 配置对象，对象里有一个属性很重要，headers请求头
    if (getToken()) {
        config.headers['token'] = getToken();
    }
    nProgress.start();//进度条启动
    return config;
},(error)=> {
    return Promise.reject(new Error('failure'))
});

//响应拦截器
requests.interceptors.response.use((res)=>{
    //成功的回调函数：服务器响应数据回来之后，响应拦截器可以检测到，可以做一些事情
    nProgress.done();//进度条结束
    return res;
},(error)=> {
    return Promise.reject(new Error('failure'))
});

export default requests;