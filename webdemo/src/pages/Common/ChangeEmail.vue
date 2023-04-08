<template>
    <div>
        <Header></Header>
        <div class="container"> 
            <Nav></Nav>
            <div class="container-right">
                <div class="user-account-content">
                    <div class="user-center-bread">
                        <span @click="goToAccount()">账号设置<em>/</em></span>
                        <span>邮箱设置</span>
                    </div>
                    <div v-if="step==1" class="form-custom">
                        <div class="form-info">
                            <span>验证码将发送到邮箱{{ baseInfo.email.substr(0,2)+"***@"+baseInfo.email.split('@')[1]}}</span>
                            <p>如果长时间未收到验证码，请检查是否将运营商拉黑</p>
                        </div>
                        <div class="form-group">
                            <label class="form-label">请填写验证码：</label>
                            <el-input @click="write()" v-model="code" style="margin-left: 10px; width:40%;">
                                <el-button class="btn" slot="append" icon="el-icon-s-promotion"></el-button>
                            </el-input>
                        </div>
                        <div class="form-group-error">
                            <div v-if="show" class="text-error">验证码不正确！</div>
                        </div>
                        <div class="form-group">
                            <div class="form-submit">
                                <button v-if="code != ''" class="btn1">下一步</button>
                                <button v-if="code == ''" class="btn2" disabled="disabled">下一步</button>
                            </div>
                        </div>
                    </div>
                    <div v-if="step==2" class="form-custom">
                        <div class="form-group">
                            <label class="form-label">输入新邮箱：</label>
                            <el-input v-model="new_email" style="margin-left:20px;width:40%;"></el-input>
                        </div>
                        <div class="form-group">
                            <label class="form-label">填写验证码：</label>
                            <el-input @click="write()" v-model="new_code" style="margin-left:20px;width:40%;">
                                <el-button @click="sendEmail()" class="btn" slot="append" icon="el-icon-s-promotion"></el-button>
                            </el-input>
                        </div>
                        <div class="form-group-error">
                            <div v-if="show" class="text-error">验证码不正确！</div>
                        </div>
                        <div class="form-group">
                            <div class="form-submit">
                                <button v-if="new_code != ''" @click="verifyEmail()" class="btn1">下一步</button>
                                <button v-if="new_code == ''" class="btn2" disabled="disabled">下一步</button>
                            </div>
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
            baseInfo: {},
            show: false,
            code: '',
            step: 1,
            new_email: '',
            new_code: '',
            error_message: ''
        }
    },
    mounted() {
        this.step = localStorage.getItem('index')
        this.$store.dispatch('getUserInfo').then(res=>{
            let id = this.$store.state.user.userinfo.id
            this.$store.dispatch('getUserInfoById',id).then(res=>{
                this.baseInfo = this.$store.state.user.user
            })
        })
    },
    methods: {
        write() {
            this.show = false
        },
        goToAccount() {
            this.$router.push('/accountInfo');
        },
        sendEmail() {
            let id = this.baseInfo.id
            let email = this.new_email
            this.$store.dispatch('sendEmail', {id,email});
        },
        verifyEmail() {
            let id = this.baseInfo.id;
            let email = this.new_email
            let code = this.new_code
            this.$store.dispatch('verifyEmail', {id,email,code}).then(res=>{
                if (res == 'ok') {
                    this.$message({type: 'success', message: '修改成功!'});
                    this.$router.push('/accountInfo')
                } else {
                    this.show = true
                }
            })
        }
    }
}
</script>

<style scoped>

.btn1 {
    width: 80px;
    border: none;
    background-color: #e33e33;
    color: #fff;
    height: 36px;
    border-radius: 4px;
    cursor: pointer;
    margin-left: 110px;
}
.btn2 {
    width: 80px;
    border: none;
    background-color: #f5f5f5;
    color: #c2c2c2;
    height: 36px;
    border-radius: 4px;
    pointer-events:none;
    margin-left: 110px;
}
.text-error {
    margin: 8px 0 0;
    color: #f21f2b;
    font-size: 12px;
}
.form-group-error {
    margin: 8px 0;
    font-size: 0;
    height: 25px;
}
.el-input-group__prepend {
    background-color: #fff;
}
/deep/.el-input__inner  {
  height: 30px;
}
.form-label {
    width: 20%;
    font-size: 14px;
    color: #4d4d4d;
    letter-spacing: 0;
    line-height: 22px;
    vertical-align: middle;
}
.form-info p {
    font-size: 14px;
    color: #ccc;
    letter-spacing: 0;
    line-height: 22px;
}
.form-info span{
    display: block;
    margin-bottom: 8px;
    font-size: 14px;
    color: #4d4d4d;
    letter-spacing: 0;
    line-height: 22px;
}
.form-info {
    margin-bottom: 25px;
    margin: 8px 0;
    font-size: 0;
}
.form-custom {
    min-height: 300px;
    width: 50%;
    margin: 0 auto 30px;
}
.form-group {
    margin: 8px 0;
}
.user-center-bread span em {
    display: inline-block;
    margin: 0 4px 0 6px;
    font-style: normal;    
}
.user-center-bread span {
    cursor: pointer;
}
.user-center-bread span:hover {
    color: #1989fa;
}
.user-center-bread {
    color: #999aaa;
    font-size: 14px;
    height: 20px;
    line-height: 20px;
    font-weight: 400;
    margin-bottom: 32px;
}
.container .container-right .user-account-content {
    padding: 16px;
    background: #fff;
    width: 70%;
}
.container .container-right {
    min-width: 50%;
    margin-left: 200px;
    min-height: 800px;
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
</style>