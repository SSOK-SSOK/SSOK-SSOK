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
      <div v-if="game_started" class="camera-game-field">
        <!--문제 영역-->
        <div class="head-section">
          <span v-if="is_start">
            <h1>{{ this.question }}을 보여주세요😉</h1>
          </span>
          <span v-else>
            <button class="gold-button" @click="initialize">문제 보기</button>
          </span>
        </div>
        <!--카메라 & 정답영역-->
        <v-row class="body-section">
          <!--카메라-->
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
          <!--정답-->
          <v-card class="answer-section col-md-6 col-xs-12" elevation="0">
            <div v-if="loading">
              <div class="progress">
                <h1>정답 확인중입니다</h1>
                <v-progress-linear
                  :active="loading"
                  :indeterminate="loading"
                  color="#FFEE58"
                ></v-progress-linear>
              </div>
            </div>
            <div v-if="is_done">
              <div v-if="is_correct" class="answer">
                <img src="@/assets/images/mascot-success.png" />
                <h1 class="my-1">정답입니다!</h1>
                <h2 class="my-1">정확도 : {{ this.score }}%</h2>
                <div>
                  <button class="gold-button mx-auto" @click="regame">
                    다시하기
                  </button>
                </div>
              </div>
              <div v-else class="answer">
                <img src="@/assets/images/mascot-fail.png" />
                <h1 class="my-1">틀렸어요😥</h1>
                <h2 class="my-1">가져온 물건 : {{ this.category }}</h2>
                <div>
                  <button class="gold-button mx-auto" @click="regame">
                    다시하기
                  </button>
                </div>
              </div>
            </div>
          </v-card>
        </v-row>
      </div>
      <div v-else class="start-button">
        <Stepper
          class="d-none d-md-block"
          v-if="!languageModal"
          @stepperDone="stepperDone"
        />
        <button
          v-if="!languageModal"
          class="gold-button mx-auto d-block d-md-none"
          @click="getGameStart"
        >
          게임하기
        </button>
      </div>
      <LanguageModal
        v-if="languageModal"
        @close="moveMainPage"
        @language="getMessage"
      />
    </div>
  </v-container>
</template>

<script>
import { WebCam } from "vue-web-cam";
import { mapGetters } from "vuex";
import LanguageModal from "@/components/LanguageModal.vue";
import Stepper from "@/components/Stepper.vue";
import axios from "axios";
import "@/style/star.sass";

const SmartCameraStore = "SmartCameraStore";

export default {
  name: "SmartCameraGame",
  components: {
    "vue-web-cam": WebCam,
    LanguageModal,
    Stepper,
  },
  data() {
    return {
      camera: null,
      deviceId: null,
      devices: [],
      loading: false,
      questions: [
        ["컵", "책", "의자"],
        ["cup", "book", "chair"],
        ["杯子", "書", "椅子"],
        ["カップ", "本", "椅子"],
        ["cốc", "sách", "cái ghế"],
        ["Coupe", "livre", "chaise"],
        ["taza", "libro", "silla"],
      ],
      img: null,
      is_start: false,
      game_started: false,
      languageModal: false,
      selected_language: 0,
      stepper: false,
      question: "",
      answer: "",
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
    // randInt: function () {
    //   var randNum = Math.random() * 3;
    //   var randNumInt = parseInt(randNum);
    //   return randNumInt;
    // },
    // question: function () {
    //   return this.questions[this.selected_language][this.randInt];
    // },
    // answer: function () {
    //   return this.questions[1][this.randInt];
    // },
  },
  watch: {
    camera: function (id) {
      this.deviceId = id;
    },
    devices: function () {
      const [first, ...tail] = this.devices;
      if (first) {
        this.camera = first.deviceId;
        this.deviceId = first.deviceId;
      }
    },
  },
  methods: {
    onCapture() {
      this.loading = true;
      var img = this.$refs.webcam.capture();
      axios
        .post("https://ssokssok.site/ai/smartcamera/detection/", {
          image: img,
          question: this.answer,
        })
        .then((res) => {
          var data = res.data;
          this.$store.dispatch("SmartCameraStore/setInfo", data);
          this.loading = false;
        })
        .catch((err) => {
          console.log(err);
        });
    },
    onStarted() {},
    onStopped() {},
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
    },
    onCameraChange(deviceId) {
      this.deviceId = deviceId;
      this.camera = deviceId;
    },
    initialize() {
      this.$store.dispatch("SmartCameraStore/initializeInfo");
      this.is_start = true;
      // 랜덤 문제
      var randNum = Math.random() * 3;
      var randInt = parseInt(randNum);
      this.question = this.questions[this.selected_language][randInt];
      this.answer = this.questions[1][randInt];
    },
    moveMainPage() {
      this.$router.push({ name: "MainPage" });
    },
    regame() {
      this.initialize();
      this.is_start = false;
    },
    getGameStart() {
      this.languageModal = true;
    },
    getMessage(language) {
      this.languageModal = false;
      this.game_started = true;
      this.selected_language = language;
    },
    stepperDone(value) {
      if (value == true) {
        this.languageModal = true;
      }
    },
    moveMainPage() {
      this.$router.push({ name: "MainPage" });
    },
  },
  created() {
    this.initialize();
    this.is_start = false;
  },
};
</script>

<style lang="scss" scoped>
@import "@/style/light-button.scss";
@import "@/style/gold-button.scss";

*p {
  text-align: center;
  font-size: 2.2em;
}
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
    .camera-game-field {
      width: 100%;
      height: 87%;
      .head-section {
        display: flex;
        justify-content: center;
        align-items: center;
        width: 100%;
        height: 15%;
      }
      .body-section {
        width: 100%;
        height: 85%;
        padding: 1%;
        margin: 0;
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
            .progress {
              width: 100%;
              height: 75%;
              .v-progress-linear {
                width: 70%;
                height: 10;
                margin-top: 2rem;
              }
            }
            .answer {
              width: 100%;
              height: 75%;
              img {
                position: relative;
                z-index: 100;
                width: 80%;
                height: 75%;
                object-fit: contain;
                margin: 0 10%;
              }
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
    .start-button {
      width: 100%;
      height: 87%;
      display: flex;
      justify-content: center;
      align-items: center;
      padding-bottom: 9%;
    }
  }
}
</style>
