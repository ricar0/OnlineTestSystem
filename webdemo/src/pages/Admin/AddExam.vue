<template>
    <div class="add">
        <el-tabs v-model="activeName">
            <el-tab-pane name="first">
                <span slot="label"><i class="el-icon-circle-plus"></i> 手动组卷</span>
                <div class="box">
                    <el-steps :active="active" finish-status="success">
                        <el-step title="填写考试信息"></el-step>
                        <el-step title="填写试卷信息"></el-step>
                        <el-step title="组卷完成"></el-step>
                    </el-steps>
                    <div v-if="step==1">
                        <p>考试信息</p>
                        <div class="content1">
                            <el-form ref="form" :model="exam" label-position="left" label-width="100px">
                                <el-form-item label="科目" prop="source">
                                    <el-input v-model="exam.source" placeholder="请输入考试科目"></el-input>
                                </el-form-item>
                                <el-form-item label="描述" prop="description">
                                    <el-input type="textarea" v-model="exam.description" placeholder="请输入考试描述"></el-input>
                                </el-form-item>
                                <el-form-item label="总时长(分钟)" prop="totalTime">
                                    <el-input-number v-model="exam.totalTime" :min="1" :max="600" suffix="分钟"></el-input-number>
                                </el-form-item>
                                <el-form-item label="开始时间" prop="start_time">
                                    <el-date-picker type="datetime" v-model="exam.start_time" placeholder="请选择考试开始时间"></el-date-picker>
                                </el-form-item>
                                <el-form-item label="结束时间" prop="end_time">
                                    <el-date-picker type="datetime" v-model="exam.end_time" placeholder="请选择考试结束时间"></el-date-picker>
                                </el-form-item>
                                <el-form-item label="是否上锁" prop="permission">
                                    <el-radio-group v-model="exam.permission">
                                        <el-radio label="0">否</el-radio>
                                        <el-radio label="1">是</el-radio>
                                    </el-radio-group>
                                </el-form-item>
                                <el-form-item v-if="exam.permission==1" label="设置密码" prop="password">
                                    <el-input type="password" v-model="exam.password" placeholder="请输入考试密码"></el-input>
                                </el-form-item>
                                <el-form-item>
                                    <el-button type="primary" @click="goToStep2()">提交</el-button>
                                    <el-button @click="resetForm()">重置</el-button>
                                </el-form-item> 
                            </el-form>
                        </div>
                    </div>
                    <div v-if="step==2">
                        <p>试卷信息</p>
                        <div class="content2">
                            <el-form style="text-align: center;" :inline="true" ref="form" :model="exam" label-position="left" label-width="100px">
                                <el-form-item label="单选题数量" prop="singleNum">
                                    <el-input-number v-model="exam.singleNum" :min="0" :max="100"></el-input-number>
                                </el-form-item>
                                <el-form-item style="margin-left: 30px;" label="单选题分值" prop="singleScore">
                                    <el-input-number v-model="exam.singleScore" :min="1" :max="1000"></el-input-number>
                                </el-form-item>
                                <el-form-item label="多选题数量" prop="multipleNum">
                                    <el-input-number v-model="exam.multipleNum" :min="0" :max="100"></el-input-number>
                                </el-form-item>
                                <el-form-item style="margin-left: 30px;" label="多选题分值" prop="singleScore">
                                    <el-input-number v-model="exam.multipleScore" :min="1" :max="1000"></el-input-number>
                                </el-form-item>
                                <el-form-item label="判断题数量" prop="multipleScore">
                                    <el-input-number v-model="exam.tfNum" :min="0" :max="100"></el-input-number>
                                </el-form-item>
                                <el-form-item style="margin-left: 30px;" label="判断题分值" prop="singleScore">
                                    <el-input-number v-model="exam.tfScore" :min="1" :max="1000"></el-input-number>
                                </el-form-item>
                                <el-form-item  label="当前总分：">
                                    <span>{{ exam.singleNum*exam.singleScore+exam.multipleNum*exam.multipleScore+exam.tfNum*exam.tfScore }}</span>
                                </el-form-item>
                            </el-form>
                        </div>
                    </div>
                </div>
            </el-tab-pane>
            <el-tab-pane name="second">
                <span slot="label"><i class="el-icon-question"></i> 随机组卷</span>
            </el-tab-pane>
            <el-tab-pane name="third">
                <span slot="label"><i class="el-icon-s-opportunity"></i> 智能组卷</span>
            </el-tab-pane>
        </el-tabs>
    </div>    
</template>

<script>
import { getDateDiff } from '@/utils/time';
export default {
    data() {
        return {
            activeName: 'first',
            exam: {
                source: '',
                description: '',
                totalScore: '',
                totalTime: '',
                start_time: '',
                end_time: '',
                permission: '0',
                singleNum: 0,
                singleScore: 1,
                multipleNum: 0,
                multipleScore: 1,
                tfNum: 0,
                tfScore: 1
            },
            active: 1,
            step: 2,
        }
    }, 
    methods: {
        resetForm() {
            this.exam.source = ''
            this.exam.description = ''
            this.exam.totalScore = ''
            this.exam.totalTime = ''
            this.exam.start_time = ''
            this.exam.end_time = ''
            this.exam.permission = ''
        },
        goToStep2() {
            this.exam.start_time = this.exam.start_time.format('yyyy-MM-dd hh:mm:ss')
            this.exam.end_time = this.exam.end_time.format('yyyy-MM-dd hh:mm:ss')
            if (getDateDiff(this.exam.start_time, this.exam.end_time, "minute") != this.totalTime) {
                this.$message({type: 'error', message: '考试时长存在问题!'})
                this.exam.start_time = ''
                this.exam.end_time = ''
                return;
            }
            this.step = 2
        }
    }
}
</script>

<style scoped>

.content1 {
    width: 30%;
    margin: 0 auto;
}
.content2 {
    width: 50%;
    margin: 0 auto;
}
.box {
    padding: 10px 40px;
    background-color: #E7F6F6;
}   
.box p {
    font-size: 25px;
    font-weight: 500;
    text-align: center;
}
.add {
    margin: 0 40px;
}
</style>