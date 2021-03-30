<template>
  <v-container fluid fill-height pa-0>
<<<<<<< HEAD
    <div class="left">
      <div id="stars"></div>
      <div id="stars2"></div>
      <div id="stars3"></div>
      <div>
        <p>메인으로 돌아가기</p>
      </div>
      <!-- <div class="ma-4">
        <v-btn icon class="ml-5 mt-5" @click="back"><v-icon style="color: white;" size="90">mdi-play-box</v-icon></v-btn>
      </div> -->
      <div style="position: relative; z-index: 100; float: left;">
        <img src="@/assets/images/purple-note.png" alt="purple-note-mascot"></img>
      </div>
    </div>

    <div class="right">
      <div class="right-card">
        <v-row>
          <p>카드<br>게임하기</p>
          <v-spacer></v-spacer>
          <v-btn icon class="mr-5 mt-5" @click.stop="drawer = !drawer"><v-icon style="color: white;" size="90">mdi-menu</v-icon></v-btn>
        </v-row>
        <v-row>
          <v-btn icon class="ml-5 mt-5" @click="moveToCG"><v-icon style="color: white;" size="90">mdi-play-box</v-icon></v-btn>
        </v-row>
      </div>
      <div class="right-camera">
        <v-row>
          <p class="header">스마트 카메라<br>게임하기</p>
        </v-row>
        <v-row>
          <v-btn icon class="ml-5 mt-5" @click="moveToSC"><v-icon style="color: white;" size="90">mdi-play-box</v-icon></v-btn>
        </v-row>
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
=======
    <v-col
      xl="6"
      lg="6"
      md="6"
      sm="12"
      xs="12"
      style="height: 100%; padding: 0"
    >
      <div class="ma-4">
        <p class="header">메인으로<br />돌아가기</p>
      </div>
      <div class="ma-4">
        <v-btn icon class="ml-5 mt-5" @click="back"
          ><v-icon style="color: white" size="90">mdi-play-box</v-icon></v-btn
        >
      </div>
      <div>
        <img src="@/assets/images/purple-note.png" alt="purple-note-mascot"></img>
      </div>
    </v-col>

    <v-col style="height: 100%; padding: 0">
      <v-row style="background-color: #f1ca21; height: 50.5%">
        <v-container class="pa-0">
          <v-row class="ma-4">
            <p class="header">카드<br />게임하기</p>
            <v-spacer></v-spacer>
            <v-btn icon class="mr-5 mt-5" @click.stop="drawer = !drawer"
              ><v-icon style="color: white" size="90">mdi-menu</v-icon></v-btn
            >
          </v-row>
          <v-row class="ma-4">
            <v-btn icon class="ml-5 mt-5" @click="moveToCG"
              ><v-icon style="color: white" size="90"
                >mdi-play-box</v-icon
              ></v-btn
            >
          </v-row>
        </v-container>
      </v-row>
      <v-row style="background-color: #3ad0de; height: 51%">
        <v-container class="pa-0">
          <v-row class="ma-4">
            <p class="header">스마트 카메라<br />게임하기</p>
          </v-row>
          <v-row class="ma-4">
            <v-btn icon class="ml-5 mt-5" @click="moveToSC"
              ><v-icon style="color: white" size="90"
                >mdi-play-box</v-icon
              ></v-btn
            >
          </v-row>
        </v-container>
      </v-row>
    </v-col>

    <v-navigation-drawer v-model="drawer" absolute right dark bottom temporary>
      <div style="text-align: end">
        <v-btn icon
          ><v-icon @click.stop="drawer = !drawer" size="50"
            >mdi-close-box</v-icon
          ></v-btn
        >
>>>>>>> 5cce13554b0111c47f2ac9e74756b31bf004b6a4
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
<<<<<<< HEAD
import "@/style/star.sass";
import "@/style/game-button.scss";
=======
>>>>>>> 5cce13554b0111c47f2ac9e74756b31bf004b6a4
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
.container{
  *{
    margin: 0;
    padding: 0;
    border: 0;
  }
  .left{
    width: 50%;
    height: 100%;
    #stars{
    }
    #stars2{
    }
    #stars3{
    }
    div{
      margin-top: 0%;
      p{
        text-align: center;
      }
    }
    div{
      img{
        // margin: 50%;
        margin-left: 70%;
        margin-top: 30%;
        width: 70%;
        transform: rotate(25deg);
        transform-origin: right top;
      }
    }
  }
  .right{
    width: 50%;
    height: 100%;
    .right-card{
      height: 50%;
      width: 100%;
    }
    .right-camera{
      height: 50%;
      width: 100%;
    }
  }
}
</style>