<template>
  <v-container fluid fill-height>
    <div class="background">
      <div id="stars"></div>
      <div id="stars2"></div>
      <div id="stars3"></div>
    </div>
    <div class="contents">
      <nav>
        <div @click="moveToSelectPage" class="glow"></div>
      </nav>
      <h1 v-if="started" class="text-center">
        녹음버튼을 눌러 정답을 말해주세요😉
      </h1>
      <div class="game-contents">
        <!--타이머-->
        <div class="countdown-timer">
          <Timer
            v-if="started"
            :quizIdx="quizIdx"
            @solvingStatus="is_solved"
          ></Timer>
        </div>
        <!--카드-->
        <div class="game-field">
          <div class="card">
            <div v-if="started">
              <QuizCard :currentQuiz="currentQuiz" />
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
          <v-btn fab dark large color="white">
            <v-icon color="red"> mdi-record </v-icon>
          </v-btn>
        </div>
      </div>
    </div>
  </v-container>
</template>

<script>
import QuizCard from "@/components/QuizCard.vue";
import Timer from "@/components/Timer.vue";
import { mapState } from "vuex";

export default {
  name: "PlayCardGame",
  components: {
    QuizCard,
    Timer,
  },
  data() {
    return {
      categoryName: "",
      categorySub: "",
      quizIdx: 0,

      //for game
      started: false,
      ended: false,
      solvingStatus: false,
    };
  },
  computed: {
    ...mapState("CardGameStore", ["playingCards"]),
    currentQuiz() {
      // 0초가 남으면 watch에서 quizIdx의 값을 증가시켜준다. 변하는 quizIdx의 값에 따라 다른 퀴즈가 QuizCard에 넘어가게 하자.
      return this.sendCurrentQuiz(this.quizIdx);
    },
  },
  watch: {
    solvingStatus(newValue) {
      // 시간 초과일 때
      if (newValue === false) {
        this.quizIdx += 1;
        this.solvingStatus = true;
      }
    },
  },
  created() {
    // this.getParams();
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
    moveToSelectPage: function () {
      this.$router.push({ name: "SelectCardGame" });
    },
    is_solved(value) {
      this.solvingStatus = value;
    },
    //forButton
    getStart: function () {
      this.started = true;
      this.solvingStatus = true;
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
    position: relative;
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
      margin-bottom: 3%;
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
        width: 20%;
        padding: 1% 3%;
        // 카드
      }
      .game-field {
        width: 60%;
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
        width: 20%;
      }
    }
  }
}
</style>
