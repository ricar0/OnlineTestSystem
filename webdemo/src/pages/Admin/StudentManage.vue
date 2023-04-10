<template>
  <div class="all">
    <p style="margin:0; font-size:25px;">学生管理</p>
    <el-divider></el-divider>
    <el-table style="width: 100%;" :data="pagination.records" border>
      <el-table-column fixed="left" prop="id" label="id" width="100"></el-table-column>
      <el-table-column prop="username" label="姓名" width="100"></el-table-column>
      <el-table-column prop="school" label="学校" width="200"></el-table-column>
      <el-table-column prop="major" label="专业" width="150"></el-table-column>
      <el-table-column prop="sex" label="性别" width="50"></el-table-column>
      <el-table-column prop="phone" label="联系方式" width="150"></el-table-column>
      <el-table-column prop="email" label="邮箱" width="150"></el-table-column>
      <el-table-column prop="idcard" label="身份证号" width="200"></el-table-column>
      <el-table-column prop="grade" label="学历" width="100"></el-table-column>
      <el-table-column fixed="right" label="操作" width="200">
        <template slot-scope="scope">
          <el-button @click="checkGrade(scope.row.id)" type="primary" size="small">编辑</el-button>
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
      title="编辑学生信息"
      :visible.sync="dialogVisible"
      width="30%"
      :before-close="handleClose">
      <section class="update">
        <el-form ref="form" :model="form" label-width="80px">
          <el-form-item label="姓名">
            <el-input v-model="form.username"></el-input>
          </el-form-item>
          <el-form-item label="学校">
            <el-input v-model="form.school"></el-input>
          </el-form-item>
           <el-form-item label="专业">
            <el-input v-model="form.major"></el-input>
          </el-form-item>
          <el-form-item label="性别">
             <el-select v-model="form.sex" placeholder="请选择">
                <el-option :value="sex1"></el-option>
                <el-option :value="sex2"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="联系方式">
            <el-input v-model="form.phone"></el-input>
          </el-form-item>
          <el-form-item label="邮箱">
            <el-input v-model="form.email"></el-input>
          </el-form-item>
          <el-form-item label="身份证号">
            <el-input v-model="form.idcard"></el-input>
          </el-form-item>
          <el-form-item label="年级">
             <el-select v-model="form.grade" placeholder="请选择">
                <el-option :value="pick1"></el-option>
                <el-option :value="pick2"></el-option>
                <el-option :value="pick3"></el-option>
                <el-option :value="pick4"></el-option>
                <el-option :value="pick5"></el-option>
                <el-option :value="pick6"></el-option>
            </el-select>
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
export default {
  data() {
    return {
      sex1:"男",
      sex2:"女",
      pick1: "小学",
      pick2: "初中",
      pick3: "高中",
      pick4: "大学",
      pick5: "研究生",
      pick6: "博士及以上",
      pagination: {
        records:[],
        current: 1, //当前页
        total: null, //记录条数
        size: 6, //每页条数
      },
      dialogVisible: false, //对话框
      form: {}, 
    };
  },
  created() {
    this.getStudentInfo();
  },
  methods: {
    getStudentInfo() {
      //分页查询所有学生信息
      this.$store.dispatch('getStudentAll').then(res=>{
        this.pagination.records = this.$store.state.user.student;
        this.pagination.total = this.pagination.records.length;
        this.pagination.records = this.pagination.records.slice(this.pagination.size*(this.pagination.current-1),this.pagination.current*this.pagination.size);
      })
    },
    //改变当前记录条数
    handleSizeChange(val) {
      this.pagination.size = val;
      this.getStudentInfo();
    },
    //改变当前页码，重新发送请求
    handleCurrentChange(val) {
      this.pagination.current = val;
      this.getStudentInfo();
    },
    checkGrade(id) { //修改学生信息
      this.dialogVisible = true
      this.$store.dispatch('getUserInfoById', id).then(res=>{
        this.form = this.$store.state.user.user
      })
      console.log(id)
    },
    deleteById(teacherId) { //删除当前学生
      this.$confirm("确定删除当前学生吗？删除后无法恢复","Warning",{
        confirmButtonText: '确定删除',
        cancelButtonText: '算了,留着吧',
        type: 'danger'
      }).then(()=> { //确认删除
        
      })
    },
    submit() { //提交更改
      this.dialogVisible = false
      console.log(this.form)
      this.$store.dispatch('updateUserInfo', this.form).then(res => {
        if(res == "ok") {
          this.$message({
            message: '更新成功',
            type: 'success'
          })
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