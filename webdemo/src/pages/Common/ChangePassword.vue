<template>
    <div>
        <Header></Header>
        <div class="container">
            <Nav></Nav>
            <div class="container-right">
                <div class="user-account-content">
                    <div class="user-center-bread">
                        <span @click="goToAccount()">账号设置<em>/</em></span>
                        <span>修改密码</span>
                    </div>
                    <div style="padding: 28px 0 30px;">
                        <ul class="acc_pass_list">
                            <li>
                                <label>输入新密码：</label>
                                <input @click="write()" v-model="password1" type="password" placeholder="长度至少为8位" class="input">
                            </li>
                            <li>
                                <label>确认新密码：</label>
                                <input @click="write()" v-model="password2" type="password" placeholder="请再次输入密码" class="input">
                            </li>
                            <li v-if="show" class="error_tips">
                                {{message}}
                            </li>
                            <li style="padding: 20px 0 0 108px;">
                                <button v-if="!(password1!=''&&password2!='')" class="confirm_btn2" disabled="disabled">确认</button>
                                <button v-if="password1!=''&&password2!=''" class="confirm_btn1" @click="changePassword()">确认</button>
                            </li>
                        </ul>
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
            show: false,
            message: '',
            password1: '',
            password2: ''
        }
    },
    mounted() {
        
    },
    methods: {
        goToAccount() {
            this.$router.push('/accountInfo')
        },
        write() {
            this.show = false
        },
        changePassword() {
            if (this.password1 != this.password2) {
                this.message = '两次密码不一致!'
                this.show = true
            } else {
                if (this.password1.length < 8 || this.password2.length < 8) {
                    this.message = '密码长度小于8'
                    this.show = true
                } else {
                    this.$store.dispatch('getUserInfo').then(res=>{
                        let id = this.$store.state.user.userinfo.id
                        let password = this.password1
                        this.$store.dispatch('updatePassword', {id,password}).then(res=>{
                            if (res == 'ok') {
                                this.$message({type:'success',message:'修改成功!'})
                                this.$store.dispatch('userLogout');
                                this.$router.push('/login')
                            }
                        })
                    })
                    
                }
            }
        }
    }
}
</script>
<style scoped>
.confirm_btn1 {
    color: #fff;
    background: #e33e33;
    border: 1px solid #e33e33;
    cursor: pointer;
    width: 80px;
    height: 36px;
    line-height: 34px;
    border-radius: 4px;
    font-size: 14px;
    text-align: center;
}
.confirm_btn2 {
    color: #c2c2c2;
    background: #f5f5f5;
    border: 1px solid #f5f5f5;
    cursor: pointer;
    width: 80px;
    height: 36px;
    line-height: 34px;
    border-radius: 4px;
    font-size: 14px;
    text-align: center;
    pointer-events:none;
}
.acc_pass_list .error_tips {
    font-size: 14px;
    color: #d0021b;
    padding-left: 108px;
}
.acc_pass_list li label {
    width: 100px;
    text-align: right;
    font-size: 14px;
    color: #4d4d4d;
}
.input {
    width: 252px;
    height: 32px;
    line-height: 30px;
    margin-left: 8px;
    border: 1px solid #ccc;
    border-radius: 4px;
    padding: 0 8px;
    font-size: 14px;
    color: #4d4d4d;
    background-color:transparent;
    opacity: 0.7;
}

.acc_pass_list li{
    position: relative;
    margin-bottom: 8px;
    font-size: 0;
}
.acc_pass_list {
    width: 400px;
    margin: 0 auto;
    padding: 0
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