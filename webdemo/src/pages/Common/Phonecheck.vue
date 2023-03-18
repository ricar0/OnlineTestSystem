<template>
  <div>
    <Header></Header>
  <div id="login">
    <el-row class="main-container">
      <el-col :lg="9" :xs="16" :md="10" :span="10">
        <div class="bottom">
          <div class="left"></div>
          <div class="container">
            <p class="title" style="margin-bottom:2%;">激活</p>
            <el-form label-width="80px">
              <div v-if="!isActivated">
                <p style="color: gray; margin-left:15%;">短信已发送至:{{phone}}</p>
                <i class="el-icon-message"></i>
                    <el-input style="width:60%;" v-model="code" placeholder="请输入验证码"></el-input>
                    <br></br>
                <div class="submit">
                    <el-button style="width:30%; height: 10%;" type="primary" class="row-login" @click="check1()">确认</el-button>
                </div>
                <div class="options">
                    <div class="register" style="width: 50%; left:75%; top:100%; position:absolute;">
                    <span><router-link to="/login">返回登录</router-link></span>
                    </div>
                </div>
              </div>  
              <div v-else>
                <el-result style="padding: 0;" icon="success" title="账户激活成功">
                    <template slot="extra">
                    <el-button type="primary" size="medium" style="background-color:#04468b;" @click="check2()">确认</el-button>
                    </template>
                </el-result>
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
            phone: this.$store.state.user.phone.substr(0,3)+'****'+this.$store.state.user.phone.substr(7),
            isActivated: false,
            code:'',
        }
    },
    methods: {
        check1() {
          if (this.code == '') {
            this.$message.error('验证码不能为空！');
          } else {
            let phone = this.$store.state.user.phone;
            let code = this.code;
            this.$store.dispatch('getCode',{code,phone}).then(res=>{
                console.log(res);
                if (res != 'ok') {
                    this.$message.error('验证码不正确!');
                } else {
                    this.isActivated = true;
                }
            })
          }
        },
        check2() {
            this.$router.push('/login');
        }
    },
}
</script>
<style lang="less" scoped>

/deep/.el-input__inner {
// 设置光标颜色
caret-color: black;
}
/deep/.el-input__inner:focus {
// el-input输入时设置边框颜色
border: dodgerblue 2px solid;
box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
}
.el-icon-message {
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
  // background-color: #b6bccdd1 !important;
}
#login .main-container {
  display: flex;
  justify-content: center;
  align-items: center;
  margin-bottom: 13.95%;
  background-image: url('@/assets/background.png');
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