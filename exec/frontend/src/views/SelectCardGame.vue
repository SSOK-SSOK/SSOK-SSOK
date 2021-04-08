<template>
  <v-container fluid fill-height>
    <div class="background">
      <div id="stars"></div>
      <div id="stars2"></div>
      <div id="stars3"></div>
    </div>
    <div class="contents">
      <nav>
        <v-tooltip bottom color="deep-purple accent-3">
          <template v-slot:activator="{ on, attrs }">
            <div
              v-bind="attrs"
              v-on="on"
              class="glow"
              @click="moveMainPage"
            ></div>
          </template>
          <span>게임 선택 GO GO!</span>
        </v-tooltip>
      </nav>
      <div class="stepperArea">
        <Stepper2
          v-if="!goToSelect"
          class="d-none d-md-block"
          @stepperDone="stepperDone"
        />
        <button
          class="gold-button mx-auto d-block d-md-none"
          @click="stepperDone"
        >
          게임하기
        </button>
        <CardCategory v-if="goToSelect" />
      </div>
    </div>
  </v-container>
</template>

<script>
import CardCategory from "@/components/CardCategory.vue";
import Stepper2 from "@/components/Stepper2.vue";
export default {
  name: "SelectCardGame",
  data() {
    return {
      goToSelect: false,
    };
  },
  components: {
    CardCategory,
    Stepper2,
  },
  methods: {
    moveMainPage() {
      this.$router.push({ name: "MainPage" });
    },
    stepperDone() {
      this.goToSelect = true;
    },
  },
};
</script>

<style lang="scss" scoped>
@import "@/style/star.sass";
@import "@/style/light-button.scss";
@import "@/style/gold-button.scss";

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
      background: none;
      color: white;
      font-size: 1rem;
    }
    .stepperArea {
      width: 100%;
      height: 70%;
      display: flex;
      justify-content: center;
      align-items: center;
    }
  }
}
</style>
