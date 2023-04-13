<template>
    <div class="all">
        <p style="margin:0; font-size:25px;">教师审批</p>
        <el-divider></el-divider>
        <el-table style="width: 60%; margin: 0 auto;" :data="pagination.records" border>
            <el-table-column fixed="left" prop="id" label="id" width="100"></el-table-column>
            <el-table-column prop="user_id" label="用户id" width="100"></el-table-column>
            <el-table-column label="支撑材料" width="300">
                <template slot-scope="scope">
                    <el-image
                        style="width: 100px; height: 100px"
                        :src="scope.material"
                        fit="fill">
                    </el-image>
                </template>
            </el-table-column>
            <el-table-column fixed="right" label="操作" width="250">
                <template slot-scope="scope">
                <el-button @click="check(scope.row.user_id)" type="danger" size="small">查看详情</el-button>
                <el-button @click="accept(scope.row.id, scope.row.user_id)" type="primary" size="small">同意</el-button>
                <el-button @click="refuse(scope.row.id)" type="danger" size="small">拒绝</el-button>
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
        // this.
    },
    methods() {

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