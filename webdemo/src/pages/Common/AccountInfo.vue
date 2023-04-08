<template>
    <div>
        <Header></Header>
        <div class="container">
            <Nav></Nav>
            <div class="container-right">
                <div class="container-account">
                    <div class="account-body">
                        <span class="account-body-title">账号设置</span>
                        <ul>
                            <li class="account-body-item">
                                <span>
                                    <span>密</span>
                                    <span>码</span>
                                </span>
                                <p>
                                    <span>修改密码以提高安全系数</span>
                                    <a @click="changePassword()">修改密码</a>
                                </p>
                            </li>
                            <li class="account-body-item">
                                <span>
                                    <span>手</span>
                                    <span>机</span>
                                </span>
                                <p>
                                    <span v-if="baseInfo.phone != null">{{baseInfo.phone.substr(0,3)}}****{{baseInfo.phone.substr(7,10)}}</span>
                                    <span v-if="baseInfo.phone == null">还没有绑定手机，马上绑定？</span>
                                    <a v-if="baseInfo.phone != null" @click="setPhoneNumber()">修改手机</a>
                                    <a v-if="baseInfo.phone == null" @click="setPhoneNumber()">绑定手机</a>
                                </p>
                            </li>
                            <li class="account-body-item">
                                <span>
                                    <span>邮</span>
                                    <span>箱</span>
                                </span>
                                <p>
                                    <span v-if="baseInfo.email != null">{{baseInfo.email.split('@')[0].substr(0,2)+'***@'+baseInfo.email.split('@')[1]}}</span>
                                    <span v-if="baseInfo.email == null">还没有绑定邮箱，马上绑定？</span>
                                    <a v-if="baseInfo.email != null" @click="setEmail(1)">修改邮箱</a>
                                    <a v-if="baseInfo.email == null" @click="setEmail(2)">绑定邮箱</a>
                                </p>
                            </li>
                        </ul>
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
            baseInfo: ''
        }
    },
    mounted() {
        this.$store.dispatch('getUserInfo').then(res=>{
            let id = this.$store.state.user.userinfo.id
            this.$store.dispatch('getUserInfoById', id).then(res=>{
                this.baseInfo = this.$store.state.user.user
            })
        })
    },
    methods: {
        changePassword() {
            this.$router.push('/accountInfo/password')
        },
        setPhoneNumber() {
            this.$router.push('/accountInfo/phoneNumber')
        },
        setEmail(index) {
            localStorage.setItem('index', index)
            this.$router.push('/accountInfo/email')
        }
    }
}
</script>
<style scoped>

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
.account-body-item {
    height: 24px;
    padding: 32px 0 16px;
    color: #555666;
    border-bottom: 1px solid rgb(216, 207, 207);
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