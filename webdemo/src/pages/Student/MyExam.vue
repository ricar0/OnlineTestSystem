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
                            <el-dropdown>
                              <span class="el-dropdown-link">
                                状态<i class="el-icon-caret-bottom"></i>
                              </span>
                              <el-dropdown-menu slot="dropdown">
                                <el-dropdown-item>全部</el-dropdown-item>
                                <el-dropdown-item>未开始</el-dropdown-item>
                                <el-dropdown-item>已开始</el-dropdown-item>
                                <el-dropdown-item>已结束</el-dropdown-item>
                              </el-dropdown-menu>
                            </el-dropdown>
                            </span>
                          </el-col>
                          <el-col :span="5" style="">
                            <span style="vertical-align: middle;">
                            <el-dropdown>
                              <span class="el-dropdown-link">
                                权限<i class="el-icon-caret-bottom"></i>
                              </span>
                              <el-dropdown-menu slot="dropdown">
                                <el-dropdown-item>全部</el-dropdown-item>
                                <el-dropdown-item>私有</el-dropdown-item>
                                <el-dropdown-item>公开</el-dropdown-item>
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
              <p v-if="pagination.total==1" class="no-contest">
                <el-empty description="暂无考试"></el-empty>
              </p>
              <ol class="contest-list">
                <li class="item-list" v-for="item in pagination.exam">
                  <div style="align-items: center;justify-content: space-between;display: flex;">
                    <el-row :gutter="100">
                      <el-col :span="5">
                        <img style="width:95px;" src="@/assets/logo.png">
                      </el-col>
                      <el-col :span="10">
                        <p class="title">{{item.source}}-{{item.description}}</p>
                        <ul class="detail">
                          <li><i class="el-icon-date"></i></li>
                        </ul>
                      </el-col>
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
export default {
    components: {
        Header
    },
    data() {
        return {
            pagination: {
                exam:[
                  {
                    description: '2021年第二学期期末考试',
                    source: '数据库原理'
                  }
                ],
                current: 1,
                size: 5,
                total: 0,
            },
            input: null,
            
            activeName: 'all'
        }
    },
    mounted() {
        // this.getPractice()
    },
    methods: {
        
        handleSizeChange(val) {
            this.pagination.size = val
        },
        handleCurrentChange(val) {
            this.pagination.current = val
        },
        // getExam() {
        //     let permissionList = this.permissionList
        //     let sourceList = this.sourceList
        //     let difficultyList = this.difficultyList
        //     let pageSize = this.pagination.size
        //     let start = this.pagination.size * (this.pagination.current - 1)
        //     this.$store.dispatch('getPracticeByFilter', {permissionList,sourceList,difficultyList,pageSize,start}).then(res=>{
        //         this.pagination.practice = this.$store.state.practice.practice;
        //         this.$store.dispatch('getAllNumber2', {permissionList,sourceList,difficultyList}).then(res=>{
        //             this.pagination.total = this.$store.state.practice.count
        //         })
                
        //     })
        // },
        
    }
}
</script>


<style lang="less" scoped>
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