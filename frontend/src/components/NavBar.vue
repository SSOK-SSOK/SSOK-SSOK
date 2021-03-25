<template>
  <div class="mt-3 mb-7">
    <v-app-bar
      color="#040431"
      :elevation="0"
      max-height="80"
    >
      <v-btn icon class="ml-2" @click="moveToMP">
        <v-avatar tile size="70" >
          <img src="@/assets/images/out-icon.png" alt="out-icon">
        </v-avatar>
      </v-btn>
      
      <v-spacer></v-spacer>
      <v-avatar size="70" class="mr-2">
        <img src="@/assets/images/welcome-icon.png" alt="welcome-icon">
      </v-avatar>
      <p class="header">Animals</p>
    </v-app-bar>
  </div>
</template>

<script>
export default {
  name: 'NavBar',
  methods: {
    moveToMP: function () {
      this.$router.push({ name: "MainPage" });
    },
  }
}
</script>

<style>

</style>