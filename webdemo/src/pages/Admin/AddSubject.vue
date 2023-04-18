<template>
    <div class="add">
      <p style="margin: 0; font-size: 25px;">学科管理</p>
      <el-divider></el-divider>
      <div class="subject-input">
        <p class="subject-input__label">学科名：</p>
        <el-input
          v-model="add_subject"
          class="subject-input__input"
          placeholder="输入学科名添加新学科"
          prefix-icon="el-icon-edit-outline"
        ></el-input>
        <el-button
          class="subject-input__button"
          type="primary"
          icon="el-icon-success"
          @click="addSubject"
        >添加</el-button>
      </div>
      <div class="subject-input">
        <p class="subject-input__label">学科名：</p>
        <el-select v-model="delete_subject" placeholder="选择科目删除" class="w150">
            <el-option
                v-for="item in subjectList"
                :key="item.value"
                :label="item.label"
                :value="item.value">
            </el-option>
        </el-select>
        <el-button
          class="subject-input__button"
          type="primary"
          icon="el-icon-error"
          @click="deleteSubject"
        >删除</el-button>
      </div>
      <div id="pie-chart" style="width: 70%; height: 400px;"></div>
    </div>
</template>
  
<script>
export default {
    data() {
      return {
        add_subject: '',
        delete_subject: '',
        subjectList: []
      };
    },
    mounted() {
        this.renderChart();
    },
    methods: {
      deleteSubject() {
        this.$store.dispatch('deleteSubject', {source:this.delete_subject}).then(res=>{
            console.log(res)
            if (res == 'ok') {
                this.$message({type:'success', message:'删除科目成功!'})
                this.getSubject()
                this.delete_subject = ''
            } 
        })
      },
      addSubject() {
        this.$store.dispatch('addSubject', {source:this.add_subject}).then(res=>{
            if (res == 'ok') {
                this.$message({type:'success', message:'添加科目成功!'})
                this.getSubject()
                this.add_subject = ''
            } else {
                this.$message({type:'error', message:res})
            }
        })
      },
      getSubject() {
        this.subjectList = []
        this.$store.dispatch('getSubjectAll').then(res=>{
            let data = this.$store.state.subject.subject
            for (let i = 0; i < data.length; i++)
            this.subjectList.push({value: data[i], label: data[i]})
        })
      },
      renderChart() {
        this.getSubject()
        this.$store.dispatch('getSubjectProblemNumber').then(res=>{
            let data = this.$store.state.subject.subjectInfo
            const chart = this.$echarts.init(document.getElementById('pie-chart'));
            const option = {
                title: {
                    text: '题目分配',
                    left: 'center'
                },
                color: ['#5470C6', '#91CC75', '#EE6666', '#F4E001', '#A4D3EE'],
                series: [{
                    type: 'pie',
                    data: data.map(item => ({
                        name: item.subject,
                        value: item.count,
                    })),
                    label: {
                        show: true,
                        formatter: '{b}: {c} ({d}%)',
                        textStyle: {
                            fontSize: 14
                        }
                    }
                }],
                legend: {
                    orient: 'vertical',
                    left: 'right',
                    data: data.map(item => item.subject)
                }
            };
  
            chart.setOption(option);
        })
      },
    },
  };
</script>
  
<style scoped>
.add {
  margin: 0px 40px;
}
.subject-input {
    display: flex;
    align-items: center;
}

.subject-input__label {
    font-weight: bold;
    margin-right: 10px;
}

.subject-input__input {
    width: 300px;
    border-radius: 4px;
    border: 1px solid #dcdfe6;
    font-size: 14px;
}

.subject-input__button {
    margin-left: 10px;
    border-radius: 4px;
    font-size: 14px;
}
</style>
  