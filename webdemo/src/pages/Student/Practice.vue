<template>
  <div id="content">
      <Header></Header>
      <div class="box">
         <el-tabs v-model="activeName" @tab-click="handleClick" style="width: 200px;">
            <el-tab-pane label="所有练习" name="all"></el-tab-pane>
            <el-tab-pane label="我的练习" name="my"></el-tab-pane>
        </el-tabs>
          <div class="shadow">
            <div class="card_body">
                <div class="row1" style="margin-right:-10px;margin-bottom:10px;">
                    <el-row :gutter="20">
                        <el-col style="" :span="2"><span class="problem_title">训练列表</span></el-col>
                        <el-col :span="6">
                            <el-input placeholder="输入关键词" suffix-icon="el-icon-search" v-model="input"></el-input>
                        </el-col>
                        <el-col style="text-align: center;padding-top: 6px;padding-bottom:6px;" :span="3">
                            <el-checkbox style="size:20px;" v-model="showSource"><span style="font-size:15px;">显示学科</span></el-checkbox>
                        </el-col>
                        <el-col :span="4" style="padding-left:40%;">
                            <el-button style="padding: 9px 15px;" type="primary" icon="el-icon-refresh" @click="reset()" round>重置</el-button>
                        </el-col>
                    </el-row>
                </div>
                <div v-if="!showMyPractice">
                <section class="filter">
                    <b class="sub_title">权限</b>
                    <div class="tag-group">
                    <el-tag style="margin-right: 1em;
                                    margin-top: 0.5em;
                                    font-size: 13px;
                                    cursor:pointer;"
                        v-for="item in permission"
                        :key="item.label"
                        :effect="item.color"
                        :type="item.type"
                        @click="changePermission(item.id)">
                        {{ item.label }}
                    </el-tag>
                    </div>
                </section>
                <section class="filter">
                    <b class="sub_title">学科</b>
                    <div class="tag-group">
                    <el-tag style="margin-right: 1em;
                                    margin-top: 0.5em;
                                    font-size: 13px;
                                    cursor:pointer;"
                        v-for="item in source"
                        :key="item.label"
                        :effect="item.color"
                        @click="changeSource(item.id)">
                        {{ item.label }}
                    </el-tag>
                    </div>
                </section>
                <section class="filter">
                    <b class="sub_title">难度</b>
                    <div class="tag-group">
                    <el-tag style="margin-right: 1em;
                                    margin-top: 0.5em;
                                    font-size: 13px;
                                    cursor:pointer;"
                        v-for="item in difficulty"
                        :key=item.label
                        :effect="item.color"
                        @click="changeDifficulty(item.id)">
                        {{ item.label }}
                    </el-tag>
                    </div>
                </section>
                </div>
            </div>
          </div>
          <div class="shadow" style="margin-top: 2em;">
            <div class="card_body">
                <el-table 
                    :data="pagination.practice"
                    style="width: 100%;"
                    :header-cell-style="{'text-align':'center'}"
                    :cell-style="{'text-align':'center'}">
                    <el-table-column
                        prop="id"
                        label="编号"
                        min-width="10%">
                    </el-table-column>
                    <el-table-column
                        prop="description"
                        label="标题"
                        min-width="20%">
                    <template slot-scope="scope">
                        <a style="cursor: pointer; color:#409eff;" @click="goToProblem(scope.row.id)">{{scope.row.description}}</a>
                    </template>
                    </el-table-column>
                    <el-table-column
                        prop="permission"
                        label="权限"
                        min-width="10%">
                        <template slot-scope="scope">
                            <el-tag v-if="scope.row.permission==0" size="small" type="success">公开训练</el-tag>
                            <el-tag v-if="scope.row.permission==1" size="small" type="danger">私有训练</el-tag>
                        </template>
                    </el-table-column>
                    <el-table-column
                        prop="difficulty"
                        label="难度"
                        min-width="10%">
                        <template slot-scope="scope">
                            <el-tag v-if="scope.row.difficulty==1" type="success" size="small">简单</el-tag>
                            <el-tag v-if="scope.row.difficulty==2" type="warning" size="small">中等</el-tag>
                            <el-tag v-if="scope.row.difficulty==3" type="danger" size="small">困难</el-tag>
                        </template>
                    </el-table-column>
                    <el-table-column
                        prop="author"
                        label="出题人"
                        min-width="10%">
                    </el-table-column>
                    <el-table-column
                        v-if="showSource"
                        prop="source"
                        label="学科"
                        min-width="10%">
                        <template slot-scope="scope">
                            <el-tag size="small">{{scope.row.source}}</el-tag>
                        </template>
                    </el-table-column>
                    <el-table-column
                        prop="totalsubmit"
                        label="参与人数"
                        min-width="10%">
                    </el-table-column>
                    <el-table-column
                        prop="rate"
                        label="平均分"
                        min-width="20%">
                        <template slot-scope="scope">
                            <el-progress v-if="scope.row.per < 30" :text-inside="true" :stroke-width="20" :percentage="scope.row.per" status="exception"></el-progress>
                            <el-progress v-if="scope.row.per < 50 && scope.row.per >= 30" :text-inside="true" :stroke-width="20" :percentage="scope.row.per" status="warning"></el-progress>
                            <el-progress v-if="scope.row.per < 70 && scope.row.per >= 50" :text-inside="true" :stroke-width="20" :percentage="scope.row.per"></el-progress>
                            <el-progress v-if="scope.row.per >= 70" :text-inside="true" :stroke-width="20" :percentage="scope.row.per" status="success"></el-progress>
                        </template>
                    </el-table-column>
                </el-table>
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
                practice:[],
                current: 1,
                size: 5,
                total: null,
            },
            showSource: true,
            input: null,
            showMyPractice: false,
            source: [
                { id: 0, color: 'dark', label: '全部' },
                { id: 1, color: 'plain', label: '数据结构' },
                { id: 2, color: 'plain', label: '计算机网络' },
                { id: 3, color: 'plain', label: '操作系统' },
                { id: 4, color: 'plain', label: '思想道德基础和法律修养' },
            ],
            difficulty: [
                { id: 0, color: 'dark', label: '全部' },
                { id: 1, color: 'plain', label: '简单' },
                { id: 2, color: 'plain', label: '中等' },
                { id: 3, color: 'plain', label: '困难' },
            ],
            permission: [
                { id: 0, color: 'dark', label: '全部' },
                { id: 1, color: 'plain', type: 'success' ,label: '公开训练' },
                { id: 2, color: 'plain', type: 'danger', label: '私有训练' },
            ],
            permissionList: [],
            sourceList: [],
            difficultyList: [],
            activeName: 'all'
        }
    },
    mounted() {
        this.getPractice()
    },
    methods: {
        handleClick(tab, event) {
            if (tab.name == 'my') {
                this.showMyPractice = true
                this.$store.dispatch('getUserInfo').then(res=>{
                    let user_id = this.$store.state.user.userinfo.id;
                    this.$store.dispatch('getMyPractice', {user_id}).then(res=>{
                        this.pagination.practice = this.$store.state.practice.mypractice
                        this.pagination.total = this.pagination.practice.length
                    })
                })
            } else {
                this.showMyPractice = false
                this.getPractice()
            }
        },
        handleSizeChange(val) {
            this.pagination.size = val
            this.getPractice()
        },
        handleCurrentChange(val) {
            this.pagination.current = val
            this.getPractice()
        },
        getPractice() {
            let permissionList = this.permissionList
            let sourceList = this.sourceList
            let difficultyList = this.difficultyList
            let pageSize = this.pagination.size
            let start = this.pagination.size * (this.pagination.current - 1)
            this.$store.dispatch('getPracticeByFilter', {permissionList,sourceList,difficultyList,pageSize,start}).then(res=>{
                this.pagination.practice = this.$store.state.practice.practice;
                this.$store.dispatch('getAllNumber2', {permissionList,sourceList,difficultyList}).then(res=>{
                    this.pagination.total = this.$store.state.practice.count
                })
                
            })
        },
        resetSource() {
            this.sourceList = []
            for (let i = 0; i < this.source.length; i++) {
                this.source[i].color = 'plain'
            }
        },
        resetDifficulty() {
            this.difficultyList = []
            for (let i = 0; i < this.difficulty.length; i++) {
                this.difficulty[i].color = 'plain'
            }
        },
        resetPermission() {
            this.permissionList = []
            for (let i = 0; i < this.permission.length; i++) {
                this.permission[i].color = 'plain'
            }
        },
        changeSource(id) {
            this.resetSource()
            this.source[id].color = 'dark'
            if (this.source[id].label == '全部') {
                this.getPractice()
            } else {
                this.sourceList.push(this.source[id].label)
                this.getPractice()
            }
            
        },
        changeDifficulty(id) {
            this.resetDifficulty()
            this.difficulty[id].color = 'dark'
            if (this.difficulty[id].label == '全部') {
                this.getPractice()
            } else {
                if (this.difficulty[id].label == '简单')
                    this.difficultyList.push(1)
                else if (this.difficulty[id].label == '中等') {
                    this.difficultyList.push(2)
                } else {
                    this.difficultyList.push(3)
                }
                this.getPractice()
            }
        },
        changePermission(id) {
            this.resetPermission()
            this.permission[id].color = 'dark'
            if (this.permission[id].label == '全部') {
                this.getPractice()
            } else {
                if (this.permission[id].label == '公开训练') {
                    this.permissionList.push(0)
                }
                else {
                    this.permissionList.push(1)
                }
                this.getPractice()
            } 
        },
        reset() {
            this.resetSource()
            this.resetDifficulty()
            this.resetPermission()
            this.source[0].color = 'dark'
            this.difficulty[0].color = 'dark'
            this.permission[0].color = 'dark'
            this.getPractice()
        }
    }
}
</script>


<style lang="less" scoped>
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
    width: 80%;
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
.problem_title {
    font-size: 1.6em;
    font-weight: 500;
    line-height: 30px;
}

.search {

}
</style>