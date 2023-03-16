<!--学生考试首页-->
<template>
  <div id="student">
    <el-row class="padding-50">
      <el-col :span="24">
        <ul style="padding-left:0;" class="list">
          <li style="cursor: pointer;" @click="change()"><i style="font-size: 150%;" class="el-icon-menu"></i></li>
          <li style="padding-right: 0;"><i style="font-size:250%;"class="el-icon-collection"></i></li>
          <li class="logo"><span style="font-size:25px; color: white;">Exam-Online</span></li>
          <li class="right" @mouseenter="flag = !flag" @mouseleave="flag = !flag">
            <router-link v-if="!isLogin" to="/login">&nbsp;&nbsp;&nbsp;登录/注册</router-link>
            <a style="color:white;" v-if="isLogin" href="javascript:;"><i style="font-size: 150%;" class="el-icon-user-solid"></i>&nbsp;&nbsp;&nbsp;{{user.username}}</a>
            <div style="cursor: pointer;" class="msg" v-if="flag && isLogin">
              <p @click="manage()">个人信息</p>
              <p class="exit" @click="logout()">退出登录</p>
            </div>
          </li>
        </ul>
      </el-col>
    </el-row>
    
  </div>
</template>

<script>
import Bus from '@/utils/bus.js'
export default {
    data() {
        return {
            user: {},
            flag: false,
            isLogin: false,
            flag2: false,
        }
    },
    created() {
      this.$store.dispatch('getUserInfo').then(res=>{
        if (this.$store.state.user.token) {
          this.isLogin = true;
          this.user = this.$store.state.user.userinfo;
        }
      })
    },
    methods: {
      logout() {
        this.$store.dispatch('userLogout').then(res=>{
          this.$router.push('/login')
        })
      },
      change() {
        this.flag2 = this.flag2 ^ 1;
        Bus.$emit('flag', this.flag2);
      }
    }
}
</script>

<style scoped>
.msg p {
    background-color:#124280; 
}
li i {
    display: inline-block;
    vertical-align: middle;
    margin: 0 auto;
}
.right .icon {
  margin-right: 100%;
}
#student .padding-50 {
  margin: 0 auto;
  padding: 0 auto;
  top: 0;
  left: 0;
  z-index: 1000;
  width: 100%;
  background-color:#124280; 
  position: fixed;
  box-shadow: 3px 3px 10px 4px rgba(0,0,0,0.3);
  
}
.list {
  margin-bottom: 0px;
  margin-top: 0px;
  /* background-color:#124280;  */
}
.list a {
  text-decoration: none;
  color: #334046;
}
li {
  list-style: none;
  height: 400%;
  line-height: 400%;
  color: white;
  /* background-color: black; */
}

#student .list{
  display: flex;
}
#student .list li {
  padding: 0 1%;
  /* cursor: pointer; */
}
#student .list li:hover {
  /* background-color: #0195ff; */
  transition: all 2s ease;
}
#student .list li:hover a {
  color: #fff;
}
#student .list .right {
  margin-left: auto;
  position: relative;
}
#student .list li.right :hover a {
  color: #000;
}
#student .list .logo {
  display: flex;
  /* font-weight: bold; */
  color: #2f6c9f;
}
#student .list .logo i {
  font-size: 300%;
}
.right .msg {
  text-align: center;
  position: absolute;
  top: 100%;
  left: 0%;
  width: 100%;
  display: flex;
  flex-direction: column;
  border-radius: 2px;
  box-shadow: 3px 3px 10px 4px rgba(1,149,255,0.2);
  /* border-bottom: 3px solid #0195ff; */
  background-color: white;
}
.right .msg p {
  opacity: 70%;
  margin: 0 0px;
  height: 200%;
  line-height: 300%;
  width: 100%;
}
.right .msg p:hover {
  background-color: #0195ff;
}

</style>