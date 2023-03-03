<!-- 用户登录界面 -->
<template>
  <div>
  <Header></Header>
  <div id="login">
    <div class="bg"></div>
    <el-row class="main-container">
      <el-col :lg="8" :xs="16" :md="10" :span="10">
        <div class="bottom">
          <div class="container">
            <p class="title">账号登录</p>
            <el-form  label-width="80px">
              <el-form-item label="用户名">
                <el-input v-model="username" placeholder="请输入用户名"></el-input>
              </el-form-item>
              <el-form-item label="密码">
                <el-input v-model="password" placeholder="请输入密码" type='password'></el-input>
              </el-form-item>
              <div class="submit">
                <el-button type="primary" class="row-login" @click="login()">登录</el-button>
              </div>
              <div class="options">
                <p class="find"><a href="javascript:;">找回密码</a></p>
                <div class="register">
                  <span>没有账号?</span>
                  <span><router-link to="/register">去注册</router-link></span>
                </div>
              </div>
            </el-form>
          </div>
        </div>
      </el-col>
    </el-row>
    <section class="remind">
      <span>管理员账号：9527</span>
      <span>教师账号：20081001</span>
      <span>密码都是：123456</span>
    </section>
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
            password:''
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
.remind {
  border-radius: 4px;
  padding: 10px 20px;
  display: flex;
  position: fixed;
  right: 20px;
  bottom: 50%;
  flex-direction: column;
  color: #606266;
  background-color: #fff;
  border-left: 4px solid #409eff;
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19)
}
.container {
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
  padding: 5% 5%;
}
.bottom .submit {
  display: flex;
  justify-content: center;
}

.bottom .options {
  margin-bottom: 10%;
  color: #ff962a;
  display: flex;
  justify-content: space-between;
}
.bottom .options > a {
  color: #ff962a;
}
.bottom .options .register span:nth-child(1) {
  color: #8C8C8C;
}
</style>