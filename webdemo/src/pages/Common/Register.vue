<template>
  <div>
    <Header></Header>
  <div id="login">
    <el-row class="main-container">
      <el-col :lg="10" :xs="16" :md="10" :span="10">
        <div class="bottom">
          <div class="left"></div>
          <div class="container">
            <p class="title">用户注册</p>
            <el-form  label-width="80px">
              <i class="el-icon-phone"></i>
                <el-input @click.native="click1()" style="width:60%;" v-model="phone" placeholder="请输入手机号"></el-input>
                <p class="tick" v-if="Ok1&&!f1">{{tick1}}</p>  
              <i class="el-icon-user"></i>
                <el-input @click.native="click2()" style="width:60%;" v-model="username" placeholder="请输入用户名"></el-input>
                <p class="tick" v-if="Ok2&&!f2">{{tick2}}</p>  
              <i class="el-icon-lock"></i>
                <el-input @click.native="click3()" style="width:60%;" v-model="password" placeholder="请输入密码" type='password'></el-input>
                <p class="tick" v-if="Ok3&&!f3">{{tick3}}</p>  
              <i class="el-icon-lock"></i>
                <el-input @click.native="click4()" style="width:60%;" v-model="password1" placeholder="请再次输入密码" type='password'></el-input>
                <p class="tick" v-if="Ok4&&!f4">{{tick4}}</p> 
              <div class="submit">
                <el-button style="width:30%; height: 10%;" type="primary" class="row-login" @click="register()">注册</el-button>
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
            phone:'',
            username:'',
            password:'',
            password1:'',
            Ok1:false,
            Ok2:false,
            Ok3:false,
            Ok4:false,
            tick1:'',
            tick2:'',
            tick3:'',
            tick4:'',
            f1:false,
            f2:false,
            f3:false,
            f4:false
        }
    },
    methods: {
        click1() {
          this.Ok1 = true;
        },
        click2() {
          this.Ok2 = true;
        },
        click3() {
          this.Ok3 = true;
        },
        click4() {
          this.Ok4 = true;
          // console.log(this.Ok4&&!this.f4)
        },
        register() {
          const {f1,f2,f3,f4,phone,username,password} = this;
          (f1&&f2&&f3&&f4)&&this.$store.dispatch('sendCode',{phone,username,password}).then(res=>{
            console.log(res);
            if (res != 'ok') {
              this.$message.error(res);
            } else {
              this.$router.push('/phonecheck');
            }
          })
        }
    },
    watch: {
      'phone': {
        immediate: true,
        handler() {
          var r = /^\+?[1-9][0-9]*$/;
          if (!r.test(this.phone) || this.phone.length != 11) this.tick1 = '手机号应为11位数字', this.f1 = false;
          else {
            this.f1 = true;
          }
        }
      },
      'username': {
        immediate: true,
        handler() {
          if (this.username == '') this.tick2 = '请填写用户名', this.f2 = false;
          else if (this.username.length > 30) this.tick2 = '用户名不能超过30个字符', this.f2 = false;
          else {
            this.f2 = true;
          }
        }
      },
      'password': {
        immediate: true,
        handler() {
          if (this.password == '') {
            this.tick3 = '请填写密码'
            this.f3 = false;
          }
          else if (this.password.length < 8) {
            this.tick3 = '长度至少为8'
            this.f3 = false;
          }
          else {
            this.f3 = true;
          }
        }
      },
      'password1': {
        immediate: true,
        handler() {
          if (this.password != this.password1) {
            this.tick4 = '两次密码不一致'
            this.f4 = false;
          } else {
            this.f4 = true;
          }
        }
      }
    }
}
</script>
<style lang="less" scoped>
.bg {
  opacity: 0%;
}
/deep/.el-input__inner {
  // 设置光标颜色
  caret-color: black;
  margin-top: 6%;
}
/deep/.el-input__inner:focus {
  // el-input输入时设置边框颜色
  border: dodgerblue 2px solid;
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
}
.tick {
  margin-top:0;
  margin-left: 23%;
  font-size: 10px;
  color: red;
  margin-bottom: 0;
}
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
  background-color: #b6bccdd1 !important;
}
#login .main-container {
  display: flex;
  justify-content: center;
  align-items: center;
  margin-bottom: 4.3%;
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