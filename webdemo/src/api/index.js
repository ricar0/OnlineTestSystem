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

//初始化考试
//url:/api/exam/setExamCookies method: post
export const reqInitExamCookies = (data)=>requests({url:'exam/initExamCookies',data, method:'post'});

//下载考试进度
//url:/api/exam/getExamCookies method: post
export const reqGetExamCookies = (data)=>requests({url:'exam/getExamCookies',data, method:'post'});

//上传考试进度
//url:/api/exam/getExamCookies method: post
export const reqSetExamCookies = (data)=>requests({url:'exam/setExamCookies',data, method:'post'});

//开始考试
//url:/api/exam/startExam method: post
export const reqStartExam = (data)=>requests({url:'exam/startExam',data, method:'post'});

//结束考试
//url:/api/exam/endExam method: post
export const reqEndExam = (data)=>requests({url:'exam/endExam',data, method:'post'});

//获取考试结果
//url:/api/exam/getExamResult method: post
export const reqGetExamResult = (data)=>requests({url:'exam/getExamResult',data, method:'post'});

//通过关键词搜索我的考试
//url:/api/search/searchMyExamByKey method: post
export const reqSearchMyExamByKey = (data)=>requests({url:'search/searchMyExamByKey',data, method:'post'});

//获取所有学生
//url:/api/user/getStudentAll method: get
export const reqGetStudentAll = ()=>requests({url:'user/getStudentAll',method:'get'})

//获取用户信息
//url:/api/auth/getUserInfoById  method:get  
export const reqGetUserInfoById = (data)=>requests({url:'/user/getUserInfoById/'+data,method:'get'});

//修改用户信息
//url:/api/auth/updateUserInfo  method:post  
export const reqUpdateUserInfo = (data)=>requests({url:'/user/updateUserInfo',data,method:'post'});

//获取题库
//url:/api/problem/getAll method:get
export const reqGetProblemAll = ()=>requests({url:'/problem/getAll',method:'get'});