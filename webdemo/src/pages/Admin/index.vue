<template>
  <div id="index">
    <section class="container">
      <Header></Header>
      <div class="left_side">
        <MainLeft></MainLeft>
      </div>
      <div class="content">
        <router-view></router-view>
      </div>
    </section>
  </div>
</template>

<script>
import Header from '@/components/Admin/Header.vue'
import MainLeft from '@/components/Admin/MainLeft.vue'
export default {
  components:{
    MainLeft,
    Header,
  },
  data() {
    return {

    }
  },
  methods: {
    
  }
}
</script>

<style lang="less" scoped>
.left_side {
  margin-top: 60px;
}
.container {
  display: flex;
  background-image: url('@/assets/background.png');
  // height: 900px;
  // background-color: #fff;
}
.main_wrapper {
  overflow: hidden;
  flex: 1;
//   background-color: #fff;
}
.content {
  margin-top: 80px;
  height: 100%;
  width: 100%;
}
</style>