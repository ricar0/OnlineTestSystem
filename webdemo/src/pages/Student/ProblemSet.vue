<template>
    <div id="content">
        <Header></Header>
        <div class="box">
            <div class="card_header">
                <div class="row1" style="margin-right:-10px;margin-bottom:10px;">
                    <el-row :gutter="20">
                        <el-col style="" :span="3"><span class="problem_title">题目列表</span></el-col>
                        <el-col :span="6">
                            <el-input  placeholder="输入关键词" suffix-icon="el-icon-search" v-model="input"></el-input>
                        </el-col>
                        <el-col style="text-align: center;padding-top: 6px;padding-bottom:6px;" :span="3">
                            <el-checkbox style="size:20px;" v-model="showTags"><span style="font-size:15px;">显示标签</span></el-checkbox>
                        </el-col>
                        <el-col :span="8" style="padding-left:40%;">
                            <el-button style="padding: 9px 15px;" type="primary" icon="el-icon-refresh" round>重置</el-button>
                        </el-col>
                    </el-row>
                </div>
                <section class="filter">
                    <b class="sub_title">学科</b>
                    <div class="tag-group">
                    <el-tag style="margin-right: 1em;
                                    margin-top: 0.5em;
                                    font-size: 13px;
                                    cursor:pointer;"
                        v-for="item in source"
                        :key="item.label"
                        effect="plain">
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
                        effect="plain">
                        {{ item.label }}
                    </el-tag>
                    </div>
                </section>
                <section class="filter">
                    <b class="sub_title">题型</b>
                    <div class="tag-group">
                    <el-tag style="margin-right: 1em;
                                    margin-top: 0.5em;
                                    font-size: 13px;
                                    cursor:pointer;"
                        v-for="item in label"
                        :key=item.label
                        effect="plain">
                        {{ item.label }}
                    </el-tag>
                    </div>
                </section>
            </div>
            <!-- <el-divider></el-divider> -->
            <div class="card_body">
                <el-table 
                    :data="pagination.problem"
                    style="width: 100%;"
                    :header-cell-style="{'text-align':'center'}"
                    :cell-style="{'text-align':'center'}">
                    <el-table-column
                        prop="id"
                        label="题目ID"
                        min-width="10%">
                    </el-table-column>
                    <el-table-column
                        prop="description"
                        label="题面"
                        min-width="20%">
                    </el-table-column>
                    <el-table-column
                        prop="label"
                        label="题型"
                        min-width="10%">
                        <template slot-scope="scope">
                            <el-tag v-if="scope.row.label=='single'" size="small">单选题</el-tag>
                            <el-tag v-if="scope.row.difficulty=='multiple'" size="small">多选题</el-tag>
                            <el-tag v-if="scope.row.difficulty=='tf'" size="small">判断题</el-tag>
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
                        v-if="showTags"
                        prop="tag"
                        label="标签"
                        min-width="10%">
                    </el-table-column>
                    <el-table-column
                        prop="num"
                        label="总数"
                        min-width="20%">
                    </el-table-column>
                    <el-table-column
                        prop="rate"
                        label="通过率"
                        min-width="20%">
                        <template slot-scope="scope">
                            <el-progress :text-inside="true" :stroke-width="20" :percentage="0" status="exception"></el-progress>
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
                :page-sizes="[10, 30, 50]"
                :page-size="pagination.size"
                layout="total, sizes, prev, pager, next, jumper"
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
                size: 10,
                total: null,
                current: 1,
                problem: []
            },
            input:'',
            showTags: true,
            source: [
                { color: 'dark', label: '全部' },
                { color: 'plain', label: '数据结构' },
                { color: 'plain', label: '计算机网络' },
                { color: 'plain', label: '操作系统' },
                { color: 'plain', label: '思想道德基础和法律修养' },
            ],
            difficulty: [
                { color: 'dark', label: '全部' },
                { color: 'plain', label: '简单' },
                { color: 'plain', label: '中等' },
                { color: 'plain', label: '困难' },
            ],
            label: [
                { color: 'dark', label: '全部' },
                { color: 'plain', label: '单选题' },
                { color: 'plain', label: '多选题' },
                { color: 'plain', label: '判断题' },
            ],
        }
    },
    components: {
        Header
    },
    mounted() {
        this.$store.dispatch('getProblemAll').then(res=>{
            this.pagination.problem = this.$store.state.problem.problem.slice(0,20);
        })
    },
    methods: {
        handleSizeChange(val) {
            this.pagination.size = val;
        },
        handleCurrentChange(val) {
            this.pagination.current = val;
        }
    }
}
</script>

<style lang="less" scoped>

.pagination {
  padding: 3% 0 5% 0;
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
    border: 1px solid #ebeef5;
    overflow: hidden;
    transition: .3s;
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
}
.search {

}
</style>


