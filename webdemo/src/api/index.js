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
export const reqGetMyExam = (data)=>requests({url:'exam/getMyExam',data,method:'post'});

//获取我的练习
//url:/api/exam/getMyExam method: get
export const reqGetMyPractice = (data)=>requests({url:'practice/getMyPractice',data,method:'post'});

//获取考试信息
//url:/api/exam/getMyExam method: get
export const reqGetExamById = (id)=>requests({url:'exam/getExamById/'+id,method:'get'});

//获取试题信息
//url:/api/paper/getPaperInfoById method: post
export const reqGetPaperInfoById = (data)=>requests({url:'paper/getPaperInfoById',data, method:'post'});

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

//获取所有教师
export const reqGetTeacherAll = ()=>requests({url:'user/getTeacherAll',method:'get'})

//获取用户信息
//url:/api/auth/getUserInfoById  method:get  
export const reqGetUserInfoById = (data)=>requests({url:'/user/getUserInfoById/'+data,method:'get'});

//修改用户信息
//url:/api/auth/updateUserInfo  method:post  
export const reqUpdateUserInfo = (data)=>requests({url:'/user/updateUserInfo',data,method:'post'});

//获取题库
//url:/api/problem/getAll method:get
export const reqGetProblemAll = ()=>requests({url:'/problem/getAll',method:'get'});

//通过筛选获取题库
//url:/api/problem/getProblemByFilter method:post
export const reqGetProblemByFilter = (data)=>requests({url:'/problem/getProblemByFilter',data,method:'post'});

//获取题目数量
//url:/api/problem/getAllNumber method:get
export const reqGetAllNumber = (data)=>requests({url:'/problem/getAllNumber',data,method:'post'});

//通过id获取题目信息
//url:/api/problem/getProblemById method:post
export const reqGetProblemById = (data)=>requests({url:'/problem/getProblemById',data,method:'post'});

//答对题目
//url:/api/problem/accept method:post
export const reqAccept = (data)=>requests({url:'/problem/accept',data,method:'post'});

//答错题目
//url:/api/problem/wrongAnswer method:post
export const reqWrongAnswer = (data)=>requests({url:'/problem/wrongAnswer',data,method:'post'});

//获取所有训练
//url:/api/practice/getPracticeAll method:get
export const reqGetPracticeAll = ()=>requests({url:'/practice/getPracticeAll',method:'get'});

//通过筛选获取训练
//url:/api/problem/getPracticeByFilter method:post
export const reqGetPracticeByFilter = (data)=>requests({url:'/practice/getPracticeByFilter',data,method:'post'});

//获取训练数量
//url:/api/practice/getAllNumber method:get
export const reqGetAllNumber2 = (data)=>requests({url:'/practice/getAllNumber',data,method:'post'});

//通过筛选获取考试
//url:/api/exam/getExamByFilter method:post
export const reqGetExamByFilter = (data)=>requests({url:'/exam/getExamByFilter',data,method:'post'});

//通过筛选获取考试数量
//url:/api/exam/getAllNumber3 method:post
export const reqGetAllNumber3 = (data)=>requests({url:'/exam/getAllNumber3',data,method:'post'});

//通过考试Id获取学生数
//url:/api/exam/getUserNumberByExamId method:post
export const reqGetUserNumberByExamId = (data)=>requests({url:'/exam/getUserNumberByExamId',data,method:'post'});

//获取我的考试数量
export const reqGetMyExamNumber = (data)=>requests({url:'/exam/getMyExamNumber',data,method:'post'});

//获取我的练习数量
export const reqGetMyPracticeNumber = (data)=>requests({url:'/practice/getMyPracticeNumber',data,method:'post'});

//添加题目到错题本
export const reqAddProblemToWrongBook = (data)=>requests({url:'/wrongbook/addProblemToWrongBook',data,method:'post'});

//获取一个用户所有的错题
export const reqGetWrongProblemByUserId = (data)=>requests({url:'/wrongbook/getWrongProblemByUserId',data,method:'post'});

//删除错题
export const reqDeleteWrongProblem = (data)=>requests({url:'/wrongbook/deleteWrongProblem',data,method:'post'});

//获取错题数量
export const reqGetWrongProblemNumber = (data)=>requests({url:'/wrongbook/getWrongProblemNumber',data,method:'post'});

//修改密码
export const reqUpdatePassword = (data)=>requests({url:'/user/updatePassword',data,method:'post'});

//发送邮箱验证码
export const reqSendEmail = (data)=>requests({url:'/email/sendEmail',data,method:'post'});

//验证邮箱验证码
export const reqVerifyEmail = (data)=>requests({url:'/email/verifyEmail',data,method:'post'});

//删除考试
export const reqDeleteExam = (data)=>requests({url:'/exam/deleteExam', data,method:'post'});

//修改考试信息
export const reqUpdateExamInfo = (data)=>requests({url:'/exam/updateExamInfo', data,method:'post'});

//添加考试
export const reqAddExam = (data)=>requests({url:'/exam/addExam', data,method:'post'});

//随机生成试题
export const reqAddExamByRand = (data)=>requests({url:'/paper/addExamByRand', data,method:'post'});

//利用遗传算法组卷
export const reqAddExamByGeneticAlgorithm = (data)=>requests({url:'/paper/addExamByGeneticAlgorithm', data,method:'post'});

//向题库中添加题目
export const reqAddProblem = (data) =>requests({url:'/problem/addProblem',data,method:'post'});

//向题库中添加题目
export const reqGetSubjectAll = () =>requests({url:'/subject/getSubjectAll',method:'get'});

//获取审批信息
export const reqGetCheckList = (data) =>requests({url:'/role/getCheckList',data,method:'post'});

//获取审批信息个数
export const reqGetCheckListSize = (data) =>requests({url:'/role/getCheckListSize',data,method:'post'});

//审批通过
export const reqAcceptCheck = (data) =>requests({url:'/role/acceptCheck',data,method:'post'});

//审批不通过
export const reqRefuseCheck = (data) =>requests({url:'/role/refuseCheck',data,method:'post'});

//上传图片到后端
export const reqUploadPic = (data) =>requests({url:'/pic/uploadPic',data,method:'post'});

//获取当前申请状态
export const reqGetCheckStateById = (data) =>requests({url:'/role/getCheckStateById',data,method:'post'});

//报名考试
export const reqRegisterExam = (data) =>requests({url:'/exam/registerExam',data,method:'post'});

//查看报名状态
export const reqGetRegisterState = (data) =>requests({url:'/exam/getRegisterState',data,method:'post'});

//增加训练
export const reqAddPractice = (data) =>requests({url:'/practice/addPractice',data,method:'post'});

//获取练习信息
export const reqGetPracticeInfo = (data) =>requests({url:'/practice/getPracticeInfo',data,method:'post'});

//报名训练
export const reqRegisterPractice = (data) =>requests({url:'/practice/registerPractice',data,method:'post'});

//获取报名状态
export const reqGetRegisterState2 = (data) =>requests({url:'/practice/getRegisterState',data,method:'post'});

//获取训练试题和相关信息
export const reqGetPaperInfoByPracticeId = (data) =>requests({url:'/practice/getPaperInfoByPracticeId',data,method:'post'});

//保存练习结果
export const reqAddPracticeResult = (data) =>requests({url:'/practice/addPracticeResult',data,method:'post'});

//获取最近的练习结果
export const reqGetRecentPracticeResult = (data) =>requests({url:'/practice/getRecentPracticeResult',data,method:'post'});

//获取最近的考试结果
export const reqGetRecentExamResult = (data) =>requests({url:'/exam/getRecentExamResult',data,method:'post'});

//获取考试分析
export const reqGetExamScoreResult = (data) =>requests({url:'/exam/getExamScoreResult',data,method:'post'});

//获取系统信息
export const reqGetSystemInfo = () =>requests({url:'/system/getSystemInfo',method:'get'});

//获取系统信息
export const reqGetRecentExamScore = () =>requests({url:'/exam/getRecentExamScore',method:'get'});

//获取学科信息
export const reqGetSubjectProblemNumber = () =>requests({url:'/subject/getSubjectProblemNumber',method:'get'});

//添加学科
export const reqAddSubject = (data) =>requests({url:'/subject/addSubject',data,method:'post'});

//删除学科
export const reqDeleteSubject = (data) =>requests({url:'/subject/deleteSubject',data, method:'post'});

//删除题目
export const reqDeleteProblem = (data) =>requests({url:'/problem/deleteProblem',data, method:'post'});

//验证验证码
export const reqVerifyEmailByCode = (data) =>requests({url:'/email/verifyEmailByCode',data, method:'post'});

//验证验证码
export const reqUpdatePasswordByEmail = (data) =>requests({url:'/email/updatePasswordByEmail',data, method:'post'});

//添加学生
export const reqAddUser = (data) =>requests({url:'/user/addUser',data, method:'post'});

//获取考试排名
export const reqGetExamRank = (data) =>requests({url:'/exam/getExamRank',data, method:'post'});
