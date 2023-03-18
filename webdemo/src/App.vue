<template>
  <div id="app">
    <router-view></router-view>
    <Footer v-show="$route.meta.show"></Footer>
  </div>
</template>

<script>

import Header from '@/components/Student/Header'
import Footer from '@/components/Student/Footer'
export default {
  components: {
    Header,
    Footer
  }
}
</script>

<style>
/* #app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
} */
#app {
  height: 100%;
  width: 100%;
  top: 0;
  left: 0;
  position: absolute;
  background-image: url('@/assets/background.png');
}
</style>
