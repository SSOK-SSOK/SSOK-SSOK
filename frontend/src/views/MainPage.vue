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
              class="glow mx-2"
              @click="moveWelcomePage"
            ></div>
          </template>
          <span>웰컴페이지 GO GO!</span>
        </v-tooltip>
      </nav>
      <div class="mascots">
        <div class="cardMascot">
          <img
            @click="moveSelectCardGame"
            class="mascot-card ma-0"
            src="@/assets/images/card.png"
            alt="macot-card"
          />
        </div>
        <div class="cameraMascot">
          <img
            @click="moveSmartCameraGame"
            class="mascot-camera"
            src="@/assets/images/camera.png"
            alt="macot-camera"
          />
        </div>
      </div>
      <div class="planets">
        <!--카드게임-->
        <div class="venus">
          <div class="venusimg">
            <img
              @click="moveSelectCardGame"
              class="spin"
              src="@/assets/images/venus.png"
              alt="venus"
            />
          </div>
          <p>카드 게임</p>
        </div>
        <!--스마트카메라게임-->
        <div class="greenus">
          <div class="greenusimg">
            <img
              @click="moveSmartCameraGame"
              class="spin"
              src="@/assets/images/greenus.png"
              alt="greenus"
            />
          </div>
          <p>카메라 게임</p>
        </div>
      </div>
    </div>
  </v-container>
</template>

<script>
export default {
  name: "MainPage",
  data: () => ({
    drawer: false,
    group: null,
  }),

  watch: {
    group() {
      this.drawer = false;
    },
  },
  methods: {
    moveSmartCameraGame: function () {
      this.$router.push({ name: "SmartCameraGame" });
    },
    moveSelectCardGame: function () {
      this.$router.push({ name: "SelectCardGame" });
    },
    moveWelcomePage: function () {
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
    width: 100%;
    height: 100%;
    left: 0;
    padding: 1%;
    // navbar
    nav {
      display: flex;
      justify-content: space-between;
      align-items: center;
      padding: 2.5% 1.5%;
      margin-bottom: 2%;
      height: 5%;
      width: 100%;
      color: white;
      background: none;
      font-size: 1rem;
      div {
        color: white;
      }
    }
    // 캐릭터
    .mascots {
      position: relative;
      display: flex;
      height: 30%;
      width: 100%;
      z-index: 5;
      .cardMascot {
        display: flex;
        justify-content: center;
        width: 50%;
        height: 100%;
        .mascot-card {
          height: 100%;
        }
      }
      .cameraMascot {
        display: flex;
        justify-content: center;
        width: 50%;
        height: 100%;
        .mascot-camera {
          height: 100%;
        }
      }
    }
    .planets {
      display: flex;
      width: 100%;
      height: 50%;
      .venus {
        cursor: pointer;
        width: 50%;
        height: 100%;
        display: flex;
        justify-content: center;
        align-items: center;
        .venusimg {
          display: flex;
          position: absolute;
          justify-content: center;
          align-items: center;
          .spin {
            width: 85%;
            height: 85%;
            display: flex;
            justify-content: center;
            align-items: top;
            animation: rotate 12s linear infinite;
            transform-origin: 50% 50%;
          }
        }
        p {
          background-color: rgb(0, 0, 0, 50%);
          z-index: 5;
          color: white;
          font-size: 6vh;
          font-weight: bold;
        }
      }
      .greenus {
        cursor: pointer;
        width: 50%;
        display: flex;
        justify-content: center;
        align-items: center;
        .greenusimg {
          display: flex;
          position: absolute;
          justify-content: center;
          align-items: center;
          .spin {
            width: 85%;
            height: 85%;
            display: flex;
            justify-content: center;
            align-items: top;
            animation: rotate 12s linear infinite;
            transform-origin: 50% 50%;
          }
        }
        p {
          background-color: rgb(0, 0, 0, 50%);
          z-index: 5;
          color: white;
          font-size: 6vh;
          font-weight: bold;
        }
      }
    }
  }
}

@keyframes rotate {
  100% {
    transform: rotate(360deg);
  }
}
</style>
