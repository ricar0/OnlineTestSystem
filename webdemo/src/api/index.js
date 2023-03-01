//对API统一管理
import requests from './request'

//登陆
//url:/api/user/login  method:post  username  password 
export const reqUserLogin = (data)=>requests({url:'/user/login',data,method:'post'}); 

