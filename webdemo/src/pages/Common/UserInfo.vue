<template>
    <div>
        <Header></Header>
        <div class="container"> 
            <Nav></Nav>
            <div class="container-right">
                <div class="base-info">
                    <div class="base-info-head">基本信息</div>
                    <div class="base-info-content">
                        <div class="base-info-content-show">
                            <el-form :label-position="labelPosition" label-width="80px">
                                <ul>
                                    <li>
                                        <div class="content-show-l">{{baseInfo[0].label}}</div>
                                        <div v-if="!baseInfo[0].selected" lass="content-show-r">{{baseInfo[0].value}}</div>
                                        <div v-if="!baseInfo[0].selected" class="edit-icon" @click="update(0)"></div>
                                    </li>
                                    <li>
                                        <div class="content-show-l">{{baseInfo[1].label}}</div>
                                        <div v-if="!baseInfo[1].selected" lass="content-show-r">{{baseInfo[1].value}}</div>
                                        <div v-if="!baseInfo[1].selected" class="edit-icon" @click="update(1)">
                                            <i></i>编辑
                                        </div>
                                        <div v-if="baseInfo[1].selected" style="width: 150px;">
                                            <el-radio v-model="baseInfo[1].value" label="男">男</el-radio>
                                            <el-radio v-model="baseInfo[1].value" label="女">女</el-radio>
                                        </div>
                                        <div style="margin-left: 15px;" v-if="baseInfo[1].selected">
                                            <el-button size="small" type="primary" @click="change(1)" round>确认</el-button>
                                            <el-button size="small" @click="cancel(1)" round>取消</el-button>
                                        </div>
                                    </li>
                                    <li>
                                        <div class="content-show-l">{{baseInfo[2].label}}</div>
                                        <div v-if="!baseInfo[2].selected" lass="content-show-r">{{baseInfo[2].value}}</div>
                                        <div v-if="!baseInfo[2].selected" class="edit-icon" @click="update(2)">
                                            <i></i>编辑
                                        </div>
                                        <div v-if="baseInfo[2].selected" style="width: 360px;">
                                            <el-select v-model="baseInfo[2].value" placeholder="请选择">
                                                <el-option :value="pick1"></el-option>
                                                <el-option :value="pick2"></el-option>
                                                <el-option :value="pick3"></el-option>
                                                <el-option :value="pick4"></el-option>
                                                <el-option :value="pick5"></el-option>
                                                <el-option :value="pick6"></el-option>
                                            </el-select>
                                        </div>
                                        <div style="margin-left: 15px;" v-if="baseInfo[2].selected">
                                            <el-button size="small" type="primary" @click="change(2)" round>确认</el-button>
                                            <el-button size="small" @click="cancel(2)" round>取消</el-button>
                                        </div>
                                    </li>
                                    <li>
                                        <div class="content-show-l">{{baseInfo[3].label}}</div>
                                        <div v-if="!baseInfo[3].selected" lass="content-show-r">{{baseInfo[3].value}}</div>
                                        <div v-if="!baseInfo[3].selected" class="edit-icon" @click="update(3)">
                                            <i></i>编辑
                                        </div>
                                        <div v-if="baseInfo[3].selected" style="width: 360px;">
                                            <el-input v-model="baseInfo[3].value"></el-input>
                                        </div>
                                        <div style="margin-left: 15px;" v-if="baseInfo[3].selected">
                                            <el-button size="small" type="primary" @click="change(3)" round>确认</el-button>
                                            <el-button size="small" @click="cancel(3)" round>取消</el-button>
                                        </div>
                                    </li>
                                    <li>
                                        <div class="content-show-l">{{baseInfo[4].label}}</div>
                                        <div v-if="!baseInfo[4].selected" lass="content-show-r">{{baseInfo[4].value}}</div>
                                        <div v-if="!baseInfo[4].selected" class="edit-icon" @click="update(4)">
                                            <i></i>编辑
                                        </div>
                                        <div v-if="baseInfo[4].selected" style="width: 360px;">
                                            <el-input v-model="baseInfo[4].value"></el-input>
                                        </div>
                                        <div style="margin-left: 15px;" v-if="baseInfo[4].selected">
                                            <el-button size="small" type="primary" @click="change(4)" round>确认</el-button>
                                            <el-button size="small" @click="cancel(4)" round>取消</el-button>
                                        </div>
                                    </li>
                                    <li>
                                        <div class="content-show-l">{{baseInfo[5].label}}</div>
                                        <div v-if="!baseInfo[5].selected" lass="content-show-r">{{baseInfo[5].value}}</div>
                                        <div v-if="!baseInfo[5].selected" class="edit-icon" @click="update(5)">
                                            <i></i>编辑
                                        </div>
                                        <div v-if="baseInfo[5].selected" style="width: 250px;">
                                            <el-date-picker
                                                v-model="baseInfo[5].value"
                                                type="date"
                                                placeholder="选择日期">
                                            </el-date-picker>
                                        </div>
                                        <div style="margin-left: 15px;" v-if="baseInfo[5].selected">
                                            <el-button size="small" type="primary" @click="change(5)" round>确认</el-button>
                                            <el-button size="small" @click="cancel(5)" round>取消</el-button>
                                        </div>
                                    </li>
                                </ul>
                            </el-form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import Header from '@/components/Student/Header'
import Nav from '@/components/Student/Nav'
export default {
    components: {
        Header,
        Nav
    },
    data() {
        return {
            pick1: "小学",
            pick2: "初中",
            pick3: "高中",
            pick4: "大学",
            pick5: "研究生",
            pick6: "博士及以上",
            labelPosition: 'right',
            baseInfo: [
                {label: '用户名', value: '', selected: false},
                {label: '性别', value: '', selected: false},
                {label: '学历', value: '', selected: false},
                {label: '专业', value: '',selected: false},
                {label: '地区', value: '', selected: false},
                {label: '生日', value: '', selected: false}
            ],
            userInfo: null,
        }
    },
    mounted() {
        this.init()
    },
    methods: {
        init() {
            this.$store.dispatch('getUserInfo').then(res=>{
                let userInfo = this.$store.state.user.userinfo
                this.$store.dispatch('getUserInfoById', userInfo.id).then(res=>{
                    let user = this.$store.state.user.user
                    this.baseInfo[0].value = user.username;
                    this.baseInfo[1].value = user.sex;
                    this.baseInfo[2].value = user.grade;
                    this.baseInfo[3].value = user.major;
                    this.baseInfo[4].value = user.region;
                    this.baseInfo[5].value = user.birthday;
                    this.userInfo = user
                    
                })
            })
        },
        cancel(index) {
            this.baseInfo[index].selected = false
        },
        update(index) {
            for (let i = 0; i < this.baseInfo.length; i++) this.baseInfo[i].selected = false
            this.baseInfo[index].selected = true
        },
        change(index) {
            this.cancel(index)
            if (index == 1) {
                this.userInfo.sex = this.baseInfo[1].value;
                this.$store.dispatch('updateUserInfo', this.userInfo).then(res=>{
                    if (res == 'ok') {
                        this.$message({type:'success', message: '修改成功!'})
                    }
                })
            } else if (index == 2) {
                this.userInfo.grade = this.baseInfo[2].value;
                this.$store.dispatch('updateUserInfo', this.userInfo).then(res=>{
                    if (res == 'ok') {
                        this.$message({type:'success', message: '修改成功!'})
                    }
                })
            } else if (index == 3) {
                this.userInfo.major = this.baseInfo[3].value;
                this.$store.dispatch('updateUserInfo', this.userInfo).then(res=>{
                    if (res == 'ok') {
                        this.$message({type:'success', message: '修改成功!'})
                    }
                })
            } else if (index == 4) {
                this.userInfo.region = this.baseInfo[4].value;
                this.$store.dispatch('updateUserInfo', this.userInfo).then(res=>{
                    if (res == 'ok') {
                        this.$message({type:'success', message: '修改成功!'})
                    }
                })
            } else if (index == 5) {
                this.baseInfo[5].value = this.baseInfo[5].value.format('yyyy-MM-dd')
                this.userInfo.birthday = this.baseInfo[5].value
                this.$store.dispatch('updateUserInfo', this.userInfo).then(res=>{
                    if (res == 'ok') {
                        this.$message({type:'success', message: '修改成功!'})
                    }
                })
            }
        }
    }
}
</script>

<style scoped>
ul li:hover .edit-icon {
    display: -webkit-box;
    display: -ms-flexbox;
    display: flex;
}
.base-info .edit-icon i {
    display: inline-block;
    margin-right: 2px;
    width: 16px;
    height: 16px;
    background: url('@/assets/edit.png') no-repeat 50%;
    background-size: 16px 16px;
    position: relative;
    top: 3px;
    left: 0;
}
.edit-icon {
    display: none;
    margin-left: 24px;
    cursor: pointer;
    -webkit-box-align: baseline;
    -ms-flex-align: baseline;
    align-items: baseline;
    color: #1989fa;
    font-size: 14px;
    min-width: 50px;
}
.content-show-r {
    color: #222226;
    word-break: break-all;
}
.content-show-l {
    width: 56px;
    color: #555666;
    margin-right: 40px;
    -ms-flex-negative: 0;
    flex-shrink: 0;
    padding-left: 16px;
    display: -webkit-box;
    display: -ms-flexbox;
    display: flex;
    -webkit-box-sizing: content-box;
    box-sizing: content-box;
    -webkit-box-pack: justify;
    -ms-flex-pack: justify;
    justify-content: space-between;
}
.base-info .base-info-content .base-info-content-show ul li {
    line-height: 24px;
    font-size: 14px;
    margin-bottom: 32px;
    display: -webkit-box;
    display: -ms-flexbox;
    display: flex;
    -webkit-box-align: baseline;
    -ms-flex-align: baseline;
    align-items: baseline;
}
.base-info {
    width: 70%;
    background: #fff;
    margin-bottom: 10px;
}
.base-info-content-show {
    padding: 16px 58px 16px 0;
    position: relative;
}
.base-info-content {
    padding: 16px;
}
.base-info-head {
    height: 48px;
    line-height: 48px;
    font-size: 18px;
    font-weight: 600;
    color: #2e2e2e;
    padding-left: 16px;
    border-bottom: 1px solid #f0f0f2;
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

.container-right {
    min-width: 50%;
    margin-left: 200px;
    min-height: 800px;
}
</style>