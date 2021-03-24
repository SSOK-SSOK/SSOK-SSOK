<template>
  <div>
    <h1 class="font-color">MainPage</h1>
    <v-btn @click="moveToSC">Smart Camera</v-btn>
    <v-btn @click="back">back</v-btn>
  </div>
</template>

<script>

export default {
  name: "MainPage",
  methods: {
    moveToSC: function () {
      this.$router.push({ name: "SmartCamera" });
    },
    back: function () {
      this.$router.push({ name: "WelcomePage" });
    },
  },
};
</script>
