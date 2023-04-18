<template>
    <div class="add">
        <p style="margin:0; font-size:25px;">添加试题</p>
        <el-divider></el-divider>
        <section class="append">
          <ul>
            <li>
              <span>题目类型:&nbsp;</span>
              <el-select v-model="optionValue" placeholder="请选择题型" class="w150">
                <el-option
                  v-for="item in options"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-select>
            </li>
            <li v-if="optionValue == '单选题'">
              <span>学科分类:&nbsp;</span>
              <el-select v-model="postChange.source" placeholder="选择学科" class="w150">
                <el-option
                  v-for="item in source"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-select>
            </li>
            <li v-if="optionValue == '多选题'">
              <span>学科分类:&nbsp;</span>
              <el-select v-model="postMultiple.source" placeholder="选择学科" class="w150">
                <el-option
                  v-for="item in source"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-select>
            </li>
            <li v-if="optionValue == '判断题'">
              <span>学科分类:&nbsp;</span>
              <el-select v-model="postJudge.source" placeholder="选择学科" class="w150">
                <el-option
                  v-for="item in source"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-select>
            </li>
            <li v-if="optionValue == '单选题'">
              <span>难度等级:&nbsp;</span>
              <el-select v-model="postChange.difficulty" placeholder="选择难度等级" class="w150">
                <el-option
                  v-for="item in difficulty"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-select>
            </li>
            <li v-if="optionValue == '多选题'">
              <span>难度等级:&nbsp;</span>
              <el-select v-model="postMultiple.difficulty" placeholder="选择难度等级" class="w150">
                <el-option
                  v-for="item in difficulty"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-select>
            </li>
            <li v-if="optionValue == '判断题'">
              <span>难度等级:&nbsp;</span>
              <el-select v-model="postJudge.difficulty" placeholder="选择难度等级" class="w150">
                <el-option
                  v-for="item in difficulty"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-select>
            </li>
            <li v-if="optionValue == '单选题'">
              <span>知识点章节:&nbsp;</span>
              <el-select v-model="postChange.category" placeholder="选择章节" class="w150">
                <el-option
                  v-for="item in category"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-select>
            </li>
            <li v-if="optionValue == '多选题'">
              <span>知识点章节:&nbsp;</span>
              <el-select v-model="postMultiple.category" placeholder="选择章节" class="w150">
                <el-option
                  v-for="item in category"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-select>
            </li>
            <li v-if="optionValue == '判断题'">
              <span>知识点章节:&nbsp;</span>
              <el-select v-model="postJudge.category" placeholder="选择章节" class="w150">
                <el-option
                  v-for="item in category"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-select>
            </li>
            <li v-if="optionValue == '单选题'">
              <span>正确选项:&nbsp;</span>
              <el-select v-model="postChange.accept" placeholder="选择正确答案" class="w150">
                <el-option
                  v-for="item in rights"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-select>
            </li>
            <li v-if="optionValue == '多选题'">
              <span>正确选项:&nbsp;</span>
              <el-select multiple collapse-tags v-model="Multipleselect" placeholder="选择正确答案" class="w150">
                <el-option
                  v-for="item in rights"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-select>
            </li>
          </ul>
          <!-- 选择题部分 -->
          <div class="change" v-if="optionValue == '单选题'">
            <div class="title">
              <el-tag>题目:</el-tag><span> 在下面的输入框中输入题目</span>
              
              <el-input
                type="textarea"
                rows="4"
                v-model="postChange.description"
                placeholder="请输入题目内容"
                resize="none"
                class="answer">
              </el-input>
            </div>
            <div class="options">
              <el-tag>选项:</el-tag><span> 在下面的输入框中输入选项</span>
              
              <ul>
                <li>
                  <el-tag type="success">A</el-tag>
                  <el-input
                    placeholder="请输入选项A的内容"
                    v-model="postChange.a"
                    clearable="">
                  </el-input>
                </li>
                <li>
                  <el-tag type="success">B</el-tag>
                  <el-input
                    placeholder="请输入选项B的内容"
                    v-model="postChange.b"
                    clearable="">
                  </el-input>
                </li>
                <li>
                  <el-tag type="success">C</el-tag>
                  <el-input
                    placeholder="请输入选项C的内容"
                    v-model="postChange.c"
                    clearable="">
                  </el-input>
                </li>
                <li>
                  <el-tag type="success">D</el-tag>
                  <el-input
                    placeholder="请输入选项D的内容"
                    v-model="postChange.d"
                    clearable="">
                  </el-input>
                </li>
              </ul>
            </div>
            <div class="title">
              <el-tag>解析:</el-tag><span> 在下面的输入框中输入答案解析</span>
              <el-input
                type="textarea"
                rows="4"
                v-model="postChange.solution"
                placeholder="请输入答案解析"
                resize="none"
                class="answer">
              </el-input>
            </div>
            <div class="submit">
              <el-button type="primary" @click="changeSubmit()">立即添加</el-button>
            </div>
          </div>
          <!-- 多选题部分 -->
          <div class="change" v-if="optionValue == '多选题'">
            <div class="title">
              <el-tag>题目:</el-tag>
              在下面的输入框中输入题目
              <el-input
                type="textarea"
                rows="4"
                v-model="postMultiple.description"
                placeholder="请输入题目内容"
                resize="none"
                class="answer">
              </el-input>
            </div>
            <div class="options">
              <el-tag>选项:</el-tag><span> 在下面的输入框中输入选项</span>
              
              <ul>
                <li>
                  <el-tag type="success">A</el-tag>
                  <el-input
                    placeholder="请输入选项A的内容"
                    v-model="postMultiple.a"
                    clearable="">
                  </el-input>
                </li>
                <li>
                  <el-tag type="success">B</el-tag>
                  <el-input
                    placeholder="请输入选项B的内容"
                    v-model="postMultiple.b"
                    clearable="">
                  </el-input>
                </li>
                <li>
                  <el-tag type="success">C</el-tag>
                  <el-input
                    placeholder="请输入选项C的内容"
                    v-model="postMultiple.c"
                    clearable="">
                  </el-input>
                </li>
                <li>
                  <el-tag type="success">D</el-tag>
                  <el-input
                    placeholder="请输入选项D的内容"
                    v-model="postMultiple.d"
                    clearable="">
                  </el-input>
                </li>
              </ul>
            </div>
            <div class="title">
              <el-tag>解析:</el-tag><span> 在下面的输入框中输入答案解析</span>
              <el-input
                type="textarea"
                rows="4"
                v-model="postMultiple.solution"
                placeholder="请输入答案解析"
                resize="none"
                class="answer">
              </el-input>
            </div>
            <div class="submit">
              <el-button type="primary" @click="fillSubmit()">立即添加</el-button>
            </div>
          </div>
          <!-- 判断题 -->
          <div class="change judge" v-if="optionValue == '判断题'">
            <div class="title">
              <el-tag>题目:</el-tag><span>在下面的输入框中输入题目</span>
              <el-input
                type="textarea"
                rows="4"
                v-model="postJudge.description"
                placeholder="请输入题目内容"
                resize="none"
                class="answer">
              </el-input>
            </div>
            <el-tag>选项:</el-tag>
            <div class="judgeAnswer">
              <el-radio v-model="postJudge.accept" label="A">正确</el-radio>
              <el-radio v-model="postJudge.accept" label="B">错误</el-radio>
            </div>
            <div class="title">
              <el-tag>解析:</el-tag><span>在下面的输入框中输入题目解析</span>
              <el-input
                type="textarea"
                rows="4"
                v-model="postJudge.solution"
                placeholder="请输入答案解析"
                resize="none"
                class="answer">
              </el-input>
            </div>
            <div class="submit">
              <el-button type="primary" @click="judgeSubmit()">立即添加</el-button>
            </div>
          </div>
        </section>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        changeNumber: null, //选择题出题数量
        fillNumber: null, //填空题出题数量
        judgeNumber: null, //判断题出题数量
        activeName: 'first',  //活动选项卡
        options: [ //题库类型
          {
            value: '单选题',
            label: '单选题'
          },
          {
            value: '多选题',
            label: '多选题'
          },
          {
            value: '判断题',
            label: '判断题'
          },
        ],
        source: [],
        difficulty: [ //试题难度
          {
            value: '1',
            label: '简单'
          },
          {
            value: '2',
            label: '中等'
          },
          {
            value: '3',
            label: '困难'
          }
        ],
        difficultyValue: '简单',
        rights: [ //正确答案
          {
            value: 'A',
            label: 'A'
          },
          {
            value: 'B',
            label: 'B'
          },
          {
            value: 'C',
            label: 'C'
          },
          {
            value: 'D',
            label: 'D'
          },
        ],
        category: [
          {
            value: '1',
            label: '第一章'
          },
          {
            value: '2',
            label: '第二章'
          },
          {
            value: '3',
            label: '第三章'
          },
          {
            value: '4',
            label: '第四章'
          },
          {
            value: '5',
            label: '第五章'
          },
        ],
        paperId: null,
        optionValue: '单选题', //题型选中值
        postChange: { //单选题提交内容
          source: '', //试卷名称
          difficulty: '', //难度等级选中值 
          accept: '', //正确答案选中值
          description: '', //题目
          solution: '', //解析
          category: '',
          a: '',
          b: '',
          c: '',
          d: '',
        },
        postMultiple: { //多选题提交内容
          source: '', //试卷名称
          difficulty: '', //难度等级选中值 
          accept: '', //正确答案选中值
          description: '', //题目
          solution: '', //解析
          category: '',
          a: '',
          b: '',
          c: '',
          d: '',
        },
        postJudge: { //判断题提交内容
          source: '', //试卷名称
          difficulty: '', //难度等级选中值 
          accept: '', //正确答案选中值
          description: '', //题目
          solution: '', //解析
          category: '',
          a: '',
          b: '',
          c: '',
          d: '',
        },
        Multipleselect: []
      };
    },
    mounted() {
      this.$store.dispatch('getSubjectAll').then(res=>{
        let subject = this.$store.state.subject.subject
        for (let i = 0; i < subject.length; i++) {
          this.source.push({value: subject[i], label: subject[i].source})
        }
      })
    },
    methods: {
      init() {
        location.reload()
      },
      changeSubmit() { //选择题提交
        let problem = this.postChange
        problem.option_num = 4
        problem.label = 'single'
        console.log(problem)
        this.$store.dispatch('addProblem', problem).then(res=>{
          if (res == 'ok') {
            this.$message({type:'success', message: '添加成功!'})
          }
        })
      },
      fillSubmit() { //多选题提交
        let problem = this.postMultiple
        problem.option_num = 4
        problem.label = 'multiple'
        this.Multipleselect = this.Multipleselect.sort()
        let ans = ""
        for (let i = 0; i < this.Multipleselect.length; i++) ans += this.Multipleselect[i]
        problem.accept = ans
        this.$store.dispatch('addProblem', problem).then(res=>{
          if (res == 'ok') {
            this.$message({type:'success', message: '添加成功!'})
          }
        })
      },
      judgeSubmit() { //判断题提交
        let Problem = this.postJudge
        Problem.option_num = 2
        Problem.label = 'tf'
        Problem.a="正确"
        Problem.b="错误"
        this.$store.dispatch('addProblem', Problem).then(res=>{
          if (res == 'ok') {
            this.$message({type:'success', message: '添加成功!'})
          }
        })
      }
    },
  };
  </script>
  
<style lang="less" scoped>
.add {
  margin: 0px 40px;
  .box {
    padding: 0px 20px;
    ul li {
      margin: 10px 0px;
      display: flex;
      align-items: center;
      .el-input {
        width: 6%;
      }
      .w150 {
        margin-left: 20px;
        width: 7%;
      }
    }
  }
  .el-icon-circle-plus {
    margin-right: 10px;
  }
  .icon-daoru-tianchong {
    margin-right: 10px;
  }
  .append {
    margin: 0px 20px;
    ul {
      display: flex;
      align-items: center;
      li {
        margin-right: 20px;
      }
    }
    .change {
      margin-top: 20px;
      padding: 20px 16px;
      background-color: #E7F6F6;
      border-radius: 4px;
      .title {
        padding-left: 6px;
        color: #2f4f4f;
        span:nth-child(1) {
          margin-right: 6px;
        }
        .answer {
          margin: 20px 0px 20px 8px;
        }
        .el-textarea {
          width: 98% !important;
        }
      }
      .options {
        ul {
          display: flex;
          flex-direction: column;
        }
        ul li {
          display: flex;
          justify-content: center;
          align-items: center;
          width: 98%;
          margin: 10px 0px;
          span {
            margin-right: 20px;
          }
        }
      }
      .submit {
        display: flex;
        justify-content: center;
        align-items: center;
      }        
    }
    .fill {
      .fillAnswer {
        display: flex;
        justify-content: center;
        align-items: center;
        span {
          margin-right: 6px;
        }
        .el-input {
          width: 91% !important;
        }
      }
      .analysis {
        margin-top: 20px;
        margin-left: 5px;
      }
    }
    .judge {
      .judgeAnswer {
        margin-left: 20px;
        margin-bottom: 20px;
      }
    }
    .w150 {
      width: 150px;
    }
    li:nth-child(2) {
      display: flex;
      align-items: center;
      justify-content: center;
    }
  }
}
</style>