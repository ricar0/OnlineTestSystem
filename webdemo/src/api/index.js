//对API统一管理
import requests from './request'

//登陆
//url:/api/user/login  method:post  username  password 
export const reqUserLogin = (data)=>requests({url:'/user/login',data,method:'post'}); 

//获取用户信息
//url:/api/user/getLoginInfo  method:get  
export const reqUserInfo = ()=>requests({url:'/user/getLoginInfo',method:'get'});

//退出登录
//url:/api/user/logout  method: get
export const reqUserLogout = ()=>requests({url:'user/logout',method:'get'});