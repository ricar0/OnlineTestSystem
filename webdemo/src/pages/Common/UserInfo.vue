<template>
    <div>
        <Header></Header>
        <div class="container"> 
            <div class="container-left">
                <ul>
                    <li><a>个人资料</a></li>
                    <li><a>账号设置</a></li>
                </ul>
            </div>
            <div class="container-right">
                <div class="base-info-head">基本信息</div>
                <div class="base-info-content">
                    <div class="base-info-content-show">
                        <el-form></el-form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import Header from '@/components/Student/Header'
export default {
    components: {
        Header
    }
}
</script>

<style scoped>
.base-info-content-show {
    padding: 16px 58px 16px 0;
    position: relative;
}
.base-info-content {
    padding: 16px;
}
.base-info-head {
    height: 48px;
    line-height: 48px;
    font-size: 18px;
    font-weight: 600;
    color: #2e2e2e;
    padding-left: 16px;
    border-bottom: 1px solid #f0f0f2;
}
.container {
    position: relative;
    width: auto;
    min-width: 896px;
    min-height: 800px;
    background: transparent;
    margin: 80px auto 0;
}
.container-left ul {
    width: 100%;
    list-style: none;
    font-size: 14px;
    padding: 8px 0;
}
.container-left ul li {
    font-weight: 500;
    color: #222226;
    background: #f0f0f5;
}
.container-left ul li a {
    display: block;
    width: 100%;
    height: 48px;
    line-height: 48px;
    text-align: center;
    color: #555666;
    text-decoration: none;
    cursor: pointer;
    background: #fff;
}
.container-left ul li a:hover {
    display: block;
    width: 100%;
    height: 48px;
    line-height: 48px;
    text-align: center;
    color: #555666;
    background:#f0f0f5;
}
.container-left {
    position: absolute;
    width: 190px;
    top: 0;
    float: left;
    background: #fff;
}
.container-right {
    min-width: 1000px;
    margin-left: 200px;
    min-height: 800px;
}
</style>