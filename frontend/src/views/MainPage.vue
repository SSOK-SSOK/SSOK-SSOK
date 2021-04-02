<template>
  <v-container fluid fill-height>
    <div class="background">
      <div id="stars"></div>
      <div id="stars2"></div>
      <div id="stars3"></div>
    </div>
    <div class="contents">
      <nav>
        <div @click="back">
          <div class="glow"></div>
        </div>
      </nav>
      <div class="planets">
        <div class="venus">
          <div class="venusimg">
            <img
              @click="moveSelectCardGame"
              class="spin"
              src="@/assets/images/venus.png"
              alt="venus"
            />
          </div>
          <div class="mascotimg">
            <img
              @click="moveSelectCardGame"
              class="mascot-card ma-0"
              src="@/assets/images/card.png"
              alt="macot-card"
            />
          </div>
          <p>카드 게임</p>
        </div>
        <div class="greenus">
          <div class="greenusimg">
            <img
              @click="moveSmartCameraGame"
              class="spin"
              src="@/assets/images/greenus.png"
              alt="greenus"
            />
          </div>
          <div class="mascotimg2">
            <img
              @click="moveSmartCameraGame"
              class="mascot-camera"
              src="@/assets/images/camera.png"
              alt="macot-camera"
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
    back: function () {
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
    position: relative;
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
    nav {
      display: flex;
      justify-content: space-between;
      align-items: center;
      padding: 2.5% 1.5%;
      margin-bottom: 5%;
      height: 5%;
      width: 100%;
      color: white;
      background: none;
      font-size: 1rem;
      div {
        color: white;
      }
    }
    .planets {
      display: flex;
      width: 100%;
      height: 100%;
      justify-content: center;
      margin-top: 8%;
      .venus {
        cursor: pointer;
        width: 50%;
        display: flex;
        justify-content: center;
        .venusimg {
          position: absolute;
          justify-content: center;
          align-items: center;
          top: 43%;
          left: 18%;
          .spin {
            width: 90%;
            height: 90%;
            display: flex;
            justify-content: center;
            align-items: top;
            animation: rotate 12s linear infinite;
            transform-origin: 50% 50%;
          }
        }
        .mascotimg {
          display: flex;
          justify-content: center;
          position: absolute;
          top: 15%;
          left: 16%;
          .mascot-card {
            width: 45%;
            height: 45%;
          }
        }
        p {
          background-color: rgb(0, 0, 0, 50%);
          position: absolute;
          top: 57%;
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
        .greenusimg {
          position: absolute;
          justify-content: center;
          align-items: center;
          top: 43%;
          left: 58%;
          .spin {
            width: 90%;
            height: 90%;
            display: flex;
            justify-content: center;
            align-items: top;
            animation: rotate 12s linear infinite;
            transform-origin: 50% 50%;
          }
        }
        .mascotimg2 {
          display: flex;
          justify-content: center;
          position: absolute;
          top: 16%;
          right: 16.5%;
          .mascot-camera {
            width: 45%;
            height: 45%;
          }
        }
        p {
          background-color: rgb(0, 0, 0, 50%);
          position: absolute;
          top: 57%;
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
