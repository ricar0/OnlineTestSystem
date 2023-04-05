<template>
    <div id="content">
        <Header></Header>
        <div class="box">
            <div class="card_header">
                <div class="row1" style="margin-right:-10px;margin-bottom:10px;">
                    <el-row :gutter="20">
                        <el-col :span="3"><span class="problem_title">错题本</span></el-col>
                        <el-col :span="6">
                            <el-input  placeholder="输入关键词" suffix-icon="el-icon-search" v-model="input"></el-input>
                        </el-col>
                    </el-row>
                </div>
            </div>
            <div class="card_body">
                <el-table 
                    :data="pagination.problem"
                    style="width: 100%;"
                    :header-cell-style="{'text-align':'center'}"
                    :cell-style="{'text-align':'center'}">
                    <el-table-column
                        prop="problem.id"
                        label="题目ID"
                        min-width="10%">
                    </el-table-column>
                    <el-table-column
                        prop="problem.description"
                        label="题面"
                        min-width="20%">
                    <template slot-scope="scope">
                        <a style="cursor: pointer;" @click="goToProblem(scope.row.problem.id)">{{scope.row.problem.description}}</a>
                    </template>
                    </el-table-column>
                    <el-table-column
                        prop="problem.label"
                        label="题型"
                        min-width="20%">
                        <template slot-scope="scope">
                            <el-tag v-if="scope.row.problem.label=='single'" size="small">单选题</el-tag>
                            <el-tag v-if="scope.row.problem.label=='multiple'" size="small">多选题</el-tag>
                            <el-tag v-if="scope.row.problem.label=='tf'" size="small">判断题</el-tag>
                        </template>
                    </el-table-column>
                    <el-table-column
                        prop="problem.difficulty"
                        label="难度"
                        min-width="20%">
                        <template slot-scope="scope">
                            <el-tag v-if="scope.row.problem.difficulty==1" type="success" size="small">简单</el-tag>
                            <el-tag v-if="scope.row.problem.difficulty==2" type="warning" size="small">中等</el-tag>
                            <el-tag v-if="scope.row.problem.difficulty==3" type="danger" size="small">困难</el-tag>
                        </template>
                    </el-table-column>
                    <el-table-column
                        prop="last_submit"
                        label="加入时间"
                        min-width="20%">
                    </el-table-column>
                    <el-table-column
                        label="操作"
                        min-width="10%">
                        <template slot-scope="scope"> 
                            <el-button @click="Delete(scope.row.problem.id)" type="danger" icon="el-icon-delete" circle></el-button>
                        </template>
                    </el-table-column>
                </el-table>
            </div>
        </div>
        <div class="pagination">
            <el-pagination
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
                :current-page="pagination.current"
                :page-sizes="[5, 10, 20]"
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
    data() {
        return {
            pagination: {
                size: 5,
                total: null,
                current: 1,
                problem: []
            },
            input:'',
            showTags: true,
        }
    },
    components: {
        Header
    },
    mounted() {
        this.getProblem();
    },
    methods: {
        Delete(problem_id) {
            this.$store.dispatch('getUserInfo').then(res=>{
                let user_id = this.$store.state.user.userinfo.id
                this.$store.dispatch('deleteWrongProblem', {user_id,problem_id}).then(res=>{
                    this.$message({
                        type: 'success',
                        message: '删除成功!'
                    })
                    this.getProblem()
                })
            })
        },
        handleSizeChange(val) {
            this.pagination.size = val;
            this.getProblem();
        },
        handleCurrentChange(val) {
            this.pagination.current = val;
            this.getProblem();
        },
        getProblem() {
            this.$store.dispatch('getUserInfo').then(res=>{
                let user_id = this.$store.state.user.userinfo.id
                let start = (this.pagination.current-1)*this.pagination.size
                let pageSize = this.pagination.size
                this.$store.dispatch('getWrongProblemByUserId',{user_id,start,pageSize}).then(res=>{
                    this.pagination.problem = this.$store.state.wrongbook.wrongproblemAll
                })
                this.$store.dispatch('getWrongProblemNumber', {user_id}).then(res=>{
                    this.pagination.total = this.$store.state.wrongbook.number
                })
            })
        },
        goToProblem(id) {
            this.$router.push({
                path: '/problem',
                query: {id:id}
            })
        },
        
    }
}
</script>

<style lang="less" scoped>

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

#content {
    background-color: #eff3f5!important;
}
.box {
    width: 80%;
    height: auto;
    background-color: #fff;
    margin: 90px auto 0 auto;
    border-radius: 4px;
    color: #303133;
    border: 2px solid #ebeef5;
    overflow: hidden;
    transition: .3s;
    box-shadow: 0 2px 12px 0 rgba(0,0,0,.1);
}
.card_header {
    padding: 18px 20px;
    box-sizing: border-box;
    padding-bottom: 0;
}
.problem_title {
    font-size: 1.6em;
    font-weight: 500;
    line-height: 30px;
}
.card_body {
    height: auto;
    width: 100%;
    padding: 0 0 25px 0;
}
.search {

}
</style>


