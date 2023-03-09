<template>
  <div id="answer">
     <div class="top">
       <ul class="item">
         <li><i class="el-icon-time" ref="toggle" @click="slider_flag = !slider_flag"></i></li>
         <li>{{examData.source}}</li>
         <li @click="flag = !flag">
           <p style="margin:0;"><i class="el-icon-user"></i> {{userInfo.name}}</p>
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
                    <a href="javascript:;" 
                      @click="single(index1)"
                      :class="{'border': index == index1 && currentType == 1,'bg': bg_flag && singleisClick[index1] == true}">
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
                    <a href="javascript:;" 
                      @click="multiple(index2)"
                      :class="{'border': index == index2 && currentType == 2,'bg': bg_flag && multipleisClick[index2] == true}">
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
                    <a href="javascript:;" @click="judge(index3)" :class="{'border': index == index3 && currentType == 3,'bg': bg_flag && judgeisClick[index3] == true}"><span :class="{'mark': judgeisMark[index3] == true}"></span>{{topicCount[0]+topicCount[1]+index3+1}}</a>
                  </li>
                </ul>
              </div>
              <div class="final" @click="commit()">结束考试</div>
            </div>
          </div>
        </transition>  
        <!--右边选择答题区-->
        <transition name="slider-fade">
        <div class="right">
          <div class="title">
            <span style="margin-left:1%;font-size:17px;font-weight:700;">{{title}}</span>
            <i class="iconfont icon-right auto-right"></i>
            <span>全卷共{{topicCount[0]+topicCount[1]+topicCount[2]}}题 &nbsp;&nbsp;<i class="el-icon-time"></i>倒计时：<b>{{time.minutes}}</b>分钟 <b>{{time.seconds}}秒</b></span>
          </div>
          <div class="content">
            <p class="topic"><span class="number">{{number}}</span>{{showQuestion}}</p>
            <div v-if="currentType == 1">
              <el-radio-group v-model="singleAnswer[index]" @change="getSingleLabel">
                <el-radio :label="1">A.{{showAnswer[0]}}</el-radio>
                <el-radio :label="2">B.{{showAnswer[1]}}</el-radio>
                <el-radio :label="3">C.{{showAnswer[2]}}</el-radio>
                <el-radio :label="4">D.{{showAnswer[3]}}</el-radio>
              </el-radio-group>
              <div class="analysis" v-if="isPractice">
                <ul>
                  <li> <el-tag type="success">正确姿势：</el-tag><span class="right">{{reduceAnswer.rightAnswer}}</span></li>
                  <li><el-tag>题目解析：</el-tag></li>
                  <li>{{reduceAnswer.analysis == null ? '暂无解析': reduceAnswer.analysis}}</li>
                </ul>
              </div>
            </div>
            <div v-if="currentType == 2">
              <el-checkbox-group v-model="multipleAnswer[index]" @change="getMultipleLabel">
                <el-checkbox :label="1">A.{{showAnswer[0]}}</el-checkbox><br></br>
                <el-checkbox :label="2">B.{{showAnswer[1]}}</el-checkbox><br></br>
                <el-checkbox :label="3">C.{{showAnswer[2]}}</el-checkbox><br></br>
                <el-checkbox :label="4">D.{{showAnswer[3]}}</el-checkbox><br></br>
              </el-checkbox-group>
              <div class="analysis" v-if="isPractice">
                <ul>
                  <li> <el-tag type="success">正确姿势：</el-tag><span class="right">{{reduceAnswer.rightAnswer}}</span></li>
                  <li><el-tag>题目解析：</el-tag></li>
                  <li>{{reduceAnswer.analysis == null ? '暂无解析': reduceAnswer.analysis}}</li>
                </ul>
              </div>
            </div>
            <div class="judge" v-if="currentType == 3">
              <el-radio-group v-model="judgeAnswer[index]" @change="getJudgeLabel">
                <el-radio :label="1">正确</el-radio>
                <el-radio :label="2">错误</el-radio>
              </el-radio-group>
              <div class="analysis" v-if="isPractice">
                <ul>
                  <li> <el-tag type="success">正确姿势：</el-tag><span class="right">{{topic[2][index].answer}}</span></li>
                  <li><el-tag>题目解析：</el-tag></li>
                  <li>{{topic[2][index].analysis == null ? '暂无解析': topic[2][index].analysis}}</li>
                </ul>
              </div>
            </div>
          </div>
          <div class="operation">
            <ul class="end">
              <li @click="previous()"><i class="el-icon-arrow-left"></i><span>上一题</span></li>
              <li @click="mark()"><i class="el-icon-view"></i><span>标记</span></li>
              <li @click="next()"><span>下一题</span><i class="el-icon-arrow-right"></i></li>
            </ul>
          </div>
        </div>
        </transition>
     </div> 
  </div>
</template>

<script>
import {mapState} from 'vuex'
export default {
  data() {
    return {
      startTime: null, //考试开始时间
      endTime: null, //考试结束时间
      time: {
        minutes: 0,
        seconds: 0
      }, //考试持续时间
      reduceAnswer:[],  //vue官方不支持3层以上数据嵌套,如嵌套则会数据渲染出现问题,此变量直接接收3层嵌套时的数据。
      answerScore: 0, //答题总分数
      bg_flag: false, //已答标识符,已答改变背景色
      isFillClick: false, //选择题是否点击标识符
      slider_flag: true, //左侧显示隐藏标识符
      flag: false, //个人信息显示隐藏标识符
      currentType: 1, //当前题型类型  1--单选题  2--多选题  3--判断题
      title: "请选择正确的选项",
      index: 0, //全局index
      userInfo: { //用户信息
        name: null,
        id: null,
      },
      score: [],  //每种类型分数的总数
      examData: { //考试信息
      },
      topic: [[],[],[]],
      topicCount:[0,0,0],
      showQuestion: null, //当前显示题目信息
      showAnswer: [null,null,null,null],//当前题目对应的答案选项
      number: 1, //题号
      multipleAnswer: [], //二维数组保存所有填空题答案
      judgeAnswer: [], //保存所有判断题答案
      singleAnswer: [],  //单选题作答编号,
      rightAnswer: '',
      singleisClick: [],
      judgeisClick: [],
      multipleisClick: [],
      singleisMark: [],
      judgeisMark: [],
      multipleisMark: []
    }
  },
  created() {
    this.getCookies()
    this.getExamData()
    this.showTime()
  },
  methods: {
    setCookies() {
      let user_id = this.userInfo.id;
      let exam_id = this.$route.query.id
      const {singleAnswer, multipleAnswer, judgeAnswer, singleisClick, singleisMark, multipleisClick, multipleisMark, judgeisClick, judgeisMark, bg_flag} = this
      this.$store.dispatch('setExamCookies', {user_id, exam_id, singleAnswer, multipleAnswer, judgeAnswer, singleisClick, singleisMark, multipleisClick, multipleisMark, judgeisClick, judgeisMark, bg_flag})
    },
    getCookies() {  //获取用户信息信息和答题信息
      this.$store.dispatch('getUserInfo').then(res=>{
        this.userInfo.name = this.$store.state.user.userinfo.username;
        this.userInfo.id = this.$store.state.user.userinfo.id;
        let user_id = this.$store.state.user.userinfo.id;
        let exam_id = this.$route.query.id
        // this.$store.dispatch('initExamCookies', {user_id, exam_id})
        //在每次刷新后先同步信息，再上传
        this.$store.dispatch('getExamCookies', {user_id, exam_id}).then(res=>{
          let data = this.$store.state.exam.examcookies;
          // 选项
          this.singleAnswer = data.singleAnswer
          this.multipleAnswer = data.multipleAnswer
          this.judgeAnswer = data.judgeAnswer
          // 计时
          this.time.minutes = data.minutes
          this.time.seconds = data.seconds
          // 点击
          this.singleisClick = data.singleisClick
          this.multipleisClick = data.multipleisClick
          this.judgeisClick = data.judgeisClick
          // 标记
          this.singleisMark = data.singleisMark
          this.multipleisMark = data.multipleisMark
          this.judgeisMark = data.judgeisMark
          this.bg_flag = data.bg_flag
          // console.log(data)
          this.setCookies();
        });
      })
    },
    getTime(date) { //日期格式化
      let year = date.getFullYear()
      let month= date.getMonth()+ 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
      let day=date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
      let hours=date.getHours() < 10 ? "0" + date.getHours() : date.getHours();
      let minutes=date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes();
      let seconds=date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds();
      // 拼接
      return year+"-"+month+"-"+day+" "+hours+":"+minutes+":"+seconds;
    },
    calcuScore() { //计算答题分数
      
    },
    getExamData() { //获取当前试卷所有信息
      let date = new Date();
      this.startTime = this.getTime(date)
      let id = this.$route.query.id //获取路由传递过来的试卷编号
      this.$store.dispatch('getExamById', id).then(res => {  //通过examCode请求试卷详细信息
        this.examData = this.$store.state.exam.examinfo //获取考试详情
      })
      this.$store.dispatch('getPaperInfoById', {id}).then(res =>{
        let data = this.$store.state.exam.paperinfo
        this.topicCount[0] = data.singleNum;
        this.topicCount[1] = data.multipleNum;
        this.topicCount[2] = data.tfNum;
        let problem = data.problems[this.index];
        this.showAnswer[0] = problem.a;
        this.showAnswer[1] = problem.b;
        this.showAnswer[2] = problem.c;
        this.showAnswer[3] = problem.d;
        this.showQuestion = problem.description;
        for (let i = 0; i < data.singleNum; i++) {
          this.topic[0].push(data.problems[i]);
        }
        for (let i = 0; i < data.multipleNum; i++) {
          this.topic[1].push(data.problems[i+data.singleNum]);
        }
        for (let i = 0; i < data.tfNum; i++) {
          this.topic[2].push(data.problems[i+data.singleNum+data.multipleNum]);
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
    commit() { //答案提交计算分数
      
      if(this.time != 0) {
        this.$confirm("考试结束时间未到,是否提前交卷","友情提示",{
          confirmButtonText: '立即交卷',
          cancelButtonText: '再检查一下',
          type: 'warning'
        }).then(() => {
          console.log("交卷")
          this.setCookies();
          let date = new Date()
          this.endTime = this.getTime(date)
          let answerDate = this.endTime.substr(0,10)
          //提交成绩信息
          let user_id = this.userInfo.id;
          let exam_id = this.$route.query.id;
          this.$store.dispatch('endExam', {user_id, exam_id}).then(res=>{
            this.$router.push({path:'/answerScore', query: {id:this.exam_id}})
            console.log(res)
          })
        }).catch(() => {
          console.log("继续答题")
        })
      }
    },
    showTime() { //倒计时
      setInterval(() => {
        if (this.time.seconds == 0) this.time.minutes -= 1, this.time.seconds = 60;
        this.time.seconds -= 1
        if(this.time.minutes == 15) {
          this.$alert('距离考试结束还剩15分钟!', '提示', {
            confirmButtonText: '确定',
          });
        }
        if(this.time.minutes == 0 && this.time.seconds == 0) {
          this.$alert('考试结束，已自动保存并交卷！', '提示', {
          confirmButtonText: '确定',
            callback: action => {
              this.$router.push('/myexam');
            }
          })
        };
      },1000),
      setInterval(()=>{
        this.setCookies();
      },1000*30*60)
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
  ul li:nth-child(2) {
    margin: 20px 0px;
  }
  ul li:nth-child(3) {
    padding: 10px;
    background-color: #d3c6c9;
    border-radius: 4px;
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
  margin: 20px 0px 20px 10px;
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