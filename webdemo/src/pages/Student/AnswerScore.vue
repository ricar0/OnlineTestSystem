<template>
  <div class="score">
    <div class="title">
      <p class="name">{{examData.source}}</p>
      <p class="description">(总分：{{examData.totalScore}}分,限时：{{examData.totalTime}}分钟)</p>
      <p class="description">学生：{{username}}</p>
    </div>
    <div class="total">
      <div class="look">
        本次考试成绩
      </div>
      <div class="show">
        <div class="number" :class="{'border': isTransition}">
          <span>{{score}}</span>
          <span>分数</span>
        </div>
      </div>
      <ul class="time">
        <li class="start"><span>开始时间</span> <span>{{startTime}}</span></li>
        <li class="end"><span>结束时间</span> <span>{{endTime}}</span></li>
      </ul>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      isTransition: false, //是否渲染完成
      score: 0, //总分

      startTime: null, //考试开始时间
      endTime: null, //考试结束时间
      examData: {
        source: null,
        totalTime: null,
        totalScore: null
      },
      username: null,
    }
  },
  created() {
    this.transiton()
    this.getScore()
  },
  methods: {
    transiton() {  //一秒后过渡
      setTimeout(() => {
        this.isTransition = true
        this.imgShow = true
      },1000)
    },
    getScore() {
      this.$store.dispatch('getUserInfo').then(res=>{
        let user_id = this.$store.state.user.userinfo.id;
        let exam_id = this.$route.query.id;
        this.username = this.$store.state.user.userinfo.username;
        this.$store.dispatch('getExamById', exam_id).then(res=>{
          this.examData.source = this.$store.state.exam.examinfo.source;
          this.examData.totalScore = this.$store.state.exam.examinfo.totalScore;
          this.examData.totalTime = this.$store.state.exam.examinfo.totalTime;
        })
        this.$store.dispatch('getExamResult', {user_id, exam_id}).then(res=>{
          console.log(this.$store.state.exam.examresult)
          this.startTime = this.$store.state.exam.examresult.start_time;
          this.endTime = this.$store.state.exam.examresult.end_time;
          this.score = this.$store.state.exam.examresult.score;
          this.startTime = this.startTime.substr(0,10)+'  '+this.startTime.substr(11,8)
          this.endTime = this.endTime.substr(0,10)+'  '+this.endTime.substr(11,8)
        })
      })
      this.score = score
      this.startTime = startTime
      this.endTime = endTime
    }
  }
}
</script>

<style lang="less" scoped>
.show {
  display: flex;
  justify-content: center;
  align-items: center;
  img {
    width: 160px;
    height: 160px;
  }
  .img1Transform {
    opacity: 1 !important;
    transform: translateX(30px) !important;  
    transition: all 0.6s ease !important;
  }
  .img2Transform {
    opacity: 1 !important;
    transform: translateX(-30px) !important;  
    transition: all 0.6s ease !important;
  }
  .img1 {
    margin-top: 70px;
    opacity: 0;
    transform: translateX(0px);  
    transition: all 0.6s ease;
  }
  .img2 {
    margin-top: 30px;
    opacity: 0;
    transform: translateX(0px);  
    transition: all 0.6s ease;
  }
}
.time {
  padding: 0px 70px;
  li {
    display: flex;
    justify-content: space-around;
    padding: 10px;
    margin: 20px 0px;
  }
  li:nth-child(1) {
    background-color: #fcf8e3;
  }
  li:nth-child(2) {
    background-color: #e9f5e9;
  }
}
.border {
  border: 6px solid #36aafd !important;
  transition: all 2s ease;
  width: 160px !important;
  height: 160px !important;
  transform: rotate(360deg) !important;
  opacity: 1 !important;
}
.score {
  max-width: 800px;
  margin: 0 auto;
  .title {
    margin: 60px 0px 30px 0px;
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;
    .name {
      font-size: 26px;
      color: inherit;
      font-weight: 500;
    }
    .description {
      font-size: 14px;
      color: #888;
    }
  }
  .total {
    border: 1px solid #dbdbdb;
    background-color: #fff;
    padding: 40px;
    .look {
      border-bottom: 1px solid #dbdbdb;
      padding: 0px 0px 14px 14px;
      color: #36aafd;
    }
    .number {
      opacity: 0;
      border: 6px solid #fff;
      transform: rotate(0deg);
      display: flex;
      justify-content: center;
      align-items: center;
      flex-direction: column;
      margin: 0 auto;
      width: 160px;
      height: 160px;
      border-radius: 50%;
      margin-top: 80px;
      margin-bottom: 20px;
      transition: all 1s ease;
      
      span:nth-child(1) {
        font-size: 36px;
        font-weight: 600;
      }
      span:nth-child(2) {
        font-size: 14px;
      }
    }
  }
}
</style>
