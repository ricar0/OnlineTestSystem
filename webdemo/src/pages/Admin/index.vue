<template>
  <div id="index">
    <section class="container">
      <div class="left_side">
        <MainLeft></MainLeft>
      </div>
      <div class="main_wrapper">
        <!-- <Navigator class="nav"></Navigator> -->
        <router-view></router-view>
      </div>
    </section>
  </div>
</template>

<script>
import MainLeft from '@/components/Admin/MainLeft.vue'
import Navigator from '@/components/Admin/MainLeft.vue'
export default {
  components:{
    MainLeft,
    Navigator
  },
  data() {
    return {
      username: '许如梦'
    }
  },
  methods: {
    
  }
}
</script>

<style lang="less" scoped>
#index .nav {
  box-shadow: 1px 0 5px rgba(0, 0, 0, 0.1);
  margin-bottom: 30px;
}
.container {
  display: flex;
//   background-color: #fff;
}
.main_wrapper {
  overflow: hidden;
  flex: 1;
//   background-color: #fff;
}
</style>