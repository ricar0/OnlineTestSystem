<template>
    <div class="all">
      <p style="margin:0; font-size:25px;">考试成绩分析</p>
      <el-divider></el-divider>
      <el-table style="width: 100%;" 
      :header-cell-style="{'text-align':'center'}"
      :cell-style="{'text-align':'center'}"
      :data="pagination.records" border>
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
        <el-table-column fixed="right" label="操作" width="250">
          <template slot-scope="scope">
            <el-button @click="checkRank(scope.row.id)" type="primary" size="small">查看排名</el-button>
            <el-button @click="checkGrade(scope.row.id)" type="primary" size="small">查看考试分析</el-button>
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
      <!-- 编辑对话框-->
      <el-dialog
        title="考试成绩分析"
        :visible.sync="dialogVisible1"
        width="50%"
        @open="opens()"
        :before-close="handleClose1">
        <section class="update">
            <div v-if="!isNull" class="chart-container" ref="chart"></div>
            <el-empty v-if="isNull" :image-size="200"></el-empty>
        </section>
        <span slot="footer" class="dialog-footer">
          <el-button @click="close1()">关 闭</el-button>
        </span>
      </el-dialog>
      <el-dialog
        title="考试排名"
        :visible.sync="dialogVisible2"
        width="50%"
        :before-close="handleClose2">
        <section class="update">
          <el-table :data="examRank" style="width: 100%">
            <el-table-column prop="rank" label="排名" width="60"></el-table-column>
            <el-table-column prop="name" label="姓名" width="120"></el-table-column>
            <el-table-column prop="score" label="分数" width="120"></el-table-column>
        </el-table>
            <el-empty v-if="isNull" :image-size="200"></el-empty>
        </section>
        <span slot="footer" class="dialog-footer">
          <el-button @click="close2()">关 闭</el-button>
        </span>
      </el-dialog>
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
        dialogVisible1: false, //对话框
        dialogVisible2: false, //对话框
        form: {}, 
        timeList: [],
        isClick: '',
        value:'',
        isNull: false,
        id: '',
        category: { //保存分数段
            '90分及以上': 0,
            '80-89分': 0,
            '70-79分': 0,
            '60-69分': 0,
            '60分以下': 0,
        },
        examData: '',
        myChart: '',
        examRank:''
      }
    },
    mounted() {
      this.getExamInfo();
    },
    methods: {
        close1() {
            this.dialogVisible1 = false
            location.reload(0)
        },
        close2() {
            this.dialogVisible2 = false
        },
        handleClose1(done) {
            location.reload(0)
            done()
        },
        handleClose2(done) {
            done()
        },
        Click() {
            this.isClick = true
        },
        opens(){
            this.$nextTick(() => {
                this.initChart()
            })
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
      initChart() {
        this.myChart = this.$echarts.init(this.$refs.chart);
        this.$store.dispatch('getExamScoreResult',{id:this.id}).then(res=>{
            let data = this.$store.state.exam.examresult
            if (data.length == 0) {
                this.isNull = true
            } else {
                this.isNull = false
            }
            // 创建图表
            data.forEach(element => {
                switch(parseInt(element.score / 10)) {
                case 10:
                case 9:
                    this.category["90分及以上"]++
                    break
                case 8:
                    this.category['80-89分']++
                    break
                case 7:
                    this.category["70-79分"]++
                    break
                case 6:
                    this.category['60-69分']++
                    break
                default:
                    this.category['60分以下']++
                }
            });
            let option = {
                title : {
                    text: `${this.examData.source}分数段图`,
                    x:'center',
                    y:'top'
                },
                tooltip : {
                    trigger: 'item',
                    formatter: "{a}：{b} <br/> {c}人 ({d}%)"
                },
                
                legend: {
                    orient: 'vertical',
                    left: 'left',
                    data: ['90分及以上','80-89分','70-79分','60-69分','60分以下']
                },
                series : [
                    {
                        name: '分数段',
                        type: 'pie',
                        radius : '80%',
                        center: ['50%', '60%'],
                        data:[
                            {value:this.category['90分及以上'], name:'90分及以上'},
                            {value:this.category['80-89分'], name:'80-89分'},
                            {value:this.category['70-79分'], name:'70-79分'},
                            {value:this.category['60-69分'], name:'60-69分'},
                            {value:this.category['60分以下'], name:'60分以下'},
                        ],
                        itemStyle: {
                            emphasis: {
                                shadowBlur: 10,
                                shadowOffsetX: 0,
                                shadowColor: 'rgba(0, 0, 0, 0.5)'
                            }
                        },
                        labelLine: {
                            normal: {
                                show: false
                            }
                        },
                        label: {
                          show: false
                        }
                    }
                ]
            };
            // 使用刚指定的配置项和数据显示图表
            this.myChart.setOption(option);
        })
      },
      checkGrade(id) { //查询考试信息成绩 
        this.$store.dispatch('getExamById', id).then(res=>{
            this.id = id
            this.dialogVisible1 = true
            this.examData = this.$store.state.exam.examinfo
        })
      },
      checkRank(id) {
        
        this.$store.dispatch('getExamRank', {id}).then(res=>{
          this.examRank = this.$store.state.exam.examrank
          this.dialogVisible2 = true
        })
      }
    }
};
</script>
<style lang="less" scoped>
.chart-container {
    width: auto;
    height: 500px;
}
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