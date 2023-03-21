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
                        :key="item.label"
                        effect="plain">
                        {{ item.label }}
                    </el-tag>
                    </div>
                </section>
            </div>
            <el-divider></el-divider>
            <div class="card_body">
                <el-table
                    :data="problem"
                    style="width: 100%">
                    <el-table-column
                        prop="id"
                        label="题目Id"
                        width="100%">
                    </el-table-column>
                    <el-table-column
                        prop="description"
                        label="题面"
                        width="300%">
                    </el-table-column>
                    <el-table-column
                        prop="difficulty"
                        label="难度"
                        width="100%">
                    </el-table-column>
                    <el-table-column
                        prop="num"
                        label="总数"
                        width="200%">
                    </el-table-column>
                    <el-table-column
                        prop="rate"
                        label="通过率"
                        width="300%">
                    </el-table-column>
                </el-table>

            </div>
        </div>
    </div>
</template>


<script>
import Header from '@/components/Student/Header'
export default {
    data() {
        return {
            input:'',
            showTags: true,
            source: [
                { isclick: true, label: '全部' },
                { isclick: false, label: '数据结构' },
                { isclick: false, label: '计算机网络' },
                { isclick: false, label: '操作系统' },
                { isclick: false, label: '思想道德基础和法律修养' },
            ],
            difficulty: [
                { isclick: true, label: '全部' },
                { isclick: false, label: '简单' },
                { isclick: false, label: '中等' },
                { isclick: false, label: '困难' },
            ],
            problem:[]
        }
    },
    components: {
        Header
    }
}
</script>

<style scoped>
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
    height: 1000px;
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

.search {

}
</style>


