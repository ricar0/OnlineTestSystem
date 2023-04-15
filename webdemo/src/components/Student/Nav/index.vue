<template>
    <div class="container-left">
        <ul>
            <li v-for="(item,index) in items" :key="index" @click="select(index)"><a :class="{active:item.selected}">{{ item.value }}</a></li>
        </ul>
    </div>
</template>

<script>
export default {
    data() {
        return {
            items: [
                {value: '个人资料', selected: false},
                {value: '账号信息', selected: false},
                {value: '教师申请', selected: false}
            ],
        }
    },
    mounted() {
        if (this.$route.path == '/userInfo') 
            this.items[0].selected = true
        else if (this.$route.path.split('/')[1] == 'accountInfo') 
            this.items[1].selected = true
        else if (this.$route.path == '/applicationForTeacher')
            this.items[2].selected = true
    },
    methods: {
        select(index) {
            for (let i = 0; i < this.items.length; i++) this.items[i].selected = false
            this.items[index].selected = true
            if (index == 0) {
                this.$router.push('/userInfo')
            }
            else if (index == 1) {
                this.$router.push('/accountInfo')
            } else if (index == 2) {
                this.$router.push('/applicationForTeacher')
            }
        }
    }
}
</script>

<style scoped>
.container-left ul li a.active {
    background: #f0f0f5;
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
</style>