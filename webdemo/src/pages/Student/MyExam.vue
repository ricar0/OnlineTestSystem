<template>
    <!-- <Header></Header> -->
    <div class="block">
    <Header></Header>
    <div id="myExam">
      <li class="order" style="font-size:25px;margin:3% 3%; height: 50%; font-weight:bold; color: dodgerblue;"><i class="el-icon-alarm-clock"> 我的考试</i></li>
        <div class="wrapper">
            <ul class="top" style="height: 40px;">
                <li class="order">
                  <el-badge :value="num1" class="item" type="primary">
                    <span @click="get1()">全部</span>
                  </el-badge>
                </li>
                <li class="order">
                  <el-badge :value="num2" class="item" type="primary">
                    <span @click="get2()">未开始</span>
                  </el-badge>
                </li>
                <li class="order">
                  <el-badge :value="num3" class="item" type="primary">
                    <span @click="get3()">已开始</span>
                  </el-badge>
                </li>
                <li class="order">
                  <el-badge :value="num4" class="item">
                    <span @click="get4()">已过期</span>
                  </el-badge>
                </li>
                
                <li class="search-li"><div class="icon"><input type="text" placeholder="试卷名称" class="search" v-model="key"><i class="el-icon-search"></i></div></li>
                <li><el-button type="primary" @click="search()">搜索试卷</el-button></li>
            </ul>
            <ul class="paper" v-loading="loading">
                <li class="item" v-for="(item,index) in pagination.nowrecords" :key="index">
                    <h4 @click="toExamMsg(item.id)">{{item.source}} <i v-if="item.lock" style="margin:0;"class="el-icon-lock"></i></h4>
                    <p class="name">{{item.source}}-{{item.description}}</p>
                    <span class="name">出卷人：</span> 
                    <span style="color:red;">{{item.teacher}}</span>
                    <div class="info">
                        <i class="el-icon-date"></i><span>{{item.start_time}} 至 {{item.end_time}}</span>
                        <i class="el-icon-bell"></i><span v-if="item.totalTime != null">限时{{item.totalTime}}分钟</span>
                        <i class="el-icon-tickets"></i><span>满分{{item.totalScore}}分</span></br>
                        <i class="el-icon-user"></i><span class="name">参与人数：{{item.number}}</span>
                    </div>
                </li>
            </ul>
            <div class="pagination">
                <el-pagination
                    @size-change="handleSizeChange"
                    @current-change="handleCurrentChange"
                    :current-page="pagination.current"
                    :page-sizes="[3, 5, 10]"
                    :page-size="pagination.size"
                    layout="total, sizes, prev, pager, next, jumper"
                    :total="pagination.total">
                </el-pagination>
            </div>
        </div>
    </div>
    </div>
</template>

<script>
import Header from '@/components/Student/Header'
import Footer from '@/components/Student/Footer'
export default {
    data() {
        return {
            loading: false,
            key: null,
            allExam: null,
            pagination: {
                current: 1,
                total: null,
                size: 3,
                totalrecords: [],
                copy: [],
                nowrecords: [],
                endrecords: [],
                startrecords: [],
                nostartrecords: []
            },
            num1: 0,
            num2: 0,
            num3: 0,
            num4: 0
        }
    },
    methods: {  
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
      handleSizeChange(val) {
        this.pagination.size = val;
        this.pagination.nowrecords = this.pagination.totalrecords.slice((this.pagination.current-1)*this.pagination.size, this.pagination.current*this.pagination.size);
      },
      handleCurrentChange(val) {
        this.pagination.current = val;
        this.pagination.nowrecords = this.pagination.totalrecords.slice((this.pagination.current-1)*this.pagination.size, this.pagination.current*this.pagination.size);
      },
      toExamMsg(id) {
        this.$router.push('/examMsg'+ "?id=" + id);
      },
      search() {
        this.$store.dispatch('getUserInfo').then(res=>{
          let user_id = this.$store.state.user.userinfo.id;
          let source = this.key;
          this.$store.dispatch('searchMyExamByKey', {user_id, source}).then(res=>{
            this.pagination.totalrecords = this.$store.state.search.myexam
            this.pagination.nostartrecords = [];
            this.pagination.startrecords = [];
            this.pagination.endrecords = [];
            let date1 = new Date();
            let date = this.getTime(date1);
            let re = this.pagination.totalrecords;
            for (let i = 0; i < re.length; i++) {
              if (re[i].start_time > date) {
                this.pagination.nostartrecords.push(re[i])
              } else if (re[i].start_time < date && re[i].end_time > date) {
                this.pagination.startrecords.push(re[i]);
              } else {
                this.pagination.endrecords.push(re[i]);
              }
            }  
            this.pagination.copy = this.pagination.totalrecords;
            this.num1 = re.length;
            this.num2 = this.pagination.nostartrecords.length;
            this.num3 = this.pagination.startrecords.length;
            this.num4 = this.pagination.endrecords.length;
          })
        })
      },
      get1() {
        this.pagination.totalrecords = this.pagination.copy;
      },
      get2() {
        this.pagination.totalrecords = this.pagination.nostartrecords;
      },
      get3() {
        this.pagination.totalrecords = this.pagination.startrecords;
      },
      get4() {
        this.pagination.totalrecords = this.pagination.endrecords;
      }
    },
    mounted() {
      this.$store.dispatch('getMyExam').then(res=>{
          this.pagination.totalrecords = this.$store.state.exam.myexam;
          let date1 = new Date();
          let date = this.getTime(date1);
          let re = this.pagination.totalrecords;
          for (let i = 0; i < re.length; i++) {
            if (re[i].start_time > date) {
              this.pagination.nostartrecords.push(re[i])
            } else if (re[i].start_time < date && re[i].end_time > date) {
              this.pagination.startrecords.push(re[i]);
            } else {
              this.pagination.endrecords.push(re[i]);
            }
          }  
          this.pagination.copy = this.pagination.totalrecords;
          this.num1 = re.length;
          this.num2 = this.pagination.nostartrecords.length;
          this.num3 = this.pagination.startrecords.length;
          this.num4 = this.pagination.endrecords.length;
      })
    },
    components: {
        Header,
        Footer
    },
    watch: {
      'pagination.totalrecords': {
        immediate: true,
        handler() {
          this.pagination.total = this.pagination.totalrecords.length;
          this.pagination.nowrecords = this.pagination.totalrecords.slice((this.pagination.current-1)*this.pagination.size, this.pagination.current*this.pagination.size);
        }
      }
    }
}
</script>

<style lang="less" scoped>

ul.top {
    margin: 0 auto;
    padding: 0 auto;
}
.block {
    height: auto;
    width: 100%;
    background-color: #eff3f5!important;
    // position: relative;
    min-height: 80%;
}
li {
    list-style: none;
}
.pagination {
  padding: 3% 0 5% 0;
  .el-pagination {
    display: flex;
    justify-content: center;
  }
}
.paper {
  h4 {
    cursor: pointer;
  }
}
.paper .item a {
  color: #000;
}
.wrapper .top .order {
  cursor: pointer;
  margin-left: 3%;
}
.wrapper .top .order:hover {
  color: #0195ff;
  border-bottom: 2px solid #0195ff;
}
.wrapper .top .order:visited {
  color: #0195ff;
  border-bottom: 2px solid #0195ff;
}
.item .info i {
  margin-right: 2%;
  color: #0195ff;
}
.item .info span {
  margin-right: 5%;
}
.paper .item {
  margin-right: 5%;  
  width: 100%;
  border-radius: 1rem;
  padding: 3% 3% 3% 10%;
  border: 2px solid #eee;
  box-shadow: 3px 3px 4px 10px rgba(217,222,234,0.3);
  transition: all 0.6s ease;
  padding-top: 0;
  padding-bottom: 0;
//   background-color: rgba(120, 200, 255, 0.075);
}
.paper .item:hover {
  box-shadow: 0 0 4px 2px rgba(140, 193, 248, 0.45);
  transform: scale(1.03);
}
.paper .item .info {
  font-size: 15px;
  color: #88949b;
}
.paper .item .name {
  font-size: 16px;
  color: #88949b;
}
.paper * {
  margin: 2% 0;
}
.wrapper .paper {
  display: flex;
  justify-content: space-around;
  flex-wrap: wrap;
}
.top .el-icon-search {
  position: absolute;
  right: 0%;
  top: 30%;
}
.top .icon {
  position: relative;
}
.wrapper .top {
//   border-bottom: 1px solid #eee;
  margin-bottom: 3%;
}

.top .search-li {
  margin-left: 25%;
  margin-right: 10%;
}
.top li {
  display: flex;
  align-items: center;
}
.top .search {
  margin-left: auto;
  padding: 7%;
  border-radius: 4px;
  border: 1px solid #eee;
  box-shadow: inset 0 1px 1px rgba(0,0,0,.075);
  transition: border-color ease-in-out .15s,box-shadow ease-in-out .15s;
}
.top .search:hover {
  color: #0195ff;
  border-color: #0195ff;
}
.wrapper .top {
  display: flex;

}
.wrapper .top li {
  margin-top: 3%;
}
#myExam {
  width: 50%;
  margin: 4% auto 0 auto;
}
#myExam .title {
  margin: 0;
}
#myExam .wrapper {
  background-color: #fff;
  border-radius: 2rem;
//   opacity: 70%;
  box-shadow: 0px 2px 4px 2px rgba(140, 193, 248, 0.45);
  position: sticky;
}

</style>