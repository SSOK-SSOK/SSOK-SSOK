<template>
  <div class="mt-5">
    <v-app-bar style="background: transparent" :elevation="0" max-height="80">
      <v-btn icon class="ml-5" @click="moveToMP">
        <v-avatar tile size="4rem">
          <img src="@/assets/images/out-icon.png" alt="out-icon" />
        </v-avatar>
      </v-btn>

      <v-spacer></v-spacer>
      <div class="d-flex align-center">
        <v-avatar size="4rem" class="mr-2 d-md-flex d-none">
          <img src="@/assets/images/welcome-icon.png" alt="welcome-icon" />
        </v-avatar>
        <h1 class="mr-5 mb-0" style="color: white">Animals</h1>
      </div>
    </v-app-bar>
  </div>
</template>

<script>
export default {
  name: "NavBar",
  methods: {
    moveToMP: function () {
      this.$router.push({ name: "MainPage" });
    },
  },
};
</script>

<style></style>
