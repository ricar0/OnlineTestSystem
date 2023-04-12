<template>
  <div class="all">
    <p style="margin:0; font-size:25px;">考试管理</p>
    <el-divider></el-divider>
    <el-table style="width: 100%;" :data="pagination.records" border>
      <el-table-column fixed="left" prop="id" label="考试id" width="70"></el-table-column>
      <el-table-column prop="source" label="学科" width="220"></el-table-column>
      <el-table-column prop="description" label="介绍" width="250"></el-table-column>
      <el-table-column prop="totalTime" label="总时长(min)" width="110"></el-table-column>
      <el-table-column prop="totalScore" label="总分" width="100"></el-table-column>
      <el-table-column prop="teacher" label="出卷人" width="150"></el-table-column>
      <el-table-column prop="start_time" label="开始时间" width="200"></el-table-column>
      <el-table-column prop="permission" label="上锁" width="100">
        <template slot-scope="scope">
            <el-tag v-if="scope.row.permission==0" size="small" type="success">否</el-tag>
            <el-tag v-if="scope.row.permission==1" size="small" type="danger">是</el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="number" label="参与人数" width="100">
        <template slot-scope="scope">
            <i class="el-icon-user-solid"></i>x{{scope.row.number}}
        </template>
        
      </el-table-column>
      <el-table-column fixed="right" label="操作" width="200">
        <template slot-scope="scope">
          <el-button @click="checkGrade(scope.row.id)" type="primary" size="small">详情</el-button>
          <el-button @click="deleteById(scope.row.id)" type="danger" size="small">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="pagination.current"
      :page-sizes="[6, 10]"
      :page-size="pagination.size"
      layout="total, sizes, prev, pager, next, jumper"
      :total="pagination.total"
      class="page">
    </el-pagination>
    <!-- 编辑对话框-->
    <el-dialog
      title="编辑考试信息"
      :visible.sync="dialogVisible"
      width="40%"
      :before-close="handleClose">
      <section class="update">
        <el-form inline="true" ref="form" :model="form" label-width="100px">
          <el-form-item label="学科">
            <el-input v-model="form.source"></el-input>
          </el-form-item>
          <el-form-item label="介绍">
            <el-input v-model="form.description"></el-input>
          </el-form-item>
           <el-form-item label="总分">
            <el-input v-model="form.totalScore"></el-input>
          </el-form-item>
          <el-form-item label="总时长">
            <el-input v-model="form.totalTime"></el-input>
          </el-form-item>
          <el-form-item label="单选题数量">
            <el-input v-model="form.singleNum"></el-input>
          </el-form-item>
          <el-form-item label="单选题分值">
            <el-input v-model="form.singleScore"></el-input>
          </el-form-item>
          <el-form-item label="多选题数量">
            <el-input v-model="form.multipleNum"></el-input>
          </el-form-item>
          <el-form-item label="多选题分值">
            <el-input v-model="form.multipleScore"></el-input>
          </el-form-item>
          <el-form-item label="判断题数量">
            <el-input v-model="form.tfNum"></el-input>
          </el-form-item>
          <el-form-item label="判断题分值">
            <el-input v-model="form.tfScore"></el-input>
          </el-form-item>
          <el-form-item label="考试时间">
            <el-date-picker
              @click="Click()"
              v-model="timeList"
              type="datetimerange"
              range-separator="至"
              start-placeholder="开始日期"
              end-placeholder="结束日期">
            </el-date-picker>
          </el-form-item>
          <el-form-item label="是否上锁">
            <el-select v-model="form.permission" placeholder="请选择">
                <el-option :key="1" label="是" :value="1"></el-option>
                <el-option :key="0" label="否" :value="0"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item v-if="form.permission==1" label="密码">
            <el-input v-model="form.password"></el-input>
          </el-form-item>
        </el-form>
      </section>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="submit()">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import { getDateDiff } from '@/utils/time';
export default {
  data() {
    return {
      pagination: {
        records:[],
        current: 1, //当前页
        total: null, //记录条数
        size: 6, //每页条数
      },
      dialogVisible: false, //对话框
      form: {}, 
      timeList: [],
      isClick: '',
      value:''
    };
  },
  created() {
    this.getExamInfo();
  },
  methods: {
    Click() {
      this.isClick = true
    },
    getExamInfo() {
      //分页查询所有考试信息
        let pageSize = this.pagination.size
        let start = this.pagination.size * (this.pagination.current-1)
        this.$store.dispatch('getExamByFilter', {pageSize, start}).then(res=>{
            this.pagination.records = this.$store.state.exam.exam
        })
        this.$store.dispatch('getAllNumber3',{}).then(res=>{
            this.pagination.total = this.$store.state.exam.count
        })
    },
    //改变当前记录条数
    handleSizeChange(val) {
      this.pagination.size = val;
      this.getExamInfo();
    },
    //改变当前页码，重新发送请求
    handleCurrentChange(val) {
      this.pagination.current = val;
      this.getExamInfo();
    },
    checkGrade(id) { //修改考试信息
      this.dialogVisible = true
      this.$store.dispatch('getExamById', id).then(res=>{
        this.form = this.$store.state.exam.examinfo
        console.log(this.form)
        this.timeList.push(this.form.start_time)
        this.timeList.push(this.form.end_time)
      })
      
    },
    deleteById(examId) { //删除当前学生
      this.$confirm("确定删除当前考试吗？删除后无法恢复","Warning",{
        confirmButtonText: '确定删除',
        cancelButtonText: '算了,留着吧',
        type: 'danger'
      }).then(()=> { //确认删除
        let id = examId
        this.$store.dispatch('deleteExam', {id}).then(res=>{
            this.$message({type:'success',message:'删除成功!'});
            this.getExamInfo()
        })
      })
    },
    submit() { //提交更改
      if (this.form.singleNum * this.form.singleScore + this.form.multipleNum * this.form.multipleScore + this.form.tfNum * this.form.tfScore != this.form.totalScore) {
        this.$message({
            message: '分值与设定的总分不符',
            type: 'error'
        })
        return;
      }
      if (this.timeList.length < 2) {
        this.$message({
            message: '考试时间不完整',
            type: 'error'
        })
        return;
      }
      if (typeof this.timeList[0] != 'string')
        this.form.start_time = this.timeList[0].format('yyyy-MM-dd hh:mm:ss')
      if (typeof this.timeList[1] != 'string')
        this.form.end_time = this.timeList[1].format('yyyy-MM-dd hh:mm:ss')
      
      if (getDateDiff(this.form.start_time, this.form.end_time, "minute") != this.form.totalTime) {
        this.$message({
            message: '考试时间与总时长不符',
            type: 'error'
        })
        return;
      }
      this.$store.dispatch('updateExamInfo', this.form).then(res => {
        if(res == "ok") {
          this.$message({
            message: '更新成功',
            type: 'success'
          })
          this.getExamInfo()
          this.dialogVisible = false
        } else {
            this.$message({
                message: res,
                type: 'error'
            })
        }
        this.getStudentInfo()
      })
    },
    handleClose(done) { //关闭提醒
        done()
    },
  }
};
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
.el-table .warning-row {
  background: #000 !important;
}
.el-table .success-row {
  background: #dd5862;
}
</style>