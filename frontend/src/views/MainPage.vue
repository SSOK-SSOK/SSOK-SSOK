<template>
  <v-container fluid fill-height pa-0>
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
        <v-img style="width: 75%" src="@/assets/images/char1.png"></v-img>
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
