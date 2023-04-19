<template>
    <div>
        <Header></Header>
        <div class="container">
            <Nav></Nav>
            <div class="container-right">
                <div class="container-account">
                    <div class="account-body">
                        <span class="account-body-title">教师申请</span>
                        <el-divider></el-divider>
                        <div>
                            <el-steps v-if="state == 0 || state == -1" :active="active" finish-status="success">
                                <el-step title="提交申请"></el-step>
                                <el-step title="等待审批"></el-step>
                                <el-step title="审批结束"></el-step>
                            </el-steps>
                            <el-steps v-if="state == 2" :active="active">
                                <el-step status="success" title="提交申请"></el-step>
                                <el-step status="success" title="等待审批"></el-step>
                                <el-step status="error" title="审批不通过" description="可以重新提交申请"></el-step>
                            </el-steps>
                            <el-steps v-if="state == 1" :active="active" finish-status="success">
                                <el-step title="提交申请"></el-step>
                                <el-step title="等待审批"></el-step>
                                <el-step title="审批已通过"></el-step>
                            </el-steps>
                        </div>
                        <div style="margin-left: 30px;width: 300px;">
                            <p><i class="el-icon-warning"></i> 要求如下：</p>
                            <div v-for="id in 4" :key="id" class="text item">
                                {{id+': '+ item[id-1]}}
                            </div>
                        </div>
                        <div class="account-behind">
                            <el-upload
                                class="upload-demo"
                                action="#"
                                accept=".jpg,.png"
                                multiple
                                :limit="4"
                                :on-preview="handlePreview"
                                :on-remove="handleRemove"
                                :on-change="handleChange"
                                :file-list="fileList"
                                :on-exceed="limitCheck"
                                :auto-upload="false"
                                list-type="picture">
                                <el-button size="small" type="primary">点击上传</el-button>
                                <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb,不超过4张图片</div>
                            </el-upload>
                            <el-button v-if="state == 0" style="margin-top: 30px;" @click="submit()" disabled>确认提交</el-button>
                            <el-button v-if="state != 0" style="margin-top: 30px;" @click="submit()">确认提交</el-button>
                        </div>
                        
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import Header from '@/components/Student/Header'
import Nav from '@/components/Student/Nav'

export default {
    components: {
        Header,
        Nav
    },
    data() {
        return {
            item: ['身份证','教师资格证','毕业证书', '其他可以证明的材料'],
            fileList: [],
            active: 0,
            state: 0
        };
    },
    mounted() {
        this.$store.dispatch('getUserInfo').then(res=>{
            let user_id = this.$store.state.user.userinfo.id
            this.$store.dispatch('getCheckStateById', {user_id}).then(res=>{
                this.state = this.$store.state.role.sta
                if (this.state == 0) this.active = 1
                else if (this.state == 1) this.active = 3
                else if (this.state == 2) this.active = 3
            })
        })
        
    },
    methods: {
        handleRemove(file, fileList) {
            this.fileList = fileList
        },
        handlePreview(file) {
            console.log(file);
        },
        limitCheck() {
            this.$message.warning('每次上传限制最多四个文件')
        },
        handleChange(file, fileList) {
            this.fileList = fileList
            const isLt500k = (file.size / 1024 < 500)
            if (!isLt500k) {
                this.$message.error('上传图片大小不能超过500kb !')
                this.fileList.pop()
            }
            return isLt500k
        },
        submit() {
            let formData = new FormData()
            this.fileList.forEach((val, index) => {
    		    formData.append('files', val.raw)
    	    })
            this.$store.dispatch('getUserInfo').then(res=>{
                formData.append('user_id', this.$store.state.user.userinfo.id)
                let time = new Date()
                time = time.format('yyyy-MM-dd hh:mm:ss')
                formData.append('time', time)
                this.$store.dispatch('UploadPic', formData).then(res=>{
                    if (res == 'ok') {
                        this.$message({type:'success', message:'上传成功!'})
                        this.active++;
                    }
                })
            })
            
        }
    }
}
</script>
<style scoped>
.account-behind {
    padding: 30px;
}
.account-body-item p a {
    display: inline-block;
    min-width: 56px;
    margin-left: 32px;
    color: #1989fa;
    cursor: pointer;
}
.account-body-item p {
    margin: 0;
    float: left;
    text-align: right;
    width: calc(100% - 72px);
    color: #222226;
}
.container-account .account-body .account-body-item>span {
    float: left;
    width: 56px;
    height: 24px;
    display: -webkit-box;
    display: -ms-flexbox;
    display: flex;
    -webkit-box-pack: justify;
    -ms-flex-pack: justify;
    justify-content: space-between;
    padding-left: 16px;
    -webkit-box-sizing: content-box;
    box-sizing: content-box;
}

.container-account {
    width: 70%;
}
.account-body-title {
    min-width: 72px;
    height: 24px;
    font-size: 18px;
    font-weight: 600;
    color: #2e2e2e;
    line-height: 24px;
}
.container {
    position: relative;
    width: auto;
    min-width: 50%;
    min-height: 800px;
    background: transparent;
    margin-top: 80px;
    margin-left: 20%;
}
.container .container-right {
    min-width: 50%;
    margin-left: 200px;
    min-height: 800px;
}

.account-body {
    width: 100%;
    padding: 24px 16px 10px;
    min-height: 200px;
    background: #fff;
    font-size: 14px;
}
</style>