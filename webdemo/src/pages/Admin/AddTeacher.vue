<template>
    <div class="all">
        <p style="margin:0; font-size:25px;">教师审批</p>
        <el-divider></el-divider>
        <el-table style="width: 100%; margin: 0 auto;" :data="pagination.records" border>
            <el-table-column fixed="left" prop="check_id" label="编号" width="200"></el-table-column>
            <el-table-column prop="user_id" label="用户id" width="200"></el-table-column>
            <el-table-column prop="time" label="递交时间" width="300"></el-table-column>
            <el-table-column label="支撑材料" width="600">
                <template slot-scope="scope">
                    <el-image v-for="item in scope.row.material"
                        :key="item"
                        style="width: 80px; height: 80px; margin-left: 10px;"
                        :src="item"
                        fit="fill"
                        :preview-src-list="scope.row.material">
                    </el-image>
                </template>
            </el-table-column>
            <el-table-column fixed="right" label="操作" width="200">
                <template slot-scope="scope">
                    <el-button @click="accept(scope.row.check_id, scope.row.user_id)" type="primary" size="small">同意</el-button>
                    <el-button @click="refuse(scope.row.check_id)" type="danger" size="small">拒绝</el-button>
                </template>
            </el-table-column>
        </el-table>
        <el-pagination
            background
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page="pagination.current"
            :page-sizes="[6, 10]"
            :page-size="pagination.size"
            layout="total, sizes, prev, pager, next, jumper"
            :total="pagination.total"
            class="page">
        </el-pagination>
    </div>
</template>

<script>
export default {
    data() {
        return {
            pagination: {
                records:[],
                current: 1, //当前页
                total: null, //记录条数
                size: 6, //每页条数
            },
        }
    },
    mounted() {
        this.getCheckList()
    },
    methods: {
        accept(check_id, user_id) {
            let state = 1
            this.$store.dispatch('acceptCheck', {check_id, user_id, state}).then(res=>{
                if (res == 'ok') {
                    this.$message({type:'success',message:'操作成功!'});
                    this.getCheckList()
                }
            })
        },
        refuse(check_id) {
            let state = 2
            this.$store.dispatch('acceptCheck', {check_id, state}).then(res=>{
                if (res == 'ok') {
                    this.$message({type:'success',message:'操作成功!'});
                    this.getCheckList()
                }
            })
        },
        handleSizeChange(val) {
            this.pagination.size = val
            this.getCheckList()
        },
        handleCurrentChange(val) {
            this.pagination.current = val
            this.getCheckList()
        },
        getCheckList() {
            let start = this.pagination.size * (this.pagination.current-1)
            let pageSize = this.pagination.size
            let state = 0
            this.$store.dispatch('getCheckList', {start,pageSize,state}).then(res=>{
                this.pagination.records = this.$store.state.role.checkList
                this.$store.dispatch('getCheckListSize',{state}).then(res=>{
                    this.pagination.total = this.$store.state.role.size
                })
            })
        }
    }
}
</script>

<style lang="less" scoped>

.all {
  padding: 0px 40px;
  .page {
    margin-top: 20px;
    display: flex;
    justify-content: center;
    align-items: center;
  }
  .edit {
    margin-left: 20px;
  }
  .el-table tr {
    background-color: #dd5862 !important;
  }
}
</style>