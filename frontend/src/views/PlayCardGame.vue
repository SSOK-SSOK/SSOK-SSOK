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
              @click="moveSelectCardGame"
            ></div>
          </template>
          <span>카드게임 다시 고르기!</span>
        </v-tooltip>
      </nav>
      <p v-if="started">녹음버튼을 눌러 정답을 말해보세요</p>
      <div class="game-contents">
        <!--타이머-->
        <div class="countdown-timer">
          <Timer
            v-if="started"
            :quizIdx="quizIdx"
            :resetTime="resetTime"
            @solvingStatus="is_solved"
          ></Timer>
        </div>
        <!--카드-->
        <div class="game-field">
          <div class="card">
            <div v-if="started">
              <QuizCard
                :currentQuiz="currentQuiz"
                :flipped="flipped"
                @nextCard="nextCard"
                @is_flipped="is_flipped"
              />
            </div>
            <button
              v-else-if="ended"
              class="auth-button mx-auto"
              @click="openModal = true"
            >
              결과 보기
            </button>
            <button v-else class="auth-button mx-auto" @click="getStart">
              게임 시작
            </button>
            <ScoreModal
              v-if="openModal"
              @close="openModal = false"
              :score="score"
            />
          </div>
        </div>
        <!--오디오버튼-->
        <div v-if="started" class="audio-button">
          <div v-if="loading" class="loading-circle">
            <v-progress-circular
              indeterminate
              color="amber"
              :size="80"
            ></v-progress-circular>
          </div>
          <Audio
            :quizIdx="quizIdx"
            @audioResult="onAudioResult"
            @is_flipped="is_flipped"
            @is_loading="is_loading"
          />
        </div>
      </div>
      <div id="alert" v-if="alertDialog">
        <div>
          <v-alert
            color="white"
            right
            outlined
            text
            type="success"
            style="position: fixed; float: right; bottom: 3%; right: 2%"
          >
            {{ alertMessage }}
          </v-alert>
        </div>
      </div>
    </div>
  </v-container>
</template>

<script>
import QuizCard from "@/components/QuizCard.vue";
import Timer from "@/components/Timer.vue";
import Audio from "@/components/Audio.vue";
import ScoreModal from "@/components/ScoreModal.vue";
import { mapState } from "vuex";

export default {
  name: "PlayCardGame",
  components: {
    QuizCard,
    Timer,
    Audio,
    ScoreModal,
  },
  data() {
    return {
      quizIdx: 0,
      started: false,
      ended: false,
      solvingStatus: false,
      flipped: false,
      resetTime: false,
      openModal: false,
      score: 0,
      snackbar: false,
      loading: false,
    };
  },
  computed: {
    ...mapState("CardGameStore", [
      "playingCards",
      "alertMessage",
      "alertDialog",
    ]),
    currentQuiz() {
      return this.sendCurrentQuiz(this.quizIdx);
    },
  },
  watch: {
    // 시간 초과면 카드를 뒤집는다
    solvingStatus(newValue) {
      if (newValue === false) {
        this.flipped = true;
      }
    },
  },
  created() {
    this.solvingStatus = false;
  },
  methods: {
    sendCurrentQuiz(idx) {
      if (idx < this.playingCards.length) {
        return this.playingCards[idx];
      } else {
        this.started = false;
        this.ended = true;
        this.quizIdx = 0;
      }
    },
    is_solved(value) {
      if (value === false) {
        this.solvingStatus = value;
        this.flipped = true;
      }
    },
    is_flipped(newValue) {
      this.resetTime = true;
    },
    nextCard(newValue) {
      if (newValue === true) {
        this.quizIdx += 1;
        this.solvingStatus = true;
        this.flipped = false;
        this.resetTime = false;
      }
    },
    // 게임시작 버튼
    getStart() {
      this.started = true;
    },
    // 뒤로가기 버튼
    moveSelectCardGame() {
      this.$router.push({ name: "SelectCardGame" });
    },
    onAudioResult(result) {
      if (result === true) {
        this.score += 1;
        this.snackbar = true;
      }
    },
    is_loading(value) {
      this.loading = value;
    },
  },
};
</script>

<style lang="scss" scoped>
@import "@/style/star.sass";
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
    z-index: 10;
    width: 100%;
    height: 100%;
    left: 0;
    padding: 1%;
    nav {
      display: flex;
      justify-content: space-between;
      align-items: center;
      width: 100%;
      height: 5%;
      padding: 2.5% 1.5%;
      margin-bottom: 2%;
      background: none;
      color: white;
      font-size: 1rem;
    }
    p {
      text-align: center;
      font-size: 2.2em;
    }
    .game-contents {
      display: flex;
      align-items: center;
      margin-top: 1%;
      // 타이머
      .countdown-timer {
        display: flex;
        justify-content: flex-end;
        width: 30%;
        height: 60vh;
        padding: 1% 0% 1% 3%;
      }
      // 카드
      .game-field {
        width: 40%;
        height: 60vh;
        display: flex;
        justify-content: center;
        align-items: center;
        .card {
          width: 100%;
          height: 65%;
          display: flex;
          justify-content: center;
          align-items: center;
        }
      }
      // 오디오 버튼
      .audio-button {
        display: flex;
        justify-content: start;
        align-items: center;
        width: 30%;
        height: 60vh;
        .loading-circle {
          width: 100%;
          display: flex;
          justify-content: center;
        }
      }
    }
  }
}
</style>
