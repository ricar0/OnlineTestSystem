<template>
    <div class="all">
      <p style="margin:0; font-size:25px;">学生成绩查询</p>
      <el-divider></el-divider>
      <el-table style="width: 100%;" 
      :header-cell-style="{'text-align':'center'}"
      :cell-style="{'text-align':'center'}"
      :data="pagination.records" border>
        <el-table-column fixed="left" prop="id" label="id" width="100"></el-table-column>
        <el-table-column prop="username" label="姓名" width="100"></el-table-column>
        <el-table-column prop="school" label="学校" width="200"></el-table-column>
        <el-table-column prop="major" label="专业" width="150"></el-table-column>
        <el-table-column prop="sex" label="性别" width="50"></el-table-column>
        <el-table-column prop="phone" label="联系方式" width="150"></el-table-column>
        <el-table-column prop="email" label="邮箱" width="150"></el-table-column>
        <el-table-column prop="idcard" label="身份证号" width="200"></el-table-column>
        <el-table-column prop="grade" label="学历" width="150"></el-table-column>
        <el-table-column fixed="right" label="操作" width="200">
          <template slot-scope="scope">
            <el-button @click="checkGrade(scope.row.id)" type="primary" size="small">查看考试详情</el-button>
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
        title="学生近期参与考试情况"
        :visible.sync="dialogVisible"
        width="50%"
        @opened="opens()"
        :before-close="handleClose">
        <section class="update">
            <div v-if="!isNull" class="chart-container" ref="chart"></div>
            <el-empty v-if="isNull" :image-size="200"></el-empty>
        </section>
        <span slot="footer" class="dialog-footer">
          <el-button @click="close()">关 闭</el-button>
        </span>
      </el-dialog>
    </div>
</template>
  
<script>
import * as echarts from 'echarts';
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
        id: '',
        isNull: false,
      };
    },
    created() {
      this.getStudentInfo();
    },
    methods: {
        close() {
            this.dialogVisible = false
            location.reload(0)
        },
        opens(){
            this.$nextTick(() => {
                this.initChart()
            })
        },
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
      initChart() {
        const myChart = this.$echarts.init(this.$refs.chart);
        this.$store.dispatch('getRecentExamResult',{id:this.id}).then(res=>{
            let data = this.$store.state.exam.result
            if (data.length == 0) this.isNull = true
            else this.isNull = false
            data.reverse()
            // 创建图表
            const option = {
                label: {
                    show: true,
                    formatter: function(params) {
                        return params.value.time + ' ' + params.value.subject + ': ' + params.value.acceptRate + '%';
                    },
                    position: 'top',
                    color: '#333',
                    fontSize: 14,
                },
                tooltip: {
                    trigger: 'axis',
                    backgroundColor: '#333',
                    textStyle: {
                        color: '#fff',
                        fontSize: 14,
                    },
                    formatter: function (params) {
                        const data = params[0].data;
                        return `科目：${data.subject}<br/>时间：${data.time} <br/>准确度: ${data.value}%`;
                    },
                },
                xAxis: {
                    type: 'category',
                    data: ['第1次', '第2次', '第3次', '第4次', '第5次', '第6次', '第7次', '第8次', '第9次', '第10次'],
                    axisLine: {
                        lineStyle: {
                            color: '#999',
                        },
                    },
                    axisTick: {
                        show: false,
                    },
                    axisLabel: {
                        color: '#333',
                        fontSize: 14,
                    },
                },
                yAxis: {
                    name: '准确度%',
                    type: 'value',
                    axisLine: {
                        lineStyle: {
                            color: '#999',
                        },
                    },
                    axisTick: {
                        show: false,
                    },
                    axisLabel: {
                        color: '#333',
                        fontSize: 14,
                    },
                    splitLine: {
                        show: true,
                        lineStyle: {
                            color: ['#eee'],
                        },
                    },
                },
                series: [
                    {
                        name: '成绩',
                        type: 'line',
                        data: data.map(item => {
                            return {
                                value: item.score,
                                time: item.end_time,
                                subject: item.source
                            }
                        }),
                        symbol: 'circle',
                        symbolSize: 8,
                        label: {
                            show: true,
                            formatter: '{c}',
                            color: '#333',
                            fontSize: 14,
                            position: 'top',
                        },
                        itemStyle: {
                            color: '#0099CC',
                            borderColor: '#0099CC',
                            borderWidth: 2,
                        },
                        lineStyle: {
                            width: 3,
                            type: 'solid',
                        },
                    },
                ],
                legend: {
                    show: true,
                    data: ['成绩'],
                    textStyle: {
                        color: '#333',
                        fontSize: 14,
                    },
                    bottom: 10,
                },
            };

            // 使用刚指定的配置项和数据显示图表
            myChart.setOption(option);
        })
      },
      checkGrade(id) { //查询学生成绩
        this.id = id
        this.dialogVisible = true
      },
      
      handleClose(done) { //关闭提醒
            location.reload(0)
            done()
      },
    }
};
</script>
<style lang="less" scoped>
.chart-container {
    width: auto;
    height: 400px;
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