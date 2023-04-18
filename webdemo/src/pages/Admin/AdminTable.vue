<template>
  <div>
    <el-row :gutter="20" style="margin-top: 20px">
      <el-col :span="8" v-for="data in cardData.slice(0, 3)" :key="data.title">
        <number-card
          :title="data.title"
          :subtitle="data.subtitle"
          :number="data.number"
        ></number-card>
      </el-col>
    </el-row>
    <el-card style="margin-top: 20px">
      <div ref="lineChart" style="width: 1280px; height: 450px; margin: 0 auto;"></div>
    </el-card>
  </div>
</template>

<script>

import NumberCard from "@/components/Admin/NumberCard.vue";

export default {
  components: {
    NumberCard
  },
  data() {
    return {
      cardData: [
        {
          title: "",
          subtitle: "",
          src: "",
          number: 0,
        },
        {
          title: "",
          subtitle: "",
          src: "",
          number: 0,
        },
        {
          title: "",
          subtitle: "",
          src: "",
          number: 0,
        },
        {
          title: "",
          data: [],
        },
      ],
    }
  },
  mounted() {
    this.$store.dispatch('getSystemInfo').then(res=>{
      let data = this.$store.state.system.systeminfo
      this.cardData = [
        {
          title: "科目总数",
          subtitle: "本系统中的科目总数",
          number: data.subject,
        },
        {
          title: "题目总数",
          subtitle: "本系统中的题目总数",
          number: data.problem,
        },
        {
          title: "考试总数",
          subtitle: "本系统中的考试总数",
          number: data.exam,
        }
      ];
    }),
    this.$store.dispatch('getRecentExamScore').then(res=>{
      let data = this.$store.state.exam.examresult
      data.reverse()
      // 创建图表
      const myChart = this.$echarts.init(this.$refs.lineChart);
      const option = {
          title: {
            text: '所有用户近十次考试记录',
						left: 'center'
          },
          label: {
              show: true,
              formatter: function(params) {
                  return params.value.time + ' ' + params.value.subject + ': ' + params.value.score + '分';
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
                  return `科目：${data.subject}<br/>时间：${data.time} <br/>分数: ${data.value}`;
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
              name: '分数',
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
  }
}
</script>

<style scoped>

</style>