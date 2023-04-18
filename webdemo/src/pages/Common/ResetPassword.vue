<template>
    <div>
      <Header></Header>
    <div id="login">
      <el-row class="main-container">
        <el-col :lg="9" :xs="16" :md="10" :span="10">
          <div class="bottom">
            <div class="left"></div>
            <div class="container">
              <p class="title">找回密码</p>
              <div v-if="state==1&&step==1" class="tags"><span class="font1" @click="changeToState1()">手机</span> / <span class="font2" @click="changeToState2()">邮箱</span></div>
              <div v-if="state==2&&step==1" class="tags"><span class="font2" @click="changeToState1()">手机</span> / <span class="font1" @click="changeToState2()">邮箱</span></div>
              <el-form v-if="step==1" label-width="80px">
                <i class="el-icon-position"></i>
                <el-input v-if="state==1" style="width:60%;" v-model="phone" placeholder="请输入绑定的手机号"></el-input>
                <el-input v-else style="width:60%;" v-model="email" placeholder="请输入绑定的邮箱"></el-input>
                <br><br>
                <i class="el-icon-message"></i>
                <el-input v-model="code" placeholder="验证码" style="width: 40%;"></el-input>
                <el-button style="width:20%;" @click="sendMsg()">发送</el-button>
                
                <div class="submit">
                  <el-button style="width:65%; height: 10%;" type="primary" class="row-login" @click="goToStep2()">确认</el-button>
                </div>
                <div class="options">
                  <div class="register" style="text-align: center;">
                    <span><router-link to="/login">返回登录</router-link></span>
                  </div>
                </div>
              </el-form>
              <el-form v-else label-width="80px">
                <i class="el-icon-lock"></i>
                  <el-input style="width:60%;" v-model="password1" placeholder="请输入新密码" type='password'></el-input>
                  <br><br>
                <i class="el-icon-lock"></i>
                  <el-input style="width:60%;" v-model="password2" placeholder="请重复输入新密码" type='password'></el-input>
                <div class="submit">
                  <el-button style="width:65%; height: 10%;" type="primary" class="row-login" @click="goToLogin()">确认</el-button>
                </div>
                <div class="options">
                  <div class="register" style="text-align: center;">
                    <span><router-link to="/login">返回登录</router-link></span>
                  </div>
                </div>
              </el-form>
            </div>
          </div>
        </el-col>
      </el-row>
    </div>
    </div>
  </template>
  
  <script>
  import Header from '@/components/Student/Header'
  import {setRole} from '@/utils/token.js'
  export default {
      components: {
        Header
      },
      data() {
          return {
              username:'',
              phone:'',
              email:'',
              state: 1,
              step: 1,
              code: '',
              password1: '',
              password2: ''
          }
      },
      methods: {
        sendMsg() {
            if (this.phone == '' && this.email == '') {
                this.$message({type:'error',message:'请输入手机号或邮箱'})
                return
            }
            if (this.state == 1) {
                this.$message.error('短信余额已经用尽!')
            } else {
                this.$store.dispatch('sendEmail',{email:this.email}).then(res=>{
                    if (res == 'ok') {
                        this.$message({type:'success', message:'验证码已发送，十分钟有效'})
                    } else {
                        this.$message({type:'error', message: '不存在该绑定邮箱'})
                    }
                })
            }
        },
        changeToState1() {
            this.state = 1
        },
        changeToState2() {
            this.state = 2
        },
        goToLogin() {
            this.email = "wmy0976543244@163.com"
            if (this.password1 == this.password2) {
                this.$store.dispatch('updatePasswordByEmail', {password:this.password1, email: this.email}).then(res=>{
                    if (res == 'ok') {
                        this.$message.success('修改成功!')
                        this.$router.push('/login')
                    }
                })
            } else if (this.password1.length<8){
                this.$message({type:'error', message: '密码长度小于8!'})
            } else {
                this.$message({type:'error', message: '两次密码不一致!'})
            }
        },
        goToStep2() {   
            if (this.code == '') {
                this.$message({type:'error', message:"请填写验证码!"})
                return
            }
            this.$store.dispatch('verifyEmailByCode', {email:this.email, code: this.code}).then(res=>{
                console.log(res)
                if (res == 'ok') {
                    this.step = 2
                } else {
                    this.$message({type:'error', message:'验证码错误!'})
                }
            })
        },
      },
  }
  </script>
  <style lang="less" scoped>
    .tags {
        right: 10px;
        top: 10px;
        position: absolute;
        font-size: 16px;
    }
    .font1 {
        font-weight: 700;
        cursor: pointer;
    }
    .font2 {
        font-weight: 100;
        cursor: pointer;
    }
  .bg {
    opacity: 0%;
  }
  /deep/.el-input__inner {
    // 设置光标颜色
    caret-color: black;
  }
  /deep/.el-input__inner:focus {
    // el-input输入时设置边框颜色
    border: dodgerblue 2px solid;
    box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
  }

  i {
    font-size:150%; 
    color: blue; 
    margin-left:13%;
    margin-right: 3%;
  }
  .bottom .left {
    background-image: url('@/assets/testlogo1.jpg');
    -moz-background-size:100% 100%; background-size:100% 100%;
    height: 100%;
    width: 50%;
    top: 0;
    left: 0;
    margin: 0;
    position: absolute;
  }
  
  .container {
    height: 100%;
    width: 50%;
    position: relative;
    top: 0;
    left: 25%;
    margin-bottom: 5%;
  }
  .container .el-radio-group {
    margin: 10% 0;
  }
  a:link {
    color:#ff962a;
    text-decoration:none;
  }
  #login {
    font-size: 15px;
    color: #000;
    background-color: #fff;
  }
  #login .bg {
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    overflow-y: auto;
    height: 100%;
  }
  #login .main-container {
    display: flex;
    justify-content: center;
    align-items: center;
    margin-bottom: 10%;
    // background-image: url('@/assets/background.png');
    background-color: #eff3f5!important;
  }
  #login .main-container .top {
    margin-top: 10%;
    margin-bottom: 5%;
    font-size: 30px;
    color: #ff962a;
    display: flex;
    justify-content: center;
  }
  
  #login .bottom {
    position: relative;
    margin-top: 30%;
    display:flex;
    justify-content: center;
    background-color:#fff;
    border-radius: 5px;
    box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
  }
  #login .bottom .title {
    text-align: center;
    font-size: 30px;
  }
  .bottom .container .title {
    margin: 10% 0;
  }
  .bottom .submit .row-login {
    width: 100%;
    background-color: #04468b;
    border-color: #04468b;
    margin: 5% 0 5% 0;
    padding: 3% 10%;
  }
  .bottom .submit {
    display: flex;
    justify-content: center;
  }
  
  .bottom .options {
    // margin-bottom: 10%;
    color: #ff962a;
    
    // justify-content: space-between;
  }
  .bottom .options > a {
    color: #ff962a;
  }
  
  .bottom .options .register span:nth-child(1) {
    color: #8C8C8C;
  }
  </style>