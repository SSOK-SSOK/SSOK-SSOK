<template>
  <v-container fluid fill-height>
    <div class="background">
      <div id="stars"></div>
      <div id="stars2"></div>
      <div id="stars3"></div>
    </div>
    <div class="contents">
      <div class="body">
        <p>페이지를 찾을 수 없습니다.</p>
        <p>홈페이지로 돌아가려면 아래의 버튼을 눌러주세요.</p>
        <div @click="moveWelcomePage" class="glow"></div>
      </div>
    </div>
  </v-container>
</template>

<script>
export default {
  name: 'FrontendPagenotfound', 
  data() {
    return {   
    }
  },
  methods: {
    moveWelcomePage() {
      this.$router.push({ name: "WelcomePage" });
    },
  },
};
</script>

<style lang="scss" scoped>
@import "@/style/star.sass";
@import "@/style/light-button.scss";
.container {
  padding: 1%;
  .background {
    position: absolute;
    z-index: -1;
    width: 100%;
    height: 100%;
  }
  .contents {
    position: absolute;
    display: flex;
    justify-content: center;
    align-items: center;
    width: 100%;
    height: 100%;
    left: 0;
    .body{
      p{
        text-align: center;
        font-size: 2vw;
      }
      .glow{
        margin: 10% auto 0;
      }
    }
  }
}
</style>