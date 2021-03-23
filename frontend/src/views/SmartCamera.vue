<template>
  <div>
    <h1 class="font-color">Smart Camera</h1>
    <button class="font-color" @click="back">back</button>
    <div>
      <dashboard :uppy="uppy" :plugins="['Webcam']" />
    </div>
  </div>
</template>

<script>
import { Dashboard } from "@uppy/vue";
import Uppy from "@uppy/core";
import Webcam from "@uppy/webcam";

import "@uppy/core/dist/style.css";
import "@uppy/dashboard/dist/style.css";

export default {
  name: "SmartCamera",
  components: {
    Dashboard,
  },
  computed: {
    uppy: () => new Uppy().use(Webcam, {}),
  },
  beforeDestroy() {
    this.uppy.close();
  },
  methods: {
    back: function () {
      this.$router.push({ name: "MainPage" });
    },
  },
};
</script>

<style></style>
