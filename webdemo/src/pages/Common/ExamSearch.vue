<template>
    <div>
        <Header></Header>
        <div class="container">
            <Nav></Nav>
            <div class="container-right">
                <div class="container-account">
                    <div class="account-body">
                        <span class="account-body-title">近期考试查询</span>
                        <el-divider></el-divider>
                        <div class="chart-container" ref="chart"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import Header from '@/components/Student/Header'
import Nav from '@/components/Student/Nav'
import * as echarts from 'echarts';
export default {
    components: {
        Header,
        Nav
    },
    mounted() {
        this.initChart()
    },
    methods: {
        initChart() {
        // 模拟数据
            this.$store.dispatch('getUserInfo').then(res=>{
                let id = this.$store.state.user.userinfo.id
                this.$store.dispatch('getRecentExamResult',{id}).then(res=>{
                    let data = this.$store.state.exam.result
                    data.reverse()
                    // 创建图表
                    const myChart = this.$echarts.init(this.$refs.chart);
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
            })
        },
    },
}
</script>

<style scoped>
.chart-container {
    width: auto;
    height: 400px;
}
.container {
    position: relative;
    width: auto;
    min-width: 50%;
    min-height: 800px;
    background: transparent;
    margin-top: 80px;
    margin-left: 20%;
}
.container-account {
    width: 70%;
}
.account-body {
    width: 100%;
    padding: 24px 16px 10px;
    min-height: 200px;
    background: #fff;
    font-size: 14px;
}
.account-body-title {
    min-width: 72px;
    height: 24px;
    font-size: 18px;
    font-weight: 600;
    color: #2e2e2e;
    line-height: 24px;
}
.container .container-right {
    min-width: 50%;
    margin-left: 200px;
    min-height: 800px;
}
</style>