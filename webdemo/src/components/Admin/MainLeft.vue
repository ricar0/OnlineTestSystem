<template>
  <div id="left">
    <el-menu
      active-text-color="#dd5862" 
      text-color="#000" 
      :default-active="this.$route.path"
      class="el-menu-vertical-demo" 
      @open="handleOpen" 
      @close="handleClose" 
      :collapse="flag"
      background-color="#124280"  
      menu-trigger="click" router>
      <el-submenu v-if="item.index!=5 || (item.index==5&&isAdmin)" v-for="(item,index) in menu" :index='item.index' :key="index">
        <template slot="title">
          <div class="left-width">
            <i class="iconfont" :class="item.icon"></i>
            <span slot="title" class="title">{{item.title}}</span>
          </div>
        </template>
        <el-menu-item-group v-for="(list,index1) in item.content" :key="index1">
          <el-menu-item style="color:#fff;" @click="handleTitle(item.index)" :index="list.path" v-if="list.item1 != null">{{list.item1}}</el-menu-item>
          <el-menu-item style="color:#fff;" @click="handleTitle(item.index)" :index="list.path" v-if="list.item2 != null">{{list.item2}}</el-menu-item>
          <el-menu-item style="color:#fff;" @click="handleTitle(item.index)" :index="list.path" v-if="list.item3 != null">{{list.item3}}</el-menu-item>
        </el-menu-item-group>
      </el-submenu>
    </el-menu>
  </div>
</template>

<script>
import Bus from '@/utils/bus.js'
import {getRole} from '@/utils/token.js'
export default {
  name: "mainLeft",
  data() {
    return {
      menu:[],
      flag: false,
      isAdmin: false,
    }
  },
  mounted() {
    let role = getRole()
    if (role == 'admin') this.isAdmin = true
    this.addData()
  },
  watch: {
    'flag': {
      immediate: true,
      handler() {
        Bus.$on('flag', (data=>{
          this.flag = data;
        }))
      }
    }
  },
  methods: {
    handleOpen(key, keyPath) {
      // console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      // console.log(key, keyPath);
    },
    //点击标题传递参数给navigator组件
    handleTitle(index) {
      this.bus.$emit('sendIndex',index)
    },
    addData() {
      // let role = this.$cookies.get("role")
      // if(role == 0) {
      this.menu.push({
        index: '1',
        title: '考试管理',
        icon: 'el-icon-s-order',
        content:[{item1: '考试查询',path: '/searchExam'}, {item2:'添加考试', path:'/addExam'}],
      }),
      this.menu.push({
        index: '2',
        title: '题库管理',
        icon: 'el-icon-s-home',
        content:[{item1:'添加科目',path:'/addSubject'},{item2: '题库查询',path: '/problemManage'}, {item3:'添加题库', path:'/addProblem'}],
      }),
      this.menu.push({
        index: '3',
        title: '成绩查询',
        icon: 'el-icon-s-data',
        content:[{item1: '学生成绩查询',path: '/searchStudentGrades'}, {item2:'成绩分段查询', path:'/searchExamGrades'}],
      }),
      this.menu.push({
        index: '4',
        title: '学生管理',
        icon: 'el-icon-user-solid',
        content:[{item1: '查询学生信息',path: '/studentManage'}, {item2:'添加学生', path:'/addUser'}],
      }),
      this.menu.push({
        index: '5',
        title: '教师管理',
        icon: 'el-icon-s-custom',
        content:[{item1:'教师管理',path:'/teacherManage'},{item2: '教师审批',path: '/addTeacher'}],
      })
      // }
    }
  },
}
</script>

<style>

.el-menu-vertical-demo .el-submenu__title {
  overflow: hidden;
  /* color: #fff; */
}
.left-width .iconfont {
  font-size: 18px;
  color: #fff;
}
.left-width {
  width: 213px;
}
.el-menu-vertical-demo:not(.el-menu--collapse) {
  min-height: 1000px;
}

#left {
  min-height: 1000px;
  background-color: #124280;
  z-index: 0;
}
#left .el-menu-vertical-demo .title {
  color: #fff;
  font-size: 16px;
  font-weight: bold;
  margin-left: 14px;
}
.el-submenu {
  border-bottom: 1px solid #eeeeee0f !important;
}
.el-submenu__title:hover {
  background-color: #fff;
}
.el-submenu__title i {
    color: #fbfbfc !important;
}
</style>