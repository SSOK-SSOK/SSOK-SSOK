<template>
  <v-container fluid fill-height>
    <div class="background">
      <div id="stars"></div>
      <div id="stars2"></div>
      <div id="stars3"></div>
    </div>
    <div class="contents">
      <nav>
        <v-tooltip bottom color="deep-purple accent-3">
          <template v-slot:activator="{ on, attrs }">
            <div
              v-bind="attrs"
              v-on="on"
              class="glow"
              @click="moveMainPage"
            ></div>
          </template>
          <span>게임 선택 GO GO!</span>
        </v-tooltip>
      </nav>
      <div class="head-section">
        <span v-if="is_start">
          <h1>{{ this.question }}을 보여주세요😉</h1>
        </span>
        <span v-else>
          <button class="auth-button" @click="initialize">문제 보기</button>
        </span>
      </div>
      <v-row class="body-section">
        <div class="camera-section col-md-6 col-xs-12">
          <vue-web-cam
            ref="webcam"
            :device-id="deviceId"
            @started="onStarted"
            @stopped="onStopped"
            @error="onError"
            @cameras="onCameras"
          />
          <div class="camera-btns">
            <v-tooltip bottom color="deep-purple accent-3">
              <template v-slot:activator="{ on, attrs }">
                <v-btn v-bind="attrs" v-on="on" fab large @click="onCapture">
                  <v-icon>mdi-camera-iris</v-icon>
                </v-btn>
              </template>
              <span>제출하기!</span>
            </v-tooltip>
            <v-tooltip bottom color="deep-purple accent-3">
              <template v-slot:activator="{ on, attrs }">
                <v-btn v-bind="attrs" v-on="on" fab large @click="onStop">
                  <v-icon>mdi-camera-off</v-icon>
                </v-btn>
              </template>
              <span>카메라 끄기</span>
            </v-tooltip>
            <v-tooltip bottom color="deep-purple accent-3">
              <template v-slot:activator="{ on, attrs }">
                <v-btn v-bind="attrs" v-on="on" fab large @click="onStart">
                  <v-icon>mdi-camera</v-icon>
                </v-btn>
              </template>
              <span>카메라 켜기!</span>
            </v-tooltip>
          </div>
        </div>
        <v-card class="answer-section col-md-6 col-xs-12" elevation="0">
          <div v-if="loading">
            <div>
              <h1>정답 확인중입니다</h1>
              <v-progress-linear
                :active="loading"
                :indeterminate="loading"
                color="#FFEE58"
              ></v-progress-linear>
            </div>
          </div>
          <div v-if="is_done">
            <div v-if="is_correct">
              <h1>정답입니다!</h1>
              <h3>정확도 : {{ this.score }}%</h3>
            </div>
            <div v-else>
              <h1>틀렸어요😥</h1>
              <h3>가져온 물건 : {{ this.category }}</h3>
            </div>
          </div>
        </v-card>
      </v-row>
    </div>
  </v-container>
</template>

<script>
import { WebCam } from "vue-web-cam";
import { mapGetters } from "vuex";
import axios from "axios";
import "@/style/star.sass";

const SmartCameraStore = "SmartCameraStore";

export default {
  name: "SmartCameraGame",
  components: {
    "vue-web-cam": WebCam,
  },
  data() {
    return {
      camera: null,
      deviceId: null,
      devices: [],
      loading: false,
      questions: ["cup", "book", "chair"],
      img: null,
      is_start: false,
    };
  },
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
    question: function () {
      var randNum = Math.random() * 3;
      var randInt = parseInt(randNum);
      return this.questions[randInt];
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
    // is_start: function () {},
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
    onStart() {
      this.$refs.webcam.start();
    },
    onStop() {
      this.$refs.webcam.stop();
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
    moveMainPage() {
      this.$router.push({ name: "MainPage" });
    },
  },
};
</script>

<style lang="scss" scoped>
@import "@/style/light-button.scss";
@import "@/style/auth-button.scss";
.container {
  padding: 1%;
  .background {
    position: absolute;
    z-index: -1;
    width: 100%;
    height: 100%;
  }
  .contents {
    position: absolute;
    left: 0;
    width: 100%;
    height: 100%;
    padding: 1%;
    nav {
      display: flex;
      justify-content: space-between;
      align-items: center;
      height: 5%;
      width: 100%;
      padding: 2.5% 1.5%;
      margin-bottom: 1%;
      background: none;
      color: white;
      font-size: 1rem;
      div {
        color: white;
      }
    }
    .head-section {
      display: flex;
      justify-content: center;
      align-items: center;
      width: 100%;
      height: 8%;
      span {
        h1 {
        }
      }
    }
    .body-section {
      width: 100%;
      height: 80%;
      padding: 1%;
      margin-top: 1%;
      .camera-section {
        height: 100%;
        video {
          position: relative;
          z-index: 100;
          width: 100%;
          height: 75%;
        }
        .camera-btns {
          display: flex;
          justify-content: center;
          align-items: center;
          width: 65%;
          height: 15%;
          margin: 2.5% auto;
          * {
            margin: 1.5rem;
          }
        }
      }
      .answer-section {
        height: 100%;
        background: none;
        div {
          width: 100%;
          height: 100%;
          div {
            width: 100%;
            height: 75%;
            h1 {
            }
            .v-progress-linear {
              width: 100%;
              height: 10;
              margin-top: 2rem;
            }
          }
          img {
            position: relative;
            z-index: 100;
            width: 80%;
            height: 75%;
            object-fit: contain;
            margin: 0 10%;
          }
          div {
            width: 65%;
            height: 20%;
            padding: 1% 0;
            margin: 0 auto;
            * {
              text-align: center;
              color: white;
            }
          }
        }
        h1 {
          text-align: center;
          color: white;
        }
      }
    }
  }
}
</style>
