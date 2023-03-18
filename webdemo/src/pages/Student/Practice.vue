<template>
    <div class="block">
      <Header></Header>
    <div id="myPractice">
        <div class="wrapper">
            <ul class="top" style="height: 40px;">
                <li class="order" style="font-size: 25px;margin:3% 3%; height: 50%; font-weight:bold; color:dodgerblue;"><i class="el-icon-reading"> 我的练习</i></li>
                <li class="search-li"><div class="icon"><input type="text" placeholder="试卷名称" class="search" v-model="key"><i class="el-icon-search"></i></div></li>
                <li><el-button type="primary" @click="search()">搜索试卷</el-button></li>
            </ul>
            <ul class="paper" v-loading="loading">
                <li class="item" v-for="(item,index) in pagination.nowrecords" :key="index">
                    <h4 @click="toExamMsg(item.examCode)">{{item.source}} <i style="margin:0;"class="el-icon-lock"></i></h4>
                    <p class="name">{{item.source}}-{{item.description}}</p>
                    <span class="name">出卷人：</span> 
                    <span style="color:red;">{{item.teacher}}</span>
                    <div class="info">
                        <i class="el-icon-timer"></i><span v-if="item.totalTime != null">限时{{item.totalTime}}分钟</span>
                        <i class="el-icon-tickets"></i><span>满分{{item.totalScore}}分</span>
                        <i class="el-icon-success"></i><span>上次得分：{{item.preScore}}分</span>
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
                nowrecords: []
            }
        }
    },
    methods: {  
      handleSizeChange(val) {
        this.pagination.size = val;
        this.pagination.nowrecords = this.pagination.totalrecords.slice((this.pagination.current-1)*this.pagination.size, this.pagination.current*this.pagination.size);
      },
      handleCurrentChange(val) {
        this.pagination.current = val;
        this.pagination.nowrecords = this.pagination.totalrecords.slice((this.pagination.current-1)*this.pagination.size, this.pagination.current*this.pagination.size);
      }
    },
    mounted() {
      this.$store.dispatch('getMyPractice').then(res=>{
          this.pagination.totalrecords = this.$store.state.practice.mypractice;
          this.pagination.total = this.pagination.totalrecords.length;
          this.pagination.nowrecords = this.pagination.totalrecords.slice((this.pagination.current-1)*this.pagination.size, this.pagination.current*this.pagination.size);
      })
    },
    components: {
        Header
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
    background-image: url('@/assets/background.png');
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
#myPractice .search-li {
  margin-left: auto;
}
.top .search-li {
  margin-left: auto;
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
  margin: 5%;
}
#myPractice {
  width: 50%;
  margin: 4% auto 0 auto;
}
#myPractice .title {
  margin: 0;
}
#myPractice .wrapper {
  background-color: #fff;
  border-radius: 2rem;
//   opacity: 70%;
  box-shadow: 0px 2px 4px 2px rgba(140, 193, 248, 0.45);
  position: sticky;
  
}
</style>