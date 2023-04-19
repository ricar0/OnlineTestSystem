<template>
    <div class="add">
        <p style="margin:0; font-size:25px;">添加学生</p>
        <el-divider></el-divider>
        <div> 
            <el-form label-width="100px" class="demo-ruleForm" style="width:30%;">
                <el-form-item label="用户名">
                    <el-input v-model="username" autocomplete="off" prefix-icon="el-icon-edit-outline"></el-input>
                </el-form-item>
                <el-form-item label="密码">
                    <el-input type="password" v-model="password" autocomplete="off" prefix-icon="el-icon-edit-outline"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="submitForm()">提交</el-button>
                    <el-button @click="resetForm()">重置</el-button>
                </el-form-item>
                </el-form>
        </div>
    </div>    
</template>

<script>
export default {
    data() {
        return {
            username: '',
            password: ''
        }
    },
    methods: {
        submitForm() {
            this.$store.dispatch('addUser', {username:this.username,password:this.password}).then(res=>{
                if (res=='ok') {
                    this.$message.success("添加成功!")
                }
            })
        },
        resetForm() {
            this.username = '',
            this.password = ''
        }
    }
}
</script>

<style scoped>
.add {
  margin: 0px 40px;
}
.subject-input {
    display: flex;
    align-items: center;
}

.subject-input__label {
    font-weight: bold;
    margin-right: 10px;
}

.subject-input__input {
    width: 300px;
    border-radius: 4px;
    border: 1px solid #dcdfe6;
    font-size: 14px;
}

.subject-input__button {
    margin-left: 10px;
    border-radius: 4px;
    font-size: 14px;
}
</style>