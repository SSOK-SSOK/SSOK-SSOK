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
          <div class="glow" @click="moveToMainPage"></div>
        </div>
        <div @click.stop="drawer = !drawer">
          <v-icon style="color: white;">mdi-menu</v-icon>
        </div>
      </nav>
      <div class="planets">
        <div class="venus">
          <div>
            <div><img class="spin" src="@/assets/images/venus.png" alt="venus"/></div>
          </div>
          <div>
            <div><img @click="moveToCG" class="mascot-card" src="@/assets/images/card.png" alt="macot-card"/><p>카드 게임</p></div>          
          </div>
        </div>
        <div class="greenus">
           <div>
            <div><img class="spin" src="@/assets/images/greenus.png" alt="greenus"/></div>
          </div>
          <div>
            <div><img @click="moveToSC" class="mascot-camera" src="@/assets/images/camera.png" alt="macot-camera"/><p>카메라 게임</p></div>          
          </div>     
        </div>
      </div>
    </div>
    <v-navigation-drawer
      v-model="drawer"
      absolute
      right
      dark
      bottom
      temporary
    >
      <div style="text-align: end;">
        <v-btn icon><v-icon @click.stop="drawer = !drawer" size="50">mdi-close-box</v-icon></v-btn>
      </div>
      <v-list>
        <v-list-item-group v-model="group" active-class="text--accent-4">
          <v-list-item>
            <v-list-item-title>도움말</v-list-item-title>
          </v-list-item>

          <v-list-item>
            <v-list-item-title>서비스 소개</v-list-item-title>
          </v-list-item>

          <v-list-item>
            <v-list-item-title>업데이트</v-list-item-title>
          </v-list-item>
        </v-list-item-group>
      </v-list>
    </v-navigation-drawer>
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
    moveToSC: function () {
      this.$router.push({ name: "SmartCamera" });
    },
    moveToCG: function () {
      this.$router.push({ name: "CardGame" });
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
.container{
  padding: 1%;
  .background{
    position: relative;
    z-index: -1;
    width: 100%;
    height: 100%;
  }
  .contents{
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
      height:5%;
      width: 100%;
      color: white;
      background: none;
      font-size: 1rem;
      div{
        //light-button
      }
      div{
        color: white;
      }
    }
    .planets{
      display: flex;
      width: 100%;
      height: 100%;
      justify-content: center;
      margin-top: 8%;
      .venus{
        cursor: pointer;
        width: 50%;
        display: flex;
        justify-content: center;
        div{
          position: absolute;
          div{
            position: relative;
            .spin{
              width: 90%;
              height: 90%;
              animation: rotate 12s linear infinite;
              transform-origin: 50% 50%;    
            }
          }
        }
        div{
          position: absolute;
          div{
            position: relative;
            text-align: center;
            .mascot-card{
              width: 50%;
              height: 50%;
            }
            p{
              color: black;
              font-size: 5vh;
              font-weight: bold;
              margin-right: 5%;
            }
          }
        }
      }
      .greenus{
        cursor: pointer;
        width: 50%;
        display: flex;
        justify-content: center;
        div{
          position: absolute;
          div{
            position: relative;
            .spin{
              width: 90%;
              height: 90%;
              animation: rotate 10s linear infinite;
              transform-origin: 50% 50%;    
            }
          }
        }
        div{
          position: absolute;
          div{
            position: relative;
            text-align: center;
            .mascot-camera{
              width: 50%;
              height: 50%; 
            }
            p{
              color: black;
              font-size: 5vh;
              font-weight: bold;
              margin-left: 6%;
            }
          }
        }
      }
    }
  }
}

@keyframes rotate{
	100% {
    transform: rotate(360deg);
  }
}
</style>