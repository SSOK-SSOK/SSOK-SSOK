<template>
  <v-container fluid>
    <div id="stars"></div>
    <div id="stars2"></div>
    <div id="stars3"></div>
    <NavBar />
    <div>
      <span v-if="is_start">
        <h1 class="font-color text-center mt-5">
          {{ question }}을 보여주세요😉
        </h1>
      </span>
      <span v-else>
        <v-btn
          rounded
          large
          color="warning"
          class="d-flex mx-auto"
          @click="initialize"
        >
          문제 보기
        </v-btn>
      </span>
    </div>
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

      <v-card
        class="col-md-6 col-xs-12 mt-3 pa-0 mx-auto"
        color="rgba(255, 255, 255, 0)"
        max-width="35vw"
        max-height="60vh"
        elevation="0"
      >
        <span v-if="is_done">
          <img
            :src="detected_img"
            width="100%"
            height="90%"
            class="d-flex mx-auto"
            style="position: relative; z-index: 100"
          />
          <span v-if="is_correct">
            <h1 class="text-center mt-3" style="color: white">정답입니다!</h1>
            <h3 class="text-center mt-3" style="color: white">
              정확도 : {{ this.score }}%
            </h3>
          </span>
          <span v-else>
            <h1 class="text-center mt-3" style="color: white">틀렸어요😥</h1>
            <h3 class="text-center mt-3" style="color: white">
              가져온 물건 : {{ this.category }}
            </h3>
          </span>
        </span>

        <!--로딩중-->
        <h1 v-if="loading" class="text-center" style="color: white">
          정답 확인중입니다
        </h1>
        <v-progress-linear
          class="mt-5"
          :active="loading"
          :indeterminate="loading"
          color="#FFEE58"
          height="10"
        ></v-progress-linear>
      </v-card>
    </v-row>
  </v-container>
</template>

<script>
import { WebCam } from "vue-web-cam";
import { mapGetters } from "vuex";
import NavBar from "@/components/NavBar.vue";
import axios from "axios";
import "@/style/star.sass";

const SmartCameraStore = "SmartCameraStore";

export default {
  name: "SmartCamera",
  components: {
    "vue-web-cam": WebCam,
    NavBar,
  },
  data: () => ({
    camera: null,
    deviceId: null,
    devices: [],
    loading: false,
    question: "cup",
    img: null,
    detected_img: require("../../../AI/images/detected_image.jpg"),
    is_start: false,
  }),
  computed: {
    device: function () {
      return this.devices.find((n) => n.deviceId === this.deviceId);
    },
    ...mapGetters(SmartCameraStore, {
      score: "getScore",
    }),
    ...mapGetters(SmartCameraStore, {
      category: "getCategory",
    }),
    ...mapGetters(SmartCameraStore, {
      is_correct: "getIsCorrect",
    }),
    ...mapGetters(SmartCameraStore, {
      is_done: "getIsDone",
    }),
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
    is_start: function () {},
  },
  methods: {
    onCapture() {
      this.loading = true;
      var img = this.$refs.webcam.capture();
      axios
        .post("https://j4a201.p.ssafy.io/ai/smartcamera/detection/", {
          image: img,
          question: this.question,
        })
        .then((res) => {
          console.log(res.data);
          var data = res.data;
          this.$store.dispatch("SmartCameraStore/setInfo", data);
          this.loading = false;
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
    initialize() {
      this.$store.dispatch("SmartCameraStore/initializeInfo");
      this.is_start = true;
    },
    mounted() {
      console.log("다시 컴백!");
      console.log(this.score);
      console.log(this.category);
      console.log(this.is_correct);
      console.log(this.is_done);
    },
  },
};
</script>

<style lang="scss" scoped></style>
