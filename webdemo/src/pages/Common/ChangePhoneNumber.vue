<template>
    <div>
        <Header></Header>
        <div class="container"> 
            <Nav></Nav>
            <div class="container-right">
                <div class="user-account-content">
                    <div class="user-center-bread">
                        <span @click="goToAccount()">账号设置<em>/</em></span>
                        <span>手机设置</span>
                    </div>
                    <div v-if="step==1" class="form-custom">
                        <div class="form-info">
                            <span>验证码将发送到手机{{ baseInfo.phone.substr(0,3)+"****"+baseInfo.phone.substr(7,4) }}</span>
                            <p>如果长时间未收到验证码，请检查是否将运营商拉黑</p>
                        </div>
                        <div class="form-group">
                            <label class="form-label">请填写验证码：</label>
                            <el-input v-model="code" style="margin-left: 10px; width:40%;">
                                <el-button class="btn" slot="append" icon="el-icon-s-promotion"></el-button>
                            </el-input>
                        </div>
                        <div class="form-group-error">
                            <div v-if="show" class="text-error">验证码不正确!</div>
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
                            <label class="form-label">输入新手机：</label>
                            <el-input v-model="new_phone" style="margin-left:20px;width:40%;"></el-input>
                        </div>
                        <div class="form-group">
                            <label class="form-label">填写验证码：</label>
                            <el-input v-model="new_code" style="margin-left:20px;width:40%;">
                                <el-button class="btn" slot="append" icon="el-icon-s-promotion"></el-button>
                            </el-input>
                        </div>
                        <div class="form-group-error">
                            <div v-if="show" class="text-error">{{ error_message }}</div>
                        </div>
                        <div class="form-group">
                            <div class="form-submit">
                                <button v-if="new_code != ''" class="btn1">下一步</button>
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
            baseInfo: {phone: '15757562010'},
            show: false,
            code: '',
            step: 2,
            new_phone: '',
            error_message: '',
            new_code: ''
        }
    },
    methods: {
        goToAccount() {
            this.$router.push('/accountInfo');
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