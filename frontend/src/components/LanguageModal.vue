<template>
  <div class="modal-mask">
    <div class="modal-wrapper">
      <div class="modal-container">
        <div class="modal-header">
          <p>📚학습할 언어를 선택해 주세요📚</p>
        </div>
        <div class="modal-body">
          <v-btn-toggle
            class="languages"
            v-model="selected_language"
            mandatory dark color="deep-purple accent-3" group>
            <v-btn class="language" v-for="(language, name) in languages" :key="name">
              {{language.name}}
            </v-btn>
          </v-btn-toggle>
        </div>
        <div class="modal-footer">
          <button @click="sendInfo">
            OK
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'LanguageModal',
  data() {
    return {
      selected_language: undefined,  
      languages: [
        { id: 0, name: "kr"},
        { id: 1, name: "us"},
        { id: 2, name: "cn"},
        { id: 3, name: "jp"},
        { id: 4, name: "vn"},
        { id: 5, name: "fr"},
        { id: 6, name: "es"},
      ],  
    };
  },
  mounted() {
    
  },
  methods: {
    sendInfo() {
      this.$emit('close', this.selected_language)
    }
  },
};
</script>

<style lang="scss" scoped>
*{
  color: white;
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
      width: 45%;
      height: 35%;
      padding: 1% 2%;
      margin: 0 auto;
      border-radius: 20px;
      box-shadow: 0 2px 8px rgba(0, 0, 0, .33);
      background-color: rgba(22, 18, 0, 0.4);
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
        .languages{
          display: flex;
          justify-content: center;
          align-items: center;
          width: 100%;
          height: 100%;
          .language{
            width: 13%;
            height: 40%;
            margin: 0;
            overflow: hidden;
            background-color: rgba(22, 18, 0, 0.4);
            box-shadow: 0 2px 3px 0 rgba(151, 150, 146, 0.4);
            cursor: pointer;
          }
        }
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