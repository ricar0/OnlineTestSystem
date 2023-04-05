<template>
    <div v-show="visible" class="popup">
        <div :class="['popup__content', type]">
          <slot></slot>
          <div class="popup__buttons">
            <el-button size="small" type="primary" v-if="type === 'error'" @click="addToMistakes">添加到错题本</el-button>
            <el-button size="small" type="primary" @click="close">关闭</el-button>
          </div>
        </div>
    </div>
</template>

<script>
export default {
props: {
    visible: {
    type: Boolean,
    default: false
    },
    type: {
    type: String,
    default: 'success'
    }
},
methods: {
    close() {
    this.$emit('close');
    },
    addToMistakes() {
    // 添加到错题本的逻辑
    this.$emit('add-to-mistakes');
    }
}
};
</script>

<style>
.popup {
  position: fixed;
  top: 0;
  left: 0;
  bottom: 0;
  right: 0;
  background-color: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
}

.popup__content {
  height: auto;
  width: 200px;
  padding: 20px;
  background-color: #fff;
  border-radius: 5px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.5);
  text-align: center;
}

.popup__content.success {
  color: green;
}

.popup__content.error {
  color: red;
}

.popup__buttons {
  margin-top: 50px;
}
</style>