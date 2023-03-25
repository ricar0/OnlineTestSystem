<template>
  <div id="content">
      <Header></Header>
      <div class="box">
          <div class="card_header">
              <div class="row1" style="margin-right:-10px;margin-bottom:10px;">
                  <el-row :gutter="20">
                      <el-col style="" :span="3"><span class="problem_title">搜索练习</span></el-col>
                      <el-col :span="6">
                          <el-input  placeholder="输入关键词" suffix-icon="el-icon-search" v-model="input"></el-input>
                      </el-col>
                      <el-col :span="8" style="padding-left:40%;">
                          <el-button style="padding: 9px 15px;" type="primary" icon="el-icon-refresh" @click="reset()" round>重置</el-button>
                      </el-col>
                  </el-row>
              </div>
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
                      <a style="cursor: pointer;" @click="goToProblem(scope.row.id)">{{scope.row.description}}</a>
                  </template>
                  </el-table-column>
                  <el-table-column
                      prop="lock"
                      label="权限"
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
                      prop="author"
                      label="出题人"
                      min-width="10%">
                  </el-table-column>
                  <el-table-column
                      prop="totalsubmit"
                      label="参与人数"
                      min-width="20%">
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
      <div class="pagination">
          <el-pagination
              @size-change="handleSizeChange"
              @current-change="handleCurrentChange"
              :current-page="pagination.current"
              :page-sizes="[10, 30, 50]"
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
        practice:[]
      },
      input: null,
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
    }
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
    box-shadow: 1px 1px 5px 5px rgba(206, 228, 228, 0.3);
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