<template>
  <v-container fluid>
    <NavBar />
    <h1 class="font-color text-center mt-10">{{ question }}을 보여주세요😉</h1>
    <v-row class="mt-5">
      <div class="col-md-6 col-xs-12">
        <vue-web-cam
          height="60%"
          width="100%"
          ref="webcam"
          :device-id="deviceId"
          @started="onStarted"
          @stopped="onStopped"
          @error="onError"
          @cameras="onCameras"
          @camera-change="onCameraChange"
          style="position: relative; z-index: 100"
        />
        <div class="mt-5 d-flex justify-between">
          <div class="mx-auto">
            <v-btn fab large class="mx-5" @click="onCapture"
              ><v-icon>mdi-camera-iris</v-icon></v-btn
            >
            <v-btn fab large class="mx-5" @click="onStop"
              ><v-icon>mdi-camera-off</v-icon></v-btn
            >
            <v-btn fab large class="mx-5" @click="onStart"
              ><v-icon>mdi-camera</v-icon></v-btn
            >
          </div>
        </div>
      </div>

      <div class="col-md-6 col-xs-12">
        <figure class="mx-auto">
          <img
            :src="img"
            width="73%"
            class="d-flex mx-auto"
            style="position: relative; z-index: 100"
          />
        </figure>
        <span v-if="img">
          <h3 class="font-color text-center mt-3">
            잠시 기다려주세요! 정답여부를 알려줄게요
          </h3>
        </span>
      </div>
    </v-row>
  </v-container>
</template>

<script>
import { WebCam } from "vue-web-cam";
import NavBar from "@/components/NavBar.vue";
import axios from "axios";

export default {
  name: "SmartCamera2",
  components: {
    "vue-web-cam": WebCam,
    NavBar,
  },
  data: () => ({
    img: null,
    camera: null,
    deviceId: null,
    devices: [],
    question: "Cup",
  }),
  computed: {
    device: function () {
      return this.devices.find((n) => n.deviceId === this.deviceId);
    },
  },
  watch: {
    camera: function (id) {
      this.deviceId = id;
    },
    devices: function () {
      // Once we have a list select the first one
      const [first, ...tail] = this.devices;
      if (first) {
        this.camera = first.deviceId;
        this.deviceId = first.deviceId;
      }
    },
  },
  methods: {
    onCapture() {
      this.img = this.$refs.webcam.capture();
      console.log(this.img);
      axios
        .post("http://127.0.0.1:8000/ai/detection/", { image: this.img })
        .then((res) => {
          console.log(res);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    onStarted(stream) {
      console.log("On Started Event", stream);
    },
    onStopped(stream) {
      console.log("On Stopped Event", stream);
    },
    onStop() {
      this.$refs.webcam.stop();
    },
    onStart() {
      this.$refs.webcam.start();
    },
    onError(error) {
      console.log("On Error Event", error);
    },
    onCameras(cameras) {
      this.devices = cameras;
      console.log("On Cameras Event", cameras);
    },
    onCameraChange(deviceId) {
      this.deviceId = deviceId;
      this.camera = deviceId;
      console.log("On Camera Change Event", deviceId);
    },
  },
};
</script>

<style></style>
