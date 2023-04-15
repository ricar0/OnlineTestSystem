<template>
    <div id="msg">
      <Header></Header>
      <div class="title">
        <span @click="toExam" style="cursor: pointer; color: blue;text-decoration:underline;">练习列表</span>
        <span>/  {{practiceData.source}}</span>
      </div>
      <div class="wrapper">
        <ul class="top">
          <li class="example">{{practiceData.source}}&nbsp;</li>
          <i v-if="practiceData.permission" class="el-icon-lock" style="font-size:23px; font-weight:700;"></i>
          &nbsp;&nbsp;&nbsp;<el-tag v-if="practiceData.difficulty == 0">不限</el-tag>
          <el-tag v-if="practiceData.difficulty == 1" type="success">简单</el-tag>
          <el-tag v-if="practiceData.difficulty == 2" type="warning">中等</el-tag>
          <el-tag v-if="practiceData.difficulty == 3" type="danger">困难</el-tag>
          <li class="right">
            <div>
              <span class="count">时间</span>
              <span class="score">{{practiceData.totalTime}}</span>
            </div>
          </li>
        </ul>
        <ul class="bottom">
          <li><i class="el-icon-edit"></i>来自 {{practiceData.author}}</li>
          <li class="right">
            <el-button v-if="register">进入练习</el-button>
            <el-button v-if="!register" @click="Register(practiceData.id)">报名练习</el-button>
          </li>
        </ul>
        <ul class="info">
          <li @click="dialogVisible = true"><a href="javascript:;"><i class="el-icon-info"></i>考生须知</a></li>
        </ul>
      </div>
      <div class="content" style="margin-bottom: 20%;">
        <div class="title">
          <span style="font-weight: 700;">题型</span><i class="header-icon el-icon-info"></i>
        </div>
        <el-card style="margin: 30px;">
            <div class="titlei">单选题 (共{{practiceData.singleNum}}题)</div>
            <div class="titlei">多选题 (共{{practiceData.multipleNum}}题)</div>
            <div class="titlei">判断题 (共{{practiceData.tfNum}}题)</div>
        </el-card>
      </div>
      <!--考生须知对话框-->
      <el-dialog
        title="考生须知"
        :visible.sync="dialogVisible"
        width="30%">
        <span>考试开始后才可答题，交卷后需要等待考试结束才可查看试题</span>
        <span slot="footer" class="dialog-footer">
          <el-button @click="dialogVisible = false">知道了</el-button>
        </span>
      </el-dialog>
    </div>
  </template>
  
  <script>
  import Header from '@/components/Student/Header'
  export default {
    components: {
      Header
    },
    data() {
      return {
        dialogVisible: false, //对话框属性
        activeName: '0',  //默认打开序号
        score: [],  //每种类型分数的总数
        practiceData: {},
        single: 0,
        multiple: 0,
        tf: 0,
        totalScore: [0,0,0],
        uid:null,
        register: false
      }
    },
    mounted() {
      this.init()
    },
    methods: {
      //初始化页面数据
      init() {
        let id = this.$route.query.id
        this.$store.dispatch('getPracticeInfo', {id}).then(res=>{
            this.practiceData = this.$store.state.practice.practiceInfo           
        })
        this.$store.dispatch('getUserInfo').then(res=>{
            let user_id = this.$store.state.user.userinfo.id
            let practice_id = id
            this.$store.dispatch('getRegisterState2',{practice_id,user_id}).then(res=>{
                if (this.$store.state.practice.sta == 1) this.register = true
            })
        })
            
      },
      Register(practice_id) {
        this.$store.dispatch('getUserInfo').then(res=>{
          let user_id = this.$store.state.user.userinfo.id
          this.$store.dispatch('registerPractice', {practice_id, user_id}).then(res=>{
            if (res == 'ok') {
                this.$message({type: 'success', message: '报名成功!'})
                this.register = true
            }
          })
        })
        
      },
      toAnswer(id) {
        if (!this.register) {
          this.$message({type:'error', message: '没有报名无法查看试卷!'});
          return
        }
        if (this.examData.permission) {
          this.$prompt('请输入密码', '提示', {
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              inputErrorMessage: '密码不正确'
            }).then(({ value }) => {
              console.log(this.examData.password)
              if (value == this.examData.password) {
                this.$message({
                  type: 'success',
                  message: '密码正确'
                });
                this.$store.dispatch('getUserInfo').then(res=>{
                  this.uid = this.$store.state.user.userinfo.id;
                  let user_id = this.uid;
                  let exam_id = id;
                    this.$store.dispatch('startExam', {user_id, exam_id}).then(res=>{
                      this.$router.push({path:"/answer",query:{id: id}})
                    })
                  })
               
              }   
            }).catch(() => {
              this.$message({
              type: 'info',
              message: '取消输入'
            });       
          });
        } else {
          this.$store.dispatch('getUserInfo').then(res=>{
            this.uid = this.$store.state.user.userinfo.id;
            let user_id = this.uid;
            let exam_id = id;
            this.$store.dispatch('startExam', {user_id, exam_id}).then(res=>{
              this.$router.push({path:"/answer",query:{id: id}})
            })
          })
        }
      },
      toExam() {
        this.$router.push('/myexam');
      },
      showTime() {
        setInterval(() => {
          let nowTime = new Date().format('yyyy-MM-dd hh:mm:ss')
          if (this.examData.start_time > nowTime) {
            this.isStarted = false
          } else if (this.examData.end_time > nowTime) {
            this.isStarted = true
            this.isFinished = false
          } else {
            this.isStarted = true
            this.isFinished = true
          }
          if (!this.isStarted) {
            this.time = timediff(nowTime, this.examData.start_time)
          } else if (this.isStarted && !this.isFinished) {
            this.time = timediff(nowTime, this.examData.end_time)
          }
          if (this.isFinished) return
        },1000)
      }
    }
  }
  </script>
  
  <style lang="less" scoped>
  li {
      list-style: none;
  }
  .bottom {
    .right{
      .el-button{
        color: #409EFF;
        border-color: #c6e2ff;
        background-color: #ecf5ff;
      }
    }
  }
  .right {
    margin-left: auto;
  }
  .inner .contenti .question {
    margin-left: 10px;
    color: #9a9a9a;
    font-size: 14px;
  }
  .content .inner .titlei {
    margin-left: 20px;
    font-size: 16px;
    color: #88949b;
    font-weight: bold;
  }
  
  .content .title {
    padding-left: 30px;
  }
  
  .content .titlei {
    margin: 20px;
    font-weight: 600;
  }
  .content .title span {
    margin-right: 10px;
  }
  #msg .content .title {
    font-size: 20px;
    margin: 0px;
    display: flex;
    align-items: center;
  }
  .content {
    padding: 20px;
    margin-top: 20px;
    height: auto;
    background-color: #fff;
  }
  .content .header {
    padding: 10px 30px;
  }
  .wrapper .info {
    margin: 20px 0px 0px 20px;
    border-top: 1px solid #eee;
    padding: 20px 0px 10px 0px;
  }
  .wrapper .info a {
    color: #88949b;
    font-size: 14px;
  }
  .wrapper .info a:hover {
    color: #0195ff;
  }
  .wrapper .bottom .btn {
    cursor: pointer;
    padding: 5px 10px;
    border: 1px solid #88949b;
    border-radius: 4px;
  } 
  .wrapper .bottom {
    display: flex;
    // margin-left: 20px;
    color: #999;
    font-size: 14px;
    align-items: center;
  }
  .wrapper .bottom li {
    margin-right: 14px;
  }
  #msg {
    // background-color: #eee;
    background-color: #eff3f5!important;
    width: 50%;
    margin: 0 auto;
  }
  #msg .title {
    margin: 2%;
    margin-top: 80px;
  }
  #msg .wrapper {
    background-color: #fff;
    padding: 1%;
  }
  .wrapper .top {
    display: flex;
    // margin: 2%;
    align-items: center;
  }
  .wrapper .top .right {
    margin-left: auto;
  }
  .wrapper .top .example {
    color: #333;
    font-size: 22px;
    font-weight: 700;
  }
  .wrapper .top li i {
    margin-left: 10%;
    color: #88949b;
  }
  .wrapper .right .count {
    margin-right: 60px;
    color: #fff;
    padding: 4px 10px;
    background-color: #88949b;
    border-top-left-radius: 4px;
    border-bottom-left-radius: 4px;
    border: 1px solid #88949b;
  }
  .wrapper .right .score {
    position: absolute;
    left: 53px;
    top: -5px;
    padding: 1px 12px;
    font-size: 20px;
    color: #88949b;
    border: 1px dashed #88949b;
    border-left: none;
    border-top-right-radius: 4px;
    border-bottom-right-radius: 4px;
    font-weight: bold;
  }
  .wrapper .right div {
    position: relative;
  }
  </style>