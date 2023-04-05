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
                <el-checkbox-group v-if="problem.label=='multiple'" v-model="multipleAnswer" @change="getMultipleLabel">
                    <el-checkbox :label="1">A.{{problem.a}}</el-checkbox><br>
                    <el-checkbox :label="2">B.{{problem.b}}</el-checkbox><br>
                    <el-checkbox :label="3">C.{{problem.c}}</el-checkbox><br>
                    <el-checkbox :label="4">D.{{problem.d}}</el-checkbox><br>
                </el-checkbox-group>
                <el-radio-group v-if="problem.label=='tf'" v-model="tfAnswer" @change="gettfLabel">
                    <el-radio :label="1">A.正确</el-radio>
                    <el-radio :label="2">B.错误</el-radio>
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
        <Popup :visible="visible" :type="type" @close="closePopup" @add-to-mistakes="addToMistakes">
            <span v-if="type === 'success'">
                <el-result icon="success"></el-result>
                恭喜你，答案正确！<br> Accepted!
            </span>
            <span v-if="type === 'error'">
                <el-result icon="error"></el-result>
                很遗憾，答案错误! <br> wrongAnswer!
            </span>
        </Popup>
        <div class="slider_left">  
            <span @click="pre"><i class="el-icon-d-arrow-left">上一题</i></span>
        </div>
        <div class="slider_right">  
            <span @click="next">下一题<i class="el-icon-d-arrow-right"></i></span>
        </div>
    </div>
</template>

<script>
import Header from '@/components/Student/Header'
import Popup from '@/components/Student/Popup'
export default {
    data() {
        return {
            problem: [],
            singleAnswer: null,
            isAnswered: false,
            opensolution: false,
            ac: [],
            multipleAnswer:[],
            tfAnswer: null,
            visible: false,
            type: 'error'
        }
    },
    components: {
        Header,
        Popup
    },
    mounted() {
        this.getProblem()
    },
    methods: {
        init() {
            this.singleAnswer = null,
            this.isAnswered = false,
            this.opensolution = false,
            this.ac = []
            this.multipleAnswer = []
            this.tfAnswer = null
        },
        pre() {
            let id = this.$route.query.id;
            id--;
            this.$store.dispatch('getProblemById',{id}).then(res=>{
                if (res == 'ok') {
                    this.$router.push({
                        path: '/problem',
                        query: {id: id}
                    })
                    this.getProblem()
                } else {
                    this.$message({
                        type: 'error',
                        message: '已经是第一题了'
                    })
                }
            })
        },
        next() {
            let id = this.$route.query.id;
            id++;
            this.$store.dispatch('getProblemById',{id}).then(res=>{
                if (res == 'ok') {
                    this.$router.push({
                        path: '/problem',
                        query: {id: id}
                    })
                    this.getProblem()
                } else {
                    this.$message({
                        type: 'error',
                        message: '已经是最后一题了'
                    })
                }
            })
        },
        getProblem() {
            this.init()
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
        addToMistakes() {
            let problem_id = this.$route.query.id;
            this.$store.dispatch('getUserInfo').then(res=>{
                let user_id = this.$store.state.user.userinfo.id
                let last_submit = new Date().format("yyyy-MM-dd hh:mm:ss")
                this.$store.dispatch('addProblemToWrongBook', {user_id,problem_id,last_submit}).then(res=>{
                    if (res == 'ok') {
                        this.$message({type:'success', message: '成功添加到错题本!'})
                        this.closePopup()
                    } else {
                        this.$message({type:'error', message: '已经在错题本中!'})
                    }
                })
            })
        },
        closePopup() {
            this.visible = false
        },  
        getSingleLabel(val) {
            this.singleAnswer = val
        },
        getMultipleLabel(val) {
            val.sort()
            this.multipleAnswer = val;
        },
        submit() {
            if (this.problem.label == 'single') {//单选题
                if (this.singleAnswer == null) {
                    this.$message.error('请选择答案');
                } else {
                    if (this.ac[0] == this.singleAnswer) {
                        this.accept()
                    } else {
                        this.wrongAnswer()
                    }
                    this.isAnswered = true;
                }
            } else if (this.problem.label == 'multiple') {//多选
                if (this.multipleAnswer.length == 0) {
                    this.$message.error('请选择答案')
                } else {
                    if (JSON.stringify(this.ac) == JSON.stringify(this.multipleAnswer)) {
                        this.accept()
                    } else {
                        this.wrongAnswer()
                    }
                    this.isAnswered = true;
                }
            } else {
                if (this.tfAnswer == null) {
                    this.$message.error('请选择答案')
                } else {
                    if (this.ac[0] == this.tfAnswer) {
                        this.accept()
                    } else {
                        this.wrongAnswer()
                    }
                    this.isAnswered = true;
                }
            }
        }, 
        open() {
            if (this.isAnswered)
                this.opensolution = true;
            else {
                this.$message.error('请先作答');
            }
        },
        accept() {
            let id = this.$route.query.id;
            this.$store.dispatch('accept', {id}).then(res=>{
                this.type = 'success'
                this.visible = true
            })
        },
        wrongAnswer() {
            let id = this.$route.query.id;
            this.$store.dispatch('wrongAnswer', {id}).then(res=>{
                this.type = 'error'
                this.visible = true
            })
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
    height: auto;
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
.slider_left {
    position: fixed;
    left: 50px;
    top: 120px;
    color: #3091f2;
    font-size: 20px;
    cursor: pointer;
}
.slider_right {
    position: fixed;
    right: 50px;
    top: 120px;
    color: #3091f2;
    font-size: 20px;
    cursor: pointer;
}
</style>