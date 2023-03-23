<template>
    <div class="content">
        <Header></Header>
        <div class="box">
            <div class="box_header">
                <div class="tag">
                    <span>学科：{{problem.source}}</span><br>
                    <span>题型：
                        <el-tag v-if="problem.label=='single'" size="small">单选题</el-tag>
                        <el-tag v-if="problem.label=='multiple'" size="small">多选题</el-tag>
                        <el-tag v-if="problem.label=='tf'" size="small">判断题</el-tag>
                    </span><br>
                    <span>难度：
                        <el-tag v-if="problem.difficulty=='1'" size="small" type="success">简单</el-tag>
                        <el-tag v-if="problem.difficulty=='2'" size="small" type="warning">中等</el-tag>
                        <el-tag v-if="problem.difficulty=='3'" size="small" type="danger">困难</el-tag>
                    </span><br>
                    <span>通过率：
                        {{problem.acnumber}}/{{problem.totalsubmit}}
                    </span><br>
                </div>
            </div>
            <div class="box_body">
                <div class="title">
                    <p style="margin-top: 0;">题目</p>
                </div>
                <div class="problem_content">
                    <p>{{problem.description}}</p>
                </div>
                <div class="title">
                    <p style="margin-top: 0;">选项</p>
                </div>
                <div class="option">
                <el-radio-group v-if="problem.label=='single'" v-model="singleAnswer" @change="getSingleLabel">
                    <el-radio :label="1">A.{{problem.a}}</el-radio>
                    <el-radio :label="2">B.{{problem.b}}</el-radio>
                    <el-radio :label="3">C.{{problem.c}}</el-radio>
                    <el-radio :label="4">D.{{problem.d}}</el-radio>
                </el-radio-group>
                </div>
                <div v-if="isAnswered && opensolution" class="solution">
                    <div class="title">
                        <p style="margin-top: 0;">解析</p>
                    </div>
                    <p v-if="problem.solution">{{problem.solution}}</p>
                    <p v-else> 暂无解析</p>
                </div>
                <div style="text-align: center;">
                    <el-button type="primary" size="small" @click="submit">提交</el-button>
                    <el-button type="primary" size="small" @click="open"><i class="el-icon-s-opportunity"></i>查看解析</el-button>
                </div>
            </div>
        </div>
        
    </div>
</template>

<script>
import Header from '@/components/Student/Header'
export default {
    data() {
        return {
            problem: [],
            singleAnswer: null,
            isAnswered: false,
            opensolution: false,
            ac: []
        }
    },
    components: {
        Header
    },
    mounted() {
        let id = this.$route.query.id;
        this.$store.dispatch('getProblemById', {id}).then(res=>{
            this.problem = this.$store.state.problem.probleminfo
            this.ac = []
            for (let i = 0; i < this.problem.accept.length; i++) {
                if (this.problem.accept[i] == 'A') this.ac.push(1);
                if (this.problem.accept[i] == 'B') this.ac.push(2);
                if (this.problem.accept[i] == 'C') this.ac.push(3);
                if (this.problem.accept[i] == 'D') this.ac.push(4);
            }
        })
    },
    methods: {
        getSingleLabel(val) {
            this.singleAnswer = val
        },
        submit() {
            console.log(this.ac)
            if (this.singleAnswer == null) {
                this.$message.error('请选择答案');
            } else {
                
                if (this.ac[0] == this.singleAnswer) {
                    let id = this.$route.query.id;
                    this.$store.dispatch('accept', {id}).then(res=>{
                        this.$message.success('答案正确');
                    })
                } else {
                    let id = this.$route.query.id;
                    this.$store.dispatch('wrongAnswer', {id}).then(res=>{
                        this.$message.error('答案错误');
                    })
                }
                this.isAnswered = true;
            }
        }, 
        open() {
            if (this.isAnswered)
                this.opensolution = true;
            else {
                this.$message.error('请先作答');
            }
        }
    }
}
</script>

<style scoped>
.content {
    height: 100%;
    width: 100%;
    padding: 0
}
.box {
    margin: 0 auto;
    height: 70%;
    width: 50%;
    background-color: white;
    transform: translateY(-50%);
    top: 50%; 
    position: relative;
    border: 2px solid #ebeef5;
    box-shadow: 1px 1px 5px 5px rgba(206, 228, 228, 0.3);
}
.box_header {
    font-size: 21px;
    font-weight: 500;
    padding: 20px 20px 0px 20px;
    line-height: 30px;
    /* background-color: ; */
    height: 25%;
}
.tag {
    border-radius: 4px;
    border: 1px solid #ddd;
    border-left: 4px solid #3498db;
    background: #fafafa;
    padding: 15px;
    line-height: 1.8;
    margin-bottom: 10px;
    font-size: 17px;
}
.box_body {
    height: auto;
    width: 90%; 
    /* background-color:#3498db; */
    padding-left: 20px;
    padding-right: 20px; 
    padding-bottom: 20px;
}
.title {
    font-size: 20px;
    font-weight: 600;
    margin: 25px 0 8px 0;
    color: #3091f2;
}
</style>