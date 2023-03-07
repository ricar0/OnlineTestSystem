//对API统一管理
import requests from './request'

//登陆
//url:/api/auth/login  method:post  username,password 
export const reqUserLogin = (data)=>requests({url:'/auth/login',data,method:'post'}); 

//获取用户信息
//url:/api/auth/getLoginInfo  method:get  
export const reqUserInfo = ()=>requests({url:'/auth/getLoginInfo',method:'get'});

//退出登录
//url:/api/auth/logout  method: get
export const reqUserLogout = ()=>requests({url:'auth/logout',method:'get'});

//发送验证码
//url:/api/auth/sendCode method: post  phone,username,password1,password2
export const reqSendCode = (data)=>requests({url:'auth/sendCode',data,method:'post'});

//验证激活
//url:/api/auth/getCode method: post  phone,code
export const reqGetCode = (data)=>requests({url:'auth/getCode',data,method:'post'});

//获取我的考试
//url:/api/exam/getMyExam method: get
export const reqGetMyExam = ()=>requests({url:'exam/getMyExam',method:'get'});

//获取我的练习
//url:/api/exam/getMyExam method: get
export const reqGetMyPractice = ()=>requests({url:'practice/getMyPractice',method:'get'});

//获取考试信息
//url:/api/exam/getMyExam method: get
export const reqGetExamById = (id)=>requests({url:'exam/getExamById/'+id,method:'get'});

//获取题目列表
//url:/api/exam/getMyExam method: post
export const reqGetProblemById = (data)=>requests({url:'exam/getProblemById',data, method:'post'});

//获取试题信息
//url:/api/exam/getMyExam method: post
export const reqGetPaperInfoById = (data)=>requests({url:'exam/getPaperInfoById',data, method:'post'});