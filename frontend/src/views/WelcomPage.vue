<template>
  <div>
    <v-toolbar 
      color="#040431"
      :elevation="0"
      height="100%"
    >
      <v-avatar size="75" class="mr-2">
        <img src="@/assets/images/welcome-icon.png" alt="welcome-icon">
      </v-avatar>
      <h1 class="font-color">SSOK SSOK</h1>
      <v-spacer></v-spacer>
      <v-btn class="mr-2" icon @click="moveToMainPage"><v-icon color="white" size="75">mdi-google-controller</v-icon></v-btn>
    </v-toolbar>

    <div style="height: 100%;">
      dd
    </div>

    <!-- <v-card
      class="d-flex align-center"
      color="#040431"
      :elevation="0"
      max-width="70%"
    >
      <v-list-item-content>
        <v-list-item-title class="headline mb-1 font-color">
          다양한 언어를
          <br>
          재밌게 배워보아요!
        </v-list-item-title>
      </v-list-item-content>
      <v-card-actions>
        <v-btn
          outlined
          rounded
          text
          color="white"
        >
          Button
        </v-btn>
      </v-card-actions>
    </v-card> -->
  </div>
  
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
