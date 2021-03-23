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
        <v-btn>구글에서 준 디자인</v-btn>
      </div>
    </v-row>
  </v-container>
  
</template>

<script>
export default {
  name: "WelcomePage",
  methods: {
    moveToMainPage: function () {
      this.$router.push({ name: "MainPage" });
    },
  },
};
</script>

<style>

</style>
