<template>
  <div>
  <Header></Header>
  <div id="login">
    <div class="bg"></div>
    <el-row class="main-container">
      <el-col :lg="10" :xs="16" :md="10" :span="10">
        <div class="bottom">
          <div class="left"></div>
          <div class="container">
            <p class="title">用户注册</p>
            <el-form  label-width="80px">
              <i class="el-icon-phone"></i>
                <el-input style="width:60%;" v-model="username" placeholder="请输入手机号"></el-input>
                <br></br>
              <i class="el-icon-user"></i>
                <el-input style="width:60%;" v-model="username" placeholder="请输入用户名"></el-input>
                <br></br>
              <i class="el-icon-lock"></i>
                <el-input style="width:60%;" v-model="password1" placeholder="请输入密码" type='password'></el-input>
                <br></br>
              <i class="el-icon-lock"></i>
              <el-input style="width:60%;" v-model="password2" placeholder="请再次输入密码" type='password'></el-input>
              <div class="submit">
                <el-button style="width:70%; height: 10%;" type="primary" class="row-login" @click="register()">注册</el-button>
              </div>
              <div class="options">
                <div class="register" style="width: 50%; left:75%; top:100%; position:absolute;">
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
export default {
    components: {
      Header
    },
    data() {
        return {
            username:'',
            password1:'',
            password2:'',
        }
    },
    methods: {
        login() {
          const {username, password} = this;
          (username&&password)&&this.$store.dispatch('userLogin',{username,password}).then(res=>{
            // console.log(this.$store.state.user.token);
            this.$router.push("/myExam");
          });
        }
    },
}
</script>
<style lang="less" scoped>
.el-icon-phone {
  font-size:150%; 
  color: blue; 
  margin-left:13%;
  margin-right:3%
}
.el-icon-user {
  font-size:150%; 
  color: blue; 
  margin-left:13%;
  margin-right:3%
}
.el-icon-lock {
  font-size:150%; 
  color: blue; 
  margin-left:13%;
  margin-right: 3%;
}
.bottom .left {
  background-color: dodgerblue;
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
//   background: url('../../assets/img/loginbg.png')center top / cover no-repeat;
  background-color: #b6bccdd1 !important;
}
#login .main-container {
  display: flex;
  justify-content: center;
  align-items: center;
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