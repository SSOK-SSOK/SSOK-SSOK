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
            <div v-bind="attrs" v-on="on"
             class="glow" @click="moveSelectCardGame"></div>
          </template>
          <span>카드게임 다시 고르기!</span>
        </v-tooltip>
      </nav>
      <h1 v-if="started" class="text-center">
        녹음버튼을 눌러 정답을 말해보세요
      </h1>
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
                :fliped="fliped"
                @nextCard="nextCard"
                @is_fliped="is_fliped"
              />
            </div>
            <button v-else-if="ended" class="auth-button mx-auto">
              결과 보기
            </button>
            <button v-else class="auth-button mx-auto" @click="getStart">
              게임 시작
            </button>
          </div>
        </div>
        <!--오디오버튼-->
        <div v-if="started" class="audio-button">
          <Audio />
        </div>
      </div>
    </div>
  </v-container>
</template>

<script>
import QuizCard from "@/components/QuizCard.vue";
import Timer from "@/components/Timer.vue";
import Audio from "@/components/Audio.vue";
import { mapState } from "vuex";

export default {
  name: "PlayCardGame",
  components: {
    QuizCard,
    Timer,
    Audio,
  },
  data() {
    return {
      categoryName: "",
      categorySub: "",
      quizIdx: 0,
      started: false,
      ended: false,
      solvingStatus: false,
      fliped: false,
      resetTime: false,
    };
  },
  computed: {
    ...mapState("CardGameStore", ["playingCards"]),
    currentQuiz() {
      return this.sendCurrentQuiz(this.quizIdx);
    },
  },
  watch: {
    // 시간 초과면 카드를 뒤집는다
    solvingStatus(newValue) {
      if (newValue === false) {
        console.log("시간초과");
        this.fliped = true;
      }
    },
  },
  created() {
    this.getParams();
    this.solvingStatus = false;
  },
  methods: {
    getParams: function () {
      const category = this.$route.params.category;
      this.categoryName = category.name;
      this.categorySub = category.sub;
    },
    sendCurrentQuiz(idx) {
      if (idx < 15) {
        return this.playingCards[idx];
      } else {
        this.started = false;
        this.ended = true;
        this.quizIdx = 0;
      }
    },
    is_solved(value) {
      if (value === false) {
        console.log("시간초과!");
        this.solvingStatus = value;
        this.fliped = true;
      }
    },
    is_fliped(newValue) {
      console.log("정답확인");
      this.resetTime = true;
    },
    nextCard(newValue) {
      if (newValue === true) {
        this.quizIdx += 1;
        this.solvingStatus = true;
        this.fliped = false;
        this.resetTime = false;
      }
    },
    // 게임시작 버튼
    getStart() {
      this.started = true;
    },
    // 뒤로가기 버튼
    moveSelectCardGame: function () {
      this.$router.push({ name: "SelectCardGame" });
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
    width: 100%;
    height: 100%;
    z-index: 10;
    left: 0;
    padding: 1%;
    nav {
      display: flex;
      justify-content: space-between;
      align-items: center;
      padding: 2.5% 1.5%;
      margin-bottom: 2%;
      height: 5%;
      width: 100%;
      color: white;
      background: none;
      font-size: 1rem;
    }
    .game-contents {
      display: flex;
      align-items: center;
      margin-top: 1%;
      // 타이머
      .countdown-timer {
        width: 30%;
        height: 60vh;
        padding: 1% 0% 1% 3%;
        display: flex;
        justify-content: flex-end;
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
        width: 30%;
        height: 60vh;
        display: flex;
        justify-content: start;
        align-items: center;
      }
    }
  }
}
</style>
