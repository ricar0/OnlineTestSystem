<!--学生考试首页-->
<template>
  <div id="student">
    <el-row class="padding-50">
      <el-col :span="24">
        <ul class="list">
          <li class="logo"><i class="el-icon-s-data"></i><span>Exam-Online</span></li>
          <li :class="{active:selected[0]}"><router-link to="/problemSet"><i class="el-icon-s-grid"></i> 题库</router-link></li>
          <li :class="{active:selected[1]}"><router-link to="/myexam"><i class="el-icon-trophy"></i> 考试</router-link></li>
          <li :class="{active:selected[2]}"><router-link to="/practice"><i class="el-icon-s-claim"></i> 练习</router-link></li>
          <li :class="{active:selected[3]}"><router-link to="/wrongbook"><i class="el-icon-collection"></i> 错题本</router-link></li>
          <li class="right" @mouseenter="flag = !flag" @mouseleave="flag = !flag">
            <router-link v-if="!isLogin" to="/login">&nbsp;&nbsp;&nbsp;登录/注册</router-link>
            <a v-if="isLogin" href="javascript:;"><i style="font-size: 150%;" class="el-icon-user-solid"></i>&nbsp;&nbsp;{{user.username}}</a>
            <div class="msg" v-if="flag && isLogin">
              <p @click="userinfo()">个人信息</p>
              <p @click="goToManageMain()" v-if="isTeacher">管理界面</p>
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
            isLogin: false,
            selected: [false,false,false,false],
            isTeacher: false,
        }
    },
    created() {
      if (this.$route.fullPath=="/problemSet") this.selected[0] = true
      else if (this.$route.fullPath=="/myexam") this.selected[1] = true
      else if (this.$route.fullPath=="/practice") this.selected[2] = true
      else if (this.$route.fullPath=='wrongbook') this.selected[3] = true
      this.$store.dispatch('getUserInfo').then(res=>{
        if (this.$store.state.user.token) {
          this.isLogin = true;
          this.$store.dispatch('getUserInfoById', this.$store.state.user.userinfo.id).then(res=>{
            this.user = this.$store.state.user.userinfo;
            if (this.user.role == 'student') {
            } else if (this.user.role == 'teacher') {
              this.isTeacher = true
            } else {
              this.isTeacher = true
            }
          })
          
        }
      })
    },
    methods: {
      goToManageMain(){
        this.$router.push('/adminTable')
      },
      userinfo() {
        this.$router.push('/userInfo')
      },
      logout() {
        this.$store.dispatch('userLogout').then(res=>{
          this.$router.push('/login')
        })
      },
      change(index) {
        for (let i = 0; i < 4; i++) this.selected[i] = false
        this.selected[index] = true 
      }
    }
}
</script>

<style scoped>
.list li.active {
  background:#4da9eb;
}
.list li.active a {
  color: #fff;
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