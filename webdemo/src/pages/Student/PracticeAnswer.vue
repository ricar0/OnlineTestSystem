<template>
    <div id="answer">
       <div class="top">
         <ul class="item">
           <li><i class="el-icon-time" ref="toggle" @click="slider_flag = !slider_flag"></i></li>
           <li>{{practiceData.source}}</li>
           <li @click="flag = !flag">
             <p style="margin:0;"><i class="el-icon-user"></i> {{userInfo.username}}</p>
           </li>
         </ul>
       </div>
       <div class="flexarea">
          <!--左边题目编号区-->
          <transition name="slider-fade">
            <div class="left" v-if="slider_flag">
              <ul class="l-top">
                <li>
                  <a href="javascript:;"></a>
                  <span>当前</span>
                </li>
                <li>
                  <a href="javascript:;"></a>
                  <span>未答</span>
                </li>
                <li>
                  <a href="javascript:;"></a>
                  <span>已答</span>
                </li>
                <li>
                  <a href="javascript:;"></a>
                  <span>标记</span>
                </li>
              </ul>
              <div class="l-bottom">
                <div class="item">
                  <p>单选题部分</p>
                  <ul>
                    <li v-for="(list, index1) in topic[0]" :key="index1">
                      <a v-if="!isFinished" href="javascript:;" 
                        @click="single(index1)"
                        :class="{'border': index == index1 && currentType == 1,'bg': bg_flag && singleisClick[index1] == true}">
                        <span :class="{'mark': singleisMark[index1] == true}"></span>
                        {{index1+1}}
                      </a>
                      <a v-if="isFinished" href="javascript:;" 
                        @click="single(index1)"
                        :class="{'border': index == index1 && currentType == 1,'bg1': singleCheck[index1], 'bg2': !singleCheck[index1]}">
                        <span :class="{'mark': singleisMark[index1] == true}"></span>
                        {{index1+1}}
                      </a>
                    </li>
                  </ul>
                </div>
                <div class="item">
                  <p>多选题部分</p>
                  <ul>
                    <li v-for="(list, index2) in topic[1]" :key="index2">
                      <a v-if="!isFinished"
                        href="javascript:;" 
                        @click="multiple(index2)"
                        :class="{'border': index == index2 && currentType == 2,'bg': bg_flag && multipleisClick[index2] == true}">
                        <span :class="{'mark': multipleisMark[index2] == true}"></span>
                        {{topicCount[0]+index2+1}}
                      </a>
                      <a v-if="isFinished"
                        href="javascript:;" 
                        @click="multiple(index2)"
                        :class="{'border': index == index2 && currentType == 2,'bg1': multipleCheck[index2], 'bg2': !multipleCheck[index2] }">
                        <span :class="{'mark': multipleisMark[index2] == true}"></span>
                        {{topicCount[0]+index2+1}}
                      </a>
                    </li>
                  </ul>
                </div>
                <div class="item">
                  <p>判断题部分</p>
                  <ul>
                    <li v-for="(list, index3) in topic[2]" :key="index3">
                      <a v-if="!isFinished" href="javascript:;" @click="judge(index3)" :class="{'border': index == index3 && currentType == 3,'bg': bg_flag && judgeisClick[index3] == true}"><span :class="{'mark': judgeisMark[index3] == true}"></span>{{topicCount[0]+topicCount[1]+index3+1}}</a>
                      <a v-if="isFinished" href="javascript:;" @click="judge(index3)" :class="{'border': index == index3 && currentType == 3,'bg1': judgeCheck[index3], 'bg2': !judgeCheck[index3]}"><span :class="{'mark': judgeisMark[index3] == true}"></span>{{topicCount[0]+topicCount[1]+index3+1}}</a>
                    </li>
                  </ul>
                </div>
                <div v-if="!isFinished" class="final" @click="commit()">提交答案</div>
                <div v-if="isFinished" class="final" @click="show()">查看成绩</div>
              </div>
            </div>
          </transition>  
          <!--右边选择答题区-->
          <transition name="slider-fade">
          <div class="right">
            <div class="title">
              <span style="margin-left:1%;font-size:17px;font-weight:700;">{{title}}</span>
              <i class="iconfont icon-right auto-right"></i>
              <span v-if="!isFinished">全卷共{{topicCount[0]+topicCount[1]+topicCount[2]}}题 &nbsp;&nbsp;<i class="el-icon-time"></i>倒计时：<b>{{time.minute}}分 {{ time.seconds }}秒</b></span>
              <span v-if="isFinished">全卷共{{topicCount[0]+topicCount[1]+topicCount[2]}}题 &nbsp;&nbsp;<el-tag type="danger">考试已结束</el-tag></span>
            </div>
            <div class="content">
              <p class="topic"><span class="number">{{number}}</span>{{showQuestion}}</p>
              <div v-if="currentType == 1">
                <el-radio-group v-if="isFinished" v-model="singleAnswer[index]" @change="getSingleLabel" disabled>
                  <el-radio :label="1">A.{{showAnswer[0]}}</el-radio>
                  <el-radio :label="2">B.{{showAnswer[1]}}</el-radio>
                  <el-radio :label="3">C.{{showAnswer[2]}}</el-radio>
                  <el-radio :label="4">D.{{showAnswer[3]}}</el-radio>
                </el-radio-group>
                <el-radio-group v-if="!isFinished" v-model="singleAnswer[index]" @change="getSingleLabel">
                  <el-radio :label="1">A.{{showAnswer[0]}}</el-radio>
                  <el-radio :label="2">B.{{showAnswer[1]}}</el-radio>
                  <el-radio :label="3">C.{{showAnswer[2]}}</el-radio>
                  <el-radio :label="4">D.{{showAnswer[3]}}</el-radio>
                </el-radio-group>
                <div class="analysis" v-if="isFinished">
                  <ul style="padding: 0;">
                    <li><el-result v-if="singleCheck[index]" icon="success" style="padding:0; align-items: flex-start"></el-result></li>
                    <li><el-result v-if="!singleCheck[index]" icon="error" style="padding:0; align-items: flex-start"></el-result></li>
                    <li> <el-tag type="success">正确答案</el-tag><span class="right">{{topic[0][index].accept}}</span></li>
                    <li>
                      <el-tag>题目解析</el-tag> &nbsp;&nbsp;{{topic[0][index].solution == null ? '暂无解析': topic[0][index].solution}}
                    </li>
                    
                  </ul>
                </div>
              </div>
              <div v-if="currentType == 2">
                <el-checkbox-group v-if="isFinished" v-model="multipleAnswer[index]" @change="getMultipleLabel" disabled>
                  <el-checkbox :label="1">A.{{showAnswer[0]}}</el-checkbox><br>
                  <el-checkbox :label="2">B.{{showAnswer[1]}}</el-checkbox><br>
                  <el-checkbox :label="3">C.{{showAnswer[2]}}</el-checkbox><br>
                  <el-checkbox :label="4">D.{{showAnswer[3]}}</el-checkbox><br>
                </el-checkbox-group>
                <el-checkbox-group v-if="!isFinished" v-model="multipleAnswer[index]" @change="getMultipleLabel">
                  <el-checkbox :label="1">A.{{showAnswer[0]}}</el-checkbox><br>
                  <el-checkbox :label="2">B.{{showAnswer[1]}}</el-checkbox><br>
                  <el-checkbox :label="3">C.{{showAnswer[2]}}</el-checkbox><br>
                  <el-checkbox :label="4">D.{{showAnswer[3]}}</el-checkbox><br>
                </el-checkbox-group>
                <div class="analysis" v-if="isFinished">
                  <ul style="padding: 0;">
                    <li><el-result v-if="multipleCheck[index]" icon="success" style="padding:0; align-items: flex-start"></el-result></li>
                    <li><el-result v-if="!multipleCheck[index]" icon="error" style="padding:0; align-items: flex-start"></el-result></li>
                    <li> <el-tag type="success">正确答案</el-tag><span class="right">{{topic[1][index].accept}}</span></li>
                    <li>
                      <el-tag>题目解析</el-tag>&nbsp;&nbsp; {{topic[1][index].solution == null ? '暂无解析': topic[1][index].solution}}
                    </li>
                  </ul>
                </div>
              </div>
              <div class="judge" v-if="currentType == 3">
                <el-radio-group v-if="isFinished" v-model="judgeAnswer[index]" @change="getJudgeLabel" disabled>
                  <el-radio :label="1">正确</el-radio>
                  <el-radio :label="2">错误</el-radio>
                </el-radio-group>
                <el-radio-group v-if="!isFinished" v-model="judgeAnswer[index]" @change="getJudgeLabel">
                  <el-radio :label="1">正确</el-radio>
                  <el-radio :label="2">错误</el-radio>
                </el-radio-group>
                <div class="analysis" v-if="isFinished">
                  <ul style="padding: 0;">
                    <li><el-result v-if="judgeCheck[index]" icon="success" style="padding:0; align-items: flex-start"></el-result></li>
                    <li><el-result v-if="!judgeCheck[index]" icon="error" style="padding:0; align-items: flex-start"></el-result></li>
                    <li> <el-tag type="success">正确答案</el-tag><span class="right">{{topic[2][index].accept}}</span></li>
                    <li>
                      <el-tag>题目解析</el-tag>&nbsp;&nbsp; {{topic[2][index].solution == null ? '暂无解析': topic[2][index].solution}}
                    </li>
                  </ul>
                </div>
              </div>
            </div>
            <div class="operation">
              <ul class="end">
                <li @click="previous()"><i class="el-icon-arrow-left"></i><span>上一题</span></li>
                <li @click="mark()"><i class="el-icon-view"></i><span>标记</span></li>
                <li @click="next()"><span>下一题</span><i class="el-icon-arrow-right"></i></li>
                <li @click="refresh()"><el-button type="primary">重新开始</el-button></li>
              </ul>
            </div>
          </div>
          </transition>
          <el-dialog
            title="练习结果"
            :visible.sync="dialogVisible"
            width="40%">
            <div style="width: 50%; margin: 0 auto;">
              <el-form :label-position="left" label-width="100px;">
                <el-form-item label="练习科目">
                  <el-tag>{{practiceData.source}}</el-tag>
                </el-form-item>
                <el-form-item label="开始时间">
                 <label>{{start_time}}</label>
                </el-form-item>
                <el-form-item label="结束时间">
                  <label>{{end_time}}</label>
                </el-form-item>
                <el-form-item label="总正确率">
                  <el-progress :text-inside="true" :stroke-width="26" :percentage="acceptRate"></el-progress>
                </el-form-item>
                <el-form-item v-if="topicCount[0]!=0" label="单选题准确率">
                  <el-progress :text-inside="true" :stroke-width="26" :percentage="(singleAccept*100/topicCount[0]).toFixed(2)"></el-progress>
                </el-form-item>
                <el-form-item v-if="topicCount[1]!=0" label="多选题准确率">
                  <el-progress :text-inside="true" :stroke-width="26" :percentage="(multipleAccept*100/topicCount[1]).toFixed(2)"></el-progress>
                </el-form-item>
                <el-form-item v-if="topicCount[2]!=0" label="判断题准确率">
                  <el-progress :text-inside="true" :stroke-width="26" :percentage="(judgeAccept*100/topicCount[2]).toFixed(2)"></el-progress>
                </el-form-item>
              </el-form>
            </div>
            <span slot="footer" class="dialog-footer">
                <el-button type="primary" @click="close()">确 定</el-button>
            </span>
        </el-dialog>
       </div> 
    </div>
  </template>
  
  <script>
  import {mapState} from 'vuex'
  export default {
    data() {
      return {
        dialogVisible: false,
        time: {
          minute: '',
          seconds: ''
        },
        answerScore: 0, //答题总分数
        bg_flag: false, //已答标识符,已答改变背景色
        isFillClick: false, //选择题是否点击标识符
        slider_flag: true, //左侧显示隐藏标识符
        flag: false, //个人信息显示隐藏标识符
        currentType: 1, //当前题型类型  1--单选题  2--多选题  3--判断题
        title: "请选择正确的选项",
        index: 0, //全局index
        userInfo: { //用户信息
          username: null,
          id: null,
        },
        practiceData: { //考试信息
        },
        topic: [[],[],[]],
        topicCount:[0,0,0],
        showQuestion: null, //当前显示题目信息
        showAnswer: [null,null,null,null],//当前题目对应的答案选项
        number: 1, //题号
        multipleAnswer: [], //二维数组保存所有填空题答案
        judgeAnswer: [], //保存所有判断题答案
        singleAnswer: [],  //单选题作答编号, 
        singleisClick: [],
        judgeisClick: [],
        multipleisClick: [],
        singleisMark: [],
        judgeisMark: [],
        multipleisMark: [],
        isFinished: false,
        judgeCheck: [],
        multipleCheck: [],
        singleCheck: [],
        start_time: '',
        end_time: '',
        singleAccept: 0,
        multipleAccept: 0,
        judgeAccept: 0,
        acceptRate: 0
      }
    },
    created() {
      this.getInfo()
      this.getExamData()
    },
    methods: {
      refresh() {
        location.reload(0)
      },
      close() {
        this.dialogVisible = false
      },
      Check1(ans1, ans2) {
        if (ans2 == 1 && ans1 == 'A') return true
        if (ans2 == 2 && ans1 == 'B') return true
        if (ans2 == 3 && ans1 == 'C') return true
        if (ans2 == 4 && ans1 == 'D') return true
        return false
      },
      Check2(ans1, ans2) {
        ans2.sort()
        let s = ""
        for (let i = 0; i < ans2.length; i++) {
          if (ans2[i] == 1) s += 'A'
          else if (ans2[i] == 2) s += 'B'
          else if (ans2[i] == 3) s += 'C'
          else s += 'D'
        }
        return ans1 == s;
      },
      show() { 
        this.dialogVisible = true
      },
      save() {
        if (this.isFinished) {
          this.$message({
            message: '考试已结束!',
            type: 'error'
          });
        } else {
        this.setCookies();
        this.$message({
            message: '已成功保存进度!',
            type: 'success'
          });
        }
      },
      submit() { //计算答题分数
        
      },
      getInfo() {
        this.$store.dispatch('getUserInfo').then(res=>{
          this.userInfo = this.$store.state.user.userinfo
        })
      },
      getExamData() { //获取当前试卷所有信息
        this.start_time = new Date().format('yyyy-MM-dd hh:mm:ss')
        let id = this.$route.query.id //获取路由传递过来的试卷编号
        this.$store.dispatch('getPaperInfoByPracticeId', {id}).then(res => {  
          this.practiceData = this.$store.state.practice.paperinfo.practice //获取考试详情
          let data = this.$store.state.practice.paperinfo.problems
          console.log(this.practiceData)
          this.topicCount[0] = this.practiceData.singleNum;
          this.topicCount[1] = this.practiceData.multipleNum;
          this.topicCount[2] = this.practiceData.tfNum;
          let problem = data[this.index];
          this.showAnswer[0] = problem.a;
          this.showAnswer[1] = problem.b;
          this.showAnswer[2] = problem.c;
          this.showAnswer[3] = problem.d;
          this.showQuestion = problem.description;
          this.time.minute = this.practiceData.totalTime
          this.time.seconds = 0
          this.showTime()
          for (let i = 0; i < this.practiceData.singleNum; i++) {
            this.topic[0].push(data[i]);
            this.singleAnswer.push(null)
            this.singleCheck.push(false)
          }
          for (let i = 0; i < this.practiceData.multipleNum; i++) {
            this.topic[1].push(data[i+this.practiceData.singleNum]);
            this.multipleAnswer.push([])
            this.multipleCheck.push(false)
          }
          for (let i = 0; i < this.practiceData.tfNum; i++) {
            this.topic[2].push(data[i+this.practiceData.singleNum+this.practiceData.multipleNum]);
            this.judgeAnswer.push(null)
            this.judgeCheck.push(false)
          }
          
        })  
      },
      single(index) { //单选题
        this.index = index
        this.isFillClick = true
        this.currentType = 1
        let len = this.topic[0].length
        if(this.index < len) {
          if(this.index <= 0){
            this.index = 0
          }
          let data = this.topic[0][this.index];
          this.showQuestion = data.description //获取题目信息
          this.showAnswer = [data.a,data.b,data.c,data.d]
          this.number = this.index + 1
        } else if(this.index >= len) {
          this.index = 0;
          this.multiple(this.index);
        }
      },
      multiple(index) {//多选题
        this.index = index
        this.isFillClick = true
        this.currentType = 2
        let len = this.topic[1].length
        if(this.index < len) {
          if(this.index < 0){
            this.index = this.topicCount[0]-1;
            this.single(this.index);
          }
          this.title = "请选择正确的选项"
          let data = this.topic[1][this.index];
          this.showQuestion = data.description //获取题目信息
          this.showAnswer = [data.a,data.b,data.c,data.d]
          this.number = this.index + 1 + this.topicCount[0]
        }else if(this.index >= len) {
          this.index = 0;
          this.judge(this.index);
        }
      },
      judge(index) { //判断题
        let len = this.topic[2].length
        this.currentType = 3
        this.index = index
        if(this.index < len) {
          if(this.index < 0){
            this.index = this.topicCount[1] - 1;
            this.multiple(this.index);
          }else {
             let data = this.topic[2][this.index];
            this.showQuestion = data.description //获取题目信息
            this.showAnswer = [data.a,data.b]
            this.number = this.topicCount[0] + this.topicCount[1] + index + 1
          }
        }else if (this.index >= len) {
          this.index = 0
          this.single(this.index)
        }
      },
      getSingleLabel(val) { //获取单选题作答选项
        this.singleAnswer[this.index] = val
        if(val) {   
          this.bg_flag = true
          this.singleisClick[this.index] = true
        }
        /* 保存学生答题选项 */
      },
      getMultipleLabel(val) { //获取多选题作答选项
        this.multipleAnswer[this.index] = val 
        if(val.length != 0) {   
          this.bg_flag = true
          this.multipleisClick[this.index] = true
        } else {
          this.multipleisClick[this.index] = false
        }
        this.multipleAnswer[this.index].sort()
        /* 保存学生答题选项 */
        
      },
      getJudgeLabel(val) {  //获取判断题作答选项
        this.judgeAnswer[this.index] = val
        if(val) {
          this.bg_flag = true
          this.judgeisClick[this.index] = true
        }
      },
      previous() { //上一题
        this.index --
        switch(this.currentType) {
          case 1: 
            this.single(this.index)
            break
          case 2: 
            this.multiple(this.index)
            break
          case 3:
            this.judge(this.index)
            break
        }
      },
      next() { //下一题
        this.index ++
        switch(this.currentType) {
          case 1: 
            this.single(this.index)
            break
          case 2: 
            this.multiple(this.index)
            break
          case 3:
            this.judge(this.index)
            break
        }
      },
      mark() { //标记功能
        switch(this.currentType) {
          case 1:
            this.singleisMark[this.index] ^= 1 //选择题标记
            break
          case 2:
            this.multipleisMark[this.index] ^= 1 //填空题标记
            break
          case 3:
            this.judgeisMark[this.index] ^= 1 //判断题标记
        }
      },
      calcAcceptRate() {
        for (let i = 0; i < this.topicCount[0]; i++) {
          if (this.Check1(this.topic[0][i].accept, this.singleAnswer[i])) {
            this.singleAccept += 1
            this.singleCheck[i] = true
          }
        }
        for (let i = 0; i < this.topicCount[1]; i++) {
          if (this.Check2(this.topic[1][i].accept, this.multipleAnswer[i])) {
            this.multipleAccept += 1
            this.multipleCheck[i] = true
          }
        }
        for (let i = 0; i < this.topicCount[2]; i++) {
          if (this.Check1(this.topic[2][i].accept, this.judgeAnswer[i])) {
            this.judgeAccept += 1
            this.judgeCheck[i] = true
          }
        }
      },
      commit() { //答案提交计算分数
        if(this.time != 0) {
          this.$confirm("考试结束时间未到,是否提前交卷","友情提示",{
            confirmButtonText: '立即交卷',
            cancelButtonText: '再检查一下',
            type: 'warning'
          }).then(() => {
            this.end_time = new Date().format('yyyy-MM-dd hh:mm:ss')
            let end_time = this.end_time
            //提交成绩信息
            let user_id = this.userInfo.id
            let practice_id = this.$route.query.id
            this.calcAcceptRate()
            this.acceptRate = (this.singleAccept+this.multipleAccept+this.judgeAccept)*100/(this.topicCount[0]+this.topicCount[1]+this.topicCount[2])
            this.acceptRate = this.acceptRate.toFixed(2)
            let acceptRate = this.acceptRate
            this.$store.dispatch('addPracticeResult', {user_id,practice_id,end_time,acceptRate}).then(res=>{
              if (res == 'ok') {
                this.$message({type:'success', message: '提交成功!'})
                this.dialogVisible = true
                this.isFinished = true
              }
            })
          }).catch(() => {
            console.log("继续答题")
          })
        }
      },
      showTime() { //倒计时
        setInterval(() => {
          if (this.time.seconds == 0 && this.time.minute == 0) {
            this.commit()
            this.$message({type:'warning', message:'训练时间到，已自动提交!'})
            return
          }
          this.time.seconds --
          if (this.time.seconds < 0) this.time.minute --, this.time.seconds = 59
        },1000)
      }
    },
    computed:mapState(["isPractice"])
  }
  </script>
  
  <style lang="less">
  
  li {
    list-style: none;
  }
  .iconfont.icon-time {
    color: #2776df;
    margin: 0px 6px 0px 20px;
  }
  .analysis {
    margin-top: 20px;
    .right {
      color: #2776df;
      font-size: 18px;
      border: 1px solid #2776df;
      padding: 0px 6px;
      border-radius: 4px;
      margin-left: 20px;
    }
    ul {
      padding: 0;
      li {
        margin-bottom: 10px;
      }
    }
  }
  .analysis span:nth-child(1) {
    font-size: 18px;
  }
  .mark {
    position: absolute;
    width: 4px;
    height: 4px;
    content: "";
    background-color: red;
    border-radius: 50%;
    top: 0px;
    left: 22px;
  }
  .border {
    position: relative;
    border: 1px solid #FF90AA !important;
  }
  .bg {
    background-color: #5188b8 !important;
  }
  .bg1 {
    background-color: rgb(139, 235, 139) !important;
  }
  .bg2 {
    background-color: rgb(238, 91, 91) !important;
  }
  .fill .el-input {
    display: inline-flex;
    width: 150px;
    margin-left: 20px;
    .el-input__inner {
      border: 1px solid transparent;
      border-bottom: 1px solid #eee;
      padding-left: 20px;
    }
  }
  /* slider过渡效果 */
  .slider-fade-enter-active {
    transition: all .3s ease;
  }
  .slider-fade-leave-active {
    transition: all .3s cubic-bezier(1.0, 0.5, 0.8, 1.0);
  }
  .slider-fade-enter, .slider-fade-leave-to {
    transform: translateX(-100px);
    opacity: 0;
  }
  .operation .end li:nth-child(2) {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    background-color: rgb(39, 118, 223);
    border-radius: 50%;
    width: 50px;
    height: 50px;
    color: #fff;
  }
  .operation .end li {
    cursor: pointer;
    margin: 0 100px;
  }
  .operation {
    background-color: #fff;
    border-radius: 4px;
    padding: 10px 0px;
    margin-right: 10px;
  }
  .operation .end {
    display: flex;
    justify-content: center;
    align-items: center;
    color: rgb(39, 118, 223);
  }
  .content .number {
    display: inline-flex;
    justify-content: center;
    align-items: center;
    width: 20px;
    height: 20px;
    background-color: rgb(39, 118, 223);
    border-radius: 4px;
    margin-right: 4px;
  }
  .content {
    padding: 0px 20px;
  }
  .content .topic {
    padding: 20px 0px;
    padding-top: 30px; 
  }
  .right .content {
    background-color: #fff;
    margin: 10px;
    margin-left: 0px;
    height: 470px;
  }
  .content .el-radio-group label {
    color: #000;
    margin: 5px 0px;
  }
  .content .el-radio-group {
    display: flex;
    flex-direction:column;
  }
  .right .title p {
    margin-left: 20px;
  }
  .flexarea {
    display: flex;
  }
  .flexarea .right {
    flex: 1;
  }
  .auto-right {
    margin-left: auto;
    color: #2776df;
    margin-right: 10px;
  }
  .right .title {
    margin-right: 10px;
    padding-right: 30px;
    display: flex;
    margin-top: 10px;
    background-color: #fff;
    height: 50px;
    line-height: 50px;
  }
  .clearfix {
    clear: both;
  }
  .l-bottom .final {
    cursor: pointer;
    display: inline-block;
    text-align: center;
   background-color: rgb(39, 118, 223);
    width: 240px;
    margin: 20px 0px 20px 0px;
    border-radius: 4px;
    height: 30px;
    line-height: 30px;
    color: #fff;
    margin-top: 22px;
  }
  #answer .left .item {
    padding: 0px;
    font-size: 16px;
  }
  .l-bottom {
    border-radius: 4px;
    background-color: #fff;
  }
  .l-bottom .item p {
    margin-bottom: 15px;
    margin-top: 10px;
    color: #000;
    margin-left: 10px;
    letter-spacing: 2px;
  }
  .l-bottom .item li {
    width: 15%;
    margin-left: 5px;
    margin-bottom: 10px;
  }
  .l-bottom .item {
    display: flex;
    flex-direction: column;
  }
  .l-bottom .item ul {
    width: 100%;
    display: flex;
    flex-wrap: wrap;
    padding: 0;
    margin: 0;
    text-align: center;
    text-align: left;
  }
  .l-bottom .item ul li a { 
    position: relative;
    justify-content: center;
    display: inline-flex;
    align-items: center;
    width: 40px;
    height: 40px;
    border-radius: 50%;
    background-color: #fff;
    border: 1px solid #eee;
    text-align: center;
    color: #000;
    font-size: 16px;
  }
  .left .l-top {
    display: flex;
    justify-content: space-around;
    padding: 16px 0px;
    border: 1px solid #eee;
    border-radius: 4px;
    margin-bottom: 10px;
    background-color: #fff;
  }
  .left {
    width: 260px;
    height: 100%;
    margin: 10px 10px 0px 10px;
  }
  .left .l-top li:nth-child(2) a {
    border: 1px solid #eee;
  }
  .left .l-top li:nth-child(3) a {
    background-color: #5188b8;
    border: none;
  }
  .left .l-top li:nth-child(4) a {
    position: relative;
    border: 1px solid #eee;
  }
  .left .l-top li:nth-child(4) a::before {
    width: 4px;
    height: 4px;
    content: " ";
    position: absolute;
    background-color: red;
    border-radius: 50%;
    top: 0px;
    left: 16px;
  }
  .left .l-top li {
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;
  }
  .left .l-top li a {
    display: inline-block;
    padding: 10px;
    border-radius: 50%;
    background-color: #fff;
    border: 1px solid #FF90AA;
  }
  #answer .top {
    background-color: rgb(39, 118, 223);
  }
  #answer .item {
    color: #fff;
    display: flex;
    padding: 20px;
    font-size: 20px;
  }
  #answer .top .item li:nth-child(1) {
    margin-right: 10px;
  }
  #answer .top .item li:nth-child(3) {
    position: relative;
    margin-left: auto;
  }
  #answer {
    padding-bottom: 30px;
  }
  .icon20 {
    font-size: 20px;
    font-weight: bold;
  }
  .item .msg {
    padding: 10px 15px;
    border-radius: 4px;
    top: 47px;
    right: -30px;
    color: #6c757d;
    position: absolute;
    border: 1px solid rgba(0,0,0,.15);
    background-color: #fff;
  }
  .item .msg p {
    font-size: 16px;
    width: 200px;
    text-align: left;
  }
  </style>