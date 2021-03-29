<template>
  <v-app>
    <v-main class="bg">
      <div id="stars"></div>
      <div id="stars2"></div>
      <div id="stars3"></div>
      <router-view />
    </v-main>
  </v-app>
</template>

<script>
import "@/style/base.scss";
import "@/style/star.sass";
export default {
  name: "App",
  data: () => ({
    //
  }),
};
</script>

<style>
/* @font-face {
    font-family: 'CookieRunOTF-Bold';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_twelve@1.0/CookieRunOTF-Bold00.woff') format('woff');
    font-weight: normal;
    font-style: normal;
} */
</style>
