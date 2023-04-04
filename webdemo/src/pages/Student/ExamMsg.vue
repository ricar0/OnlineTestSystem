<template>
  <div id="msg">
    <Header></Header>
    <div class="title">
      <span @click="toExam" style="cursor: pointer; color: blue;text-decoration:underline;">试卷列表</span>
      <span>/  {{examData.source}}</span>
    </div>
    <div class="wrapper">
      <ul class="top">
        <li class="example">{{examData.source}}&nbsp;</li><i v-if="examData.lock" class="el-icon-lock" style="font-size:23px; font-weight:700;"></i>
        <li class="right">
          <div>
            <span class="count">总分</span>
            <span class="score">{{examData.totalScore}}</span>
          </div>
        </li>
      </ul>
      <ul class="bottom">
        <li><i class="el-icon-edit"></i>来自 {{examData.teacher}}</li>
        <li class="right"><el-button @click="toAnswer(examData.id)">开始答题</el-button></li>
      </ul>
      <ul class="info">
        <li @click="dialogVisible = true"><a href="javascript:;"><i class="el-icon-info"></i>考生须知</a></li>
      </ul>
    </div>
    <div class="content" style="margin-bottom: 20%;">
      <el-collapse v-model="activeName" >
        <el-collapse-item class="header" name="0">
          <template slot="title" class="stitle" >
            <div class="title">
              <span style="font-weight: 700;">题型</span><i class="header-icon el-icon-info"></i>
              <span class="time">{{examData.totalScore}}分 / {{examData.totalTime}}分钟</span>
              <el-button type="primary" size="small">点击查看试题详情</el-button>
            </div>
          </template>
          <el-collapse class="inner">
            <el-collapse-item>
              <template slot="title" name="1">
                <div class="titlei">单选题 (共{{single}}题 共计{{totalScore[0]}}分)</div>
              </template>
            </el-collapse-item>
            <el-collapse-item>
              <template slot="title" name="2">
                <div class="titlei">多选题 (共{{multiple}}题  共计{{totalScore[1]}}分)</div>
              </template>
            </el-collapse-item>
            <el-collapse-item>
              <template slot="title" name="3">
                <div class="titlei">判断题 (共{{tf}}题 共计{{totalScore[2]}}分)</div>
              </template>
            </el-collapse-item>
          </el-collapse>
        </el-collapse-item>
        
      </el-collapse>
    </div>
    <!--考生须知对话框-->
    <el-dialog
      title="考生须知"
      :visible.sync="dialogVisible"
      width="30%">
      <span>{{examData.tips}}</span>
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
      examData: {},
      single: 0,
      multiple: 0,
      tf: 0,
      totalScore: [0,0,0],
      uid:null,
    }
  },
  mounted() {
    this.init()
  },
  methods: {
    //初始化页面数据
    init() {
        let id = this.$route.query.id //获取路由传递过来的试卷编号
        this.$store.dispatch('getExamById', id).then(res=>{
          this.examData = this.$store.state.exam.examinfo;
        })
        this.$store.dispatch('getPaperInfoById', {id}).then(res=>{
          let list = this.$store.state.exam.paperinfo;
          for (let i = 0; i < list.problems.length; i++) {
            if (list.problems[i].label == 'single') this.single++;
            if (list.problems[i].label == 'multiple') this.multiple++;
            if (list.problems[i].label == 'tf') this.tf++;
          }
          this.totalScore[0] = this.single * list.singleScore;
          this.totalScore[1] = this.multiple * list.multipleScore;
          this.totalScore[2] = this.tf * list.tfScore;
          this.$store.dispatch('getUserInfo').then(res=>{
            this.uid = this.$store.state.user.userinfo.id;
            let user_id = this.uid;
            let exam_id = this.$route.query.id;
            let singleNum = this.single
            let multipleNum = this.multiple
            let tfNum = this.tf
            // this.$store.dispatch('initExamCookies', {user_id, exam_id, singleNum, multipleNum, tfNum})
          })
        })
    },
    toAnswer(id) {
      if (this.examData.lock) {
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
.content .title .time {
  font-size: 16px;
  margin-left: 500px;
  color: #999;
}
.content .stitle {
  background-color: #0195ff;
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
  margin-top: 20px;
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