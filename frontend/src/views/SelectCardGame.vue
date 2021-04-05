<template>
  <v-container fluid fill-height>
    <div class="background">
      <div id="stars"></div>
      <div id="stars2"></div>
      <div id="stars3"></div>
    </div>
    <div class="contents">
      <nav>
        <div>
          <div class="glow" @click="moveToMainPage"></div>
        </div>
      </nav>
      <CardCategory />
    </div>
  </v-container>
</template>

<script>
import CardCategory from "@/components/CardCategory.vue";
export default {
  name: "SelectCardGame",
  components: {
    CardCategory,
  },
  methods: {
    moveToMainPage: function () {
      this.$router.push({ name: "MainPage" });
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
    left: 0;
    width: 100%;
    height: 100%;
    padding: 1%;
    nav {
      display: flex;
      justify-content: space-between;
      align-items: center;
      width: 100%;
      height: 5%;
      padding: 2.5% 1.5%;
      margin-bottom: 1%;
      color: white;
      background: none;
      font-size: 1rem;
      div {
        //light-button
      }
    }
  }
}
</style>
