<template>
  <v-app>
    <v-main>
      <router-view />
    </v-main>
  </v-app>
</template>

<script>
export default {
  name: "App",
  data() {
    return {
    }
  },
};
</script>

<style lang="scss" scoped>
@import "@/style/base.scss";
</style>
