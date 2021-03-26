<template>
  <v-container pa-0 fluid>
    <NavBar />
    <v-row class="mt-10">
      <v-col xl="6" lg="6" md="6" sm="12" xs="12">
        <div class="border">
          <vue-web-cam
            ref="webcam"
            :device-id="deviceId"
            width="100%"
            @started="onStarted"
            @stopped="onStopped"
            @error="onError"
            @cameras="onCameras"
            @camera-change="onCameraChange"
          />
        </div>
        <v-row justify="center" class="my-6">
          <h2 class="white--text">Current Camera</h2>
        </v-row>

        <v-row justify="center">
          <v-btn fab large class="mx-5" @click="onCapture"
            ><v-icon>mdi-camera-iris</v-icon></v-btn
          >
          <v-btn fab large class="mx-5" @click="onStop"
            ><v-icon>mdi-camera-off</v-icon></v-btn
          >
          <v-btn fab large class="mx-5" @click="onStart"
            ><v-icon>mdi-camera</v-icon></v-btn
          >
          <!-- </v-col> -->
        </v-row>
        <!-- <v-row justify="center">
          <select v-model="camera" color="white">
            <option>-- Select Device --</option>
            <option
              v-for="device in devices"
              :key="device.deviceId"
              :value="device.deviceId"
            >
              <p class="white--text">{{ device.label }}</p>
            </option>
          </select>
        </v-row> -->
      </v-col>
      <v-col xl="6" lg="6" md="6" sm="12" xs="12">
        <figure class="figure">
          <img :src="img" class="img-responsive" />
          <!-- <h2 class="white--text">Captured Image</h2> -->
        </figure>
      </v-col>
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
