<template>
    <div>
        <span>账号：</span>
        <el-input style="width:200px;" placeholder="请输入用户名" v-model="username"></el-input>
        <br>
        <span>密码：</span>
        <el-input style="width:200px;" placeholder="请输入密码" v-model="password"></el-input>
        <br>
        <el-button @click="login">登录</el-button>
        token: {{ $store.state.user.token }}
    </div>
</template>

<script>
export default {
    data() {
        return {
            username:'',
            password:''
        }
    },
    methods: {
        login() {
            const {username,password} = this;
            (username&&password)&&this.$store.dispatch('userLogin',{username,password});
        }
    },
}
</script>
<style scoped>
    
</style>>