<template>
  <div id="content">
      <Header></Header>
      <div class="box">
         <el-tabs v-model="activeName" @tab-click="handleClick" style="width: 200px;">
            <el-tab-pane label="所有考试" name="all"></el-tab-pane>
            <el-tab-pane label="我的考试" name="my"></el-tab-pane>
        </el-tabs>
          <div class="shadow">
            <div class="card_header">
                <div style="border-box;">
                  <span class="panel-title">考试列表</span>
                  <div class="row1" style="float: right; margin-right: 80px;">
                      <el-row :gutter="20">
                          <el-col :span="5" style="">
                            <span style="vertical-align: middle;">
                            <el-dropdown v-if="activeName=='all'" @command="handleCommand1">
                              <span class="el-dropdown-link">
                                状态<i class="el-icon-caret-bottom"></i>
                              </span>
                              <el-dropdown-menu slot="dropdown">
                                <el-dropdown-item command="all">全部</el-dropdown-item>
                                <el-dropdown-item command="1">未开始</el-dropdown-item>
                                <el-dropdown-item command="2">已开始</el-dropdown-item>
                                <el-dropdown-item command="3">已结束</el-dropdown-item>
                              </el-dropdown-menu>
                            </el-dropdown>
                            </span>
                          </el-col>
                          <el-col :span="5" style="">
                            <span style="vertical-align: middle;">
                            <el-dropdown v-if="activeName=='all'" @command="handleCommand2">
                              <span class="el-dropdown-link">
                                权限<i class="el-icon-caret-bottom"></i>
                              </span>
                              <el-dropdown-menu slot="dropdown">
                                <el-dropdown-item command="all">全部</el-dropdown-item>
                                <el-dropdown-item command="1">私有</el-dropdown-item>
                                <el-dropdown-item command="0">公开</el-dropdown-item>
                              </el-dropdown-menu>
                            </el-dropdown>
                            </span>
                          </el-col>
                          <el-col :span="10">
                              <el-input style="width:200px;" placeholder="输入关键词" suffix-icon="el-icon-search" v-model="input"></el-input>
                          </el-col>
                      </el-row>
                  </div>
                </div>
            </div>
          </div>
          <div class="card_body">
            <div>
              <p v-if="pagination.total==0" class="no-contest">
                <el-empty description="暂无考试"></el-empty>
              </p>
              <ol class="contest-list">
                <li class="item-list" v-for="item in pagination.exam">
                  <div>
                    <el-row justify='space-between' align="middle" type="flex">
                      <el-col :xs="10" :md="3" :lg="2" :sm="4">
                          <img style="width:95px; height: 75px;" src="@/assets/acm.jpg">
                      </el-col>
                      <el-col :xs="10" :sm="16" :md="19" :lg="20">
                        <p class="title">{{item.source}}-{{item.description}} <i v-if="item.permission" class="el-icon-lock" style="color:red;"></i></p>
                        
                        <ul class="detail">
                          <li>
                            <i class="el-icon-date" style="color: rgb(48, 145, 242);"></i>
                            {{item.start_time}}-{{item.end_time}}
                          </li>
                          <li>
                            <i class="el-icon-time" style="color: rgb(48, 145, 242);"></i>
                            {{ item.span }}分钟
                          </li>
                          <li>
                            <el-tag v-if="item.permission" effect="plain" type="danger">
                            私有赛
                            </el-tag>
                            <el-tag v-if="!item.permission" effect="plain" type="success">
                            公开赛
                            </el-tag>
                          </li>
                          <li>
                            <i class="el-icon-user-solid" style="color: rgb(48, 145, 242);"></i>
                            x{{ item.number }}
                          </li>
                        </ul>
                      </el-col>
                      <el-row :xs="4" :sm="4" :md="2" :lg="2" style="text-align: center;">
                        <span>
                          <el-tag v-if="item.state == 1" effect="dark" size="medium">
                            <i class="el-icon-time"></i>
                            未开始
                          </el-tag>
                          <el-tag v-if="item.state == 2" type="success" effect="dark" size="medium">
                            <i class="el-icon-loading"></i>
                            进行中
                          </el-tag>
                          <el-tag v-if="item.state == 3" type="danger" effect="dark" size="medium">
                            <i class="el-icon-time"></i>
                            已结束
                          </el-tag>
                        </span>
                      </el-row>
                    </el-row>
                  </div>
                </li>
              </ol>
            </div>
          </div>
      </div>
      <div class="pagination">
          <el-pagination
              @size-change="handleSizeChange"
              @current-change="handleCurrentChange"
              :current-page="pagination.current"
              :page-sizes="[5, 10, 15]"
              :page-size="pagination.size"
              layout="total, sizes, prev, pager, next"
              :total="pagination.total">
          </el-pagination>
      </div>
  </div>
</template>

<script>
import Header from '@/components/Student/Header'
import {getDateDiff} from '@/utils/time'
export default {
    components: {
        Header
    },
    data() {
        return {
            pagination: {
                exam:[],
                current: 1,
                size: 5,
                total: 0,
            },
            input: null,
            permissionList: [],
            state: null,
            activeName: 'all',
        }
    },
    mounted() {
        this.getExam()
    },
    methods: {
      handleClick(tab, event) {
        if (tab.name == 'all') {
          this.getExam()
        } else {
          this.getMyExam()
        }
      },
      handleExam() {
        let date = new Date().format("yyyy-MM-dd hh:mm:ss")
        for (let i = 0; i < this.pagination.exam.length; i++) {
          this.pagination.exam[i].span = getDateDiff(this.pagination.exam[i].start_time, this.pagination.exam[i].end_time, "minute")
          if (this.pagination.exam[i].start_time > date) {
            this.pagination.exam[i].state = 1;
          } else if (this.pagination.exam[i].end_time > date) {
            this.pagination.exam[i].state = 2;
          } else {
            this.pagination.exam[i].state = 3;
          }
        }
      },
      handleCommand1(command) {
        this.state = null
        if (command == 'all') {
          ;
        } else if (command == '1') {
          this.state = command;
        } else if (command == '2') {
          this.state = command;
        } else {
          this.state = command;
        }
        this.getExam()
      },
      handleCommand2(command) {
        this.permissionList = []
        if (command == 'all') {
          ;
        } else {
          this.permissionList.push(command)
        }
        this.getExam()
      },
      handleSizeChange(val) {
          this.pagination.size = val
          if (this.activeName == 'all') 
            this.getExam()
          else 
            this.getMyExam()
      },
      handleCurrentChange(val) {
          this.pagination.current = val
          if (this.activeName == 'all') 
            this.getExam()
          else 
            this.getMyExam()
      },
      getMyExam() {
        this.$store.dispatch('getUserInfo').then(res=>{
          let user_id = this.$store.state.user.userinfo.id
          let pageSize = this.pagination.pageSize
          let start = this.pagination.size * (this.pagination.current - 1)
          this.$store.dispatch('getMyExam', {user_id,pageSize,start}).then(res=>{
            this.pagination.exam = this.$store.state.exam.myexam
            this.handleExam()
            this.$store.dispatch('getMyExamNumber', {user_id}).then(res=>{
              this.pagination.total = this.$store.state.exam.number
            })
          })
          
        })
        
      },
      getExam() {
          let permissionList = this.permissionList
          let state = this.state
          let pageSize = this.pagination.size
          let time = new Date().format("yyyy-MM-dd hh:mm:ss")
          let start = this.pagination.size * (this.pagination.current - 1)
          this.$store.dispatch('getExamByFilter', {permissionList,pageSize,start,state,time}).then(res=>{
              this.pagination.exam = this.$store.state.exam.exam;
              this.handleExam();
              this.$store.dispatch('getAllNumber3', {permissionList,state,time}).then(res=>{
                  this.pagination.total = this.$store.state.exam.count
              })
              
          })
      },
        
    }
}
</script>


<style lang="less" scoped>
.detail li {
  display: inline-block;
  padding: 10px 0 0 10px;
}
.detail {
  font-size: .875rem;
  padding-left: 0;
  padding-bottom: 10px;
}
.title {
  font-size: 1.25rem;
  padding-left: 8px;
  margin-bottom: 0;
}
.contest-list>li {
  border-left: 4px solid rgb(25, 190, 107);
  padding: 5px;
  margin-left: -20px;
  margin-top: 10px;
  width: 100%;
  border-bottom: 1px solid hsla(0,0%,73.3%,.5);
  list-style: none;
}
/deep/ .el-dropdown {
    display: inline-block;
    position: relative;
    color: #606266;
    font-size: 14px;
}
.panel-title {
    font-size: 21px;
    font-weight: 500;
    padding-top: 10px;
    padding-bottom: 20px;
    line-height: 30px;
}
.el-dropdown-link {
  cursor: pointer;
  // color: #409EFF;
}
.el-icon-arrow-down {
  font-size: 12px;
}
/deep/ .el-tabs__item {
    font-size: 20px;
    font-weight: 700;
}
 /deep/ .el-progress-bar__innerText {
	  color: white !important; 
}
.pagination {
  padding: 2% 0 5% 0;
  .el-pagination {
    display: flex;
    justify-content: center;
  }
}
.filter {
    display: flex;
    align-items: baseline;
    margin-bottom: 0.8em;
}
.sub_title {
    font-size: 18px;
    font-weight: bold;
    line-height: 30px;
    margin-right: 1em;
    font-weight: bolder;
    white-space: nowrap;
    font-size: 16px;
    margin-top: 8px;
}
::v-deep .el-input__inner{
    height: 32px;
    /* width: auto; */
}
::v-deep .el-input__icon {
    line-height: 32px !important;
    font-size: 17px;
}
.shadow {
    box-shadow: 0 2px 12px 0 rgba(0,0,0,.1);
    border-radius: 4px;
    border: 1px solid #ebeef5;
    background-color: #fff;
    overflow: hidden;
    color: #303133;
    transition: .3s;
}
#content {
    background-color: #eff3f5!important;
}
.box {
    width: 90%;
    margin: 90px auto 0 auto;
    border-radius: 4px;
    color: #303133;

    transition: .3s;
    box-sizing: border-box;
    position: relative;
}
.card_body {
    border-radius: 4px;
    border: 1px solid #ebeef5;
    background-color: #fff;
    overflow: hidden;
    color: #303133;
    transition: .3s;
    padding: 20px 20px;
}
.card_header {
  padding: 18px 20px;
  border-bottom: 1px solid #ebeef5;
  box-sizing: border-box;
}
.problem_title {
    font-size: 1.6em;
    font-weight: 500;
    line-height: 30px;
}

.search {

}
</style>