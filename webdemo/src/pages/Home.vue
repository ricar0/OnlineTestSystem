<template>
    <div>
        这里是Home
        <router-link to="/">首页</router-link>
        <router-link to="/register">注册</router-link>
        <router-link to="/login">登录</router-link>
        <router-link to="/student">学生模块</router-link>
        <el-input label-width="100px" v-model="keywords" placeholder="请输入内容"></el-input>
        <el-button @click="goSearch">搜索</el-button>
    </div>
</template>

<script>
export default {
    name:"",
    data() {
      return {
        keywords:''
      }
    }, 
    methods: {
      goSearch() {
        // this.$router.push("/search/"+this.keywords+"?k="+this.keywords.toUpperCase());
        // this.$router.push(`/search/${this.keywords}?k=${this.keywords.toUpperCase()}`);
        this.$router.push({
            name:"search",
            params: {keywords:this.keywords},
            query:{k:this.keywords.toUpperCase()}
        })
      }
    }
}
</script>