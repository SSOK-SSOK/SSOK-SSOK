<template>
  <v-container pa-0 fluid fill-height bg-img>
    <v-toolbar 
      style="background-color: rgba( 255, 255, 255, 0 );"
      :elevation="0"
      height="100%"
      width="100%"
    >
      <v-avatar size="75" class="mr-2">
        <img src="@/assets/images/welcome-icon.png" alt="welcome-icon">
      </v-avatar>
      <h1 class="font-color display-2">SSOK SSOK</h1>
      <v-spacer></v-spacer>
      <v-btn class="mr-2" icon @click="moveToMainPage"><v-icon color="white" size="75">mdi-google-controller</v-icon></v-btn>
    </v-toolbar>

    <v-row fluid fill-height align="center" class="mx-7" style="min-height: 85vh;">
      <div style="width: auto;">
        <h1 class="font-color display-3">
          다양한 언어를
          <br>
          재밌게 배워보아요!
        </h1>
        <v-btn class="google" @click="google">Google</v-btn>
        <!-- <v-btn class="naver" @click.prevent="naver">Naver</v-btn> -->
        <v-btn class="logout" @click="logout">Logout</v-btn>
      </div>
    </v-row>
  </v-container>
  
</template>

<script>
import axios from 'axios';
import { mapActions } from 'vuex';
import { GOOGLE_AUTH_URL } from '@/config/index.js';

export default {
  name: "WelcomePage",
  methods: {
    ... mapActions(['logout']),
    moveToMainPage: function () {
      this.$router.push({ name: "MainPage" });
    },
    google() {
      axios.get(`${GOOGLE_AUTH_URL}`)
      .then((res) => {
        console.log(res)
        function getUrlParameter(name) {
          name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
          var regex = new RegExp("[\\?&]" + name + "=([^&#]*)");
          var results = regex.exec(window.location.search);

          return results === null
            ? ""
            : decodeURIComponent(results[1].replace(/\+/g, " "));
        }

        const token = getUrlParameter("token");
        console.log(token);

        if (token) {
          localStorage.setItem(ACCESS_TOKEN, token);
          this.$router.push({ name: "MainPage" });
        }
      })
      .catch((err) => {
        console.log(err)
      })
    }
    
  },
};
</script>

<style>

</style>
