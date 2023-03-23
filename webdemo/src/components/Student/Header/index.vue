<!--学生考试首页-->
<template>
  <div id="student">
    <el-row class="padding-50">
      <el-col :span="24">
        <ul class="list">
          <li class="logo"><i class="el-icon-s-opportunity"></i><span>Exam-Online</span></li>
          <li><router-link to="/problemSet">题库</router-link></li>
          <li><router-link to="/myexam">我的考试</router-link></li>
          <li><router-link to="/practice">我的练习</router-link></li>
          <li><router-link to="/wrongbook">我的错题本</router-link></li>
          <li><router-link to="/message">给我留言</router-link></li>
          <li><a href="javascript:;">搜索试卷</a></li>
          <li class="right" @mouseenter="flag = !flag" @mouseleave="flag = !flag">
            <router-link v-if="!isLogin" to="/login">&nbsp;&nbsp;&nbsp;登录/注册</router-link>
            <a v-if="isLogin" href="javascript:;"><i style="font-size: 150%;" class="el-icon-user-solid"></i>&nbsp;&nbsp;&nbsp;{{user.username}}</a>
            <div class="msg" v-if="flag && isLogin">
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
export default {
    data() {
        return {
            user: {},
            flag: false,
            isLogin: false
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
      }
    }
}
</script>

<style scoped>
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
  background-color: white;
  position: fixed;
  box-shadow: 3px 3px 10px 4px rgba(1,149,255,0.2);
  /* background-color: yellow; */
}
.list {
  margin-bottom: 0px;
  margin-top: 0px;
}
.list a {
  text-decoration: none;
  color: #334046;
}
li {
  list-style: none;
  height: 400%;
  line-height: 400%;
  /* background-color: black; */
}
#student .list{
  display: flex;
}
#student .list li {
  padding: 0 1%;
  cursor: pointer;
}
#student .list li:hover {
  background-color: #0195ff;
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
  font-weight: bold;
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