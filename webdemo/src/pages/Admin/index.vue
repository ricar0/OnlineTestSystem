<template>
  <div id="index">
    <section class="container">
      <Header></Header>
      <div class="left_side">
        <MainLeft></MainLeft>
      </div>
      <div class="content"></div>
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
      username: '许如梦'
    }
  },
  methods: {
    
  }
}
</script>

<style lang="less" scoped>
.left_side {
  margin-top: 3.2%;
}
.container {
  display: flex;
  background-image: url('@/assets/background.png');
  // height: 100%;
  // background-color: #fff;
}
.main_wrapper {
  overflow: hidden;
  flex: 1;
//   background-color: #fff;
}
</style>