<template>
  <div class="modal-mask">
    <div class="modal-wrapper">
      <div class="modal-container">
        <div class="modal-header">
          <p>✨당신의 점수는?✨</p>
        </div>
        <div class="modal-body">
          <p>5 / 10</p>
        </div>
        <div class="modal-footer">
          <button @click="$emit('close')">
            OK
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'ScoreModal',
  data() {
    return {
      
    };
  },
  mounted() {
    
  },
  methods: {
  },
};
</script>

<style lang="scss" scoped>
*{
  color: black;
}
.modal-mask {
  position: fixed;
  z-index: 9998;
  top: 0;
  left: 0;
  display: table;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, .3);
  .modal-wrapper {
  display: table-cell;
  vertical-align: middle;
    .modal-container {
      width: 35%;
      height: 35%;
      padding: 1% 2%;
      margin: 0 auto;
      border-radius: 20px;
      box-shadow: 0 2px 8px rgba(0, 0, 0, .33);
      background-color: #fff;
      .modal-header{
        display: flex;
        justify-content: center;
        width: 100%;
        height: 25%;
        padding-top: 1%;
        font-size: 1.5em;
      }
      .modal-body {
        display: flex;
        justify-content: center;
        align-items: center;
        width: 100%;
        height: 60%;
        font-size: 3em;
      }
      .modal-footer{
        display: flex;
        justify-content: flex-end;
        align-items: center;
        width: 100%;
        height: 15%;
      }
    }
  }
}
</style>