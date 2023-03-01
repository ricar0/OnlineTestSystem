module.exports = {
  transpileDependencies: true,
  lintOnSave: false,
  //代理跨域
  devServer:{
    proxy: {
      'api': {
        target: 'http://localhost:8080'
      }
    }
  }
}
