<template>
  <v-container fluid fill-height>
    <div class="background">
      <div id="stars"></div>
      <div id="stars2"></div>
      <div id="stars3"></div>
    </div>
    <div class="contents">
      <nav>
        <div>
          <div class="glow" @click="moveToSelectPage"></div>
        </div>
      </nav>
      <div class="game-field">
        <QuizCard :currentQuiz="currentQuiz"/>
      </div>
      <div class="countdown-timer">
        <div class="base-timer">
          <svg class="base-timer__svg" viewBox="0 0 100 100" xmlns="http://www.w3.org/2000/svg">
            <g class="base-timer__circle">
              <circle class="base-timer__path-elapsed" cx="50" cy="50" r="45"></circle>
              <path
                :stroke-dasharray="circleDasharray"
                class="base-timer__path-remaining"
                :class="remainingPathColor"
                d="
                  M 50, 50
                  m -45, 0
                  a 45,45 0 1,0 90,0
                  a 45,45 0 1,0 -90,0
                "
              ></path>
            </g>
          </svg>
          <span class="base-timer__label">{{ formattedTimeLeft }}</span>
        </div>
      </div>
    </div>
  </v-container>
</template>

<script>
import QuizCard from '@/components/QuizCard.vue';
import { mapState } from 'vuex';

// for Timer
const FULL_DASH_ARRAY = 283;
const WARNING_THRESHOLD = 10;
const ALERT_THRESHOLD = 5;

const COLOR_CODES = {
  info: {
    color: "green"
  },
  warning: {
    color: "orange",
    threshold: WARNING_THRESHOLD
  },
  alert: {
    color: "red",
    threshold: ALERT_THRESHOLD
  }
};

const TIME_LIMIT = 15;


export default {
  name: 'PlayGame',
  components: {
    QuizCard,
  },
  data() {
    return {
      categoryName: '',
      categorySub: '',
      quizIdx: 0,

      // for Timer
      timePassed: 0,
      timerInterval: null,
    };
  },
  computed: {
    ...mapState('CardGameStore', ['playingCards']),
    currentQuiz () {
      // 0초가 남으면 watch에서 quizIdx의 값을 증가시켜준다. 변하는 quizIdx의 값에 따라 다른 퀴즈가 QuizCard에 넘어가게 하자.
      return this.sendCurrentQuiz(this.quizIdx);
    },
    // for Timer
    circleDasharray() {
      return `${(this.timeFraction * FULL_DASH_ARRAY).toFixed(0)} 283`;
    },
    formattedTimeLeft() {
      const timeLeft = this.timeLeft;
      let seconds = timeLeft % 60;
      if (seconds < 10) {
        seconds = `0${seconds}`;
      } 
      return `${seconds}`;
    },
    timeLeft() {
      return TIME_LIMIT - this.timePassed;
    },
    timeFraction() {
      const rawTimeFraction = this.timeLeft / TIME_LIMIT;
      return rawTimeFraction - (1 / TIME_LIMIT) * (1 - rawTimeFraction);
    },
    remainingPathColor() {
      const { alert, warning, info } = COLOR_CODES;
      if (this.timeLeft <= alert.threshold) {
        return alert.color;
      } else if (this.timeLeft <= warning.threshold) {
        return warning.color;
      } else {
        return info.color;
      }
    }    
  },
  watch: {
    // for Timer
    timeLeft(newValue) {
      if (newValue === 0) {
        this.onTimesUp();
        this.quizIdx += 1
      }
    },
  },
  created() {
    this.getParams();
  },
  mounted() {
    // for Timer
    this.startTimer();
  },
  methods: {
    getParams: function () {
      const card = this.$route.params.card;
      this.categoryName = card.name;
      this.categorySub = card.sub;
    },
    sendCurrentQuiz(idx) {
      if (idx < 15) {
        return this.playingCards[idx]
      } 
    },
    moveToSelectPage: function () {
      this.$router.push({name: "CardGame"})
    },
    // for Timer
    onTimesUp() {
      // clearInterval은 setInterval로 인해 반복하고 있는 것을 멈추게 한다.
      clearInterval(this.timerInterval);
      // 다음 카드를 보여주자.
      this.quizIdx += 1
    },
    startTimer() {
      // 1초마다 timePassed에 1을 더해준다.
      this.timerInterval = setInterval(() => (this.timePassed += 1), 1000);
    },
  },
}
</script>

<style lang="scss" scoped>
@import "@/style/star.sass";
@import "@/style/light-button.scss";

.container{
  padding: 1%;
  .background{
    position: relative;
    z-index: -1;
    width: 100%;
    height: 100%;
  }
  .contents{
    position: absolute;
    width: 100%;
    height: 100%;
    left: 0;
    padding: 1%;
    nav {
      display: flex;
      justify-content: space-between;
      align-items: center;
      padding: 2.5% 1.5%;
      margin-bottom: 3%;
      height:5%;
      width: 100%;
      color: white;
      background: none;
      font-size: 1rem;
      div{
        //light-button
      }
    }
    .game-field{
      width: 100%;
      height: 50%;
      div{
        //QuizCard
      }
    }

    // 타이머
    .countdown-timer{
      text-align: end;
      padding: 1% 3%;
      width: 100%;
      height: 30%;
      .base-timer {
        position: relative;
        width: 100%;
        height: 100%;

        &__svg {
          width: 10%;
          transform: scaleX(-1);
        }

        &__circle {
          fill: none;
          stroke: none;
        }

        &__path-elapsed {
          stroke-width: 6%;
          stroke: grey;
        }

        &__path-remaining {
          stroke-width: 6%;
          stroke-linecap: round;
          transform: rotate(90deg);
          transform-origin: center;
          transition: 1s linear all;
          fill-rule: nonzero;
          stroke: currentColor;

          &.green {
            color: rgb(65, 184, 131);
          }

          &.orange {
            color: orange;
          }

          &.red {
            color: red;
          }
        }
        // 숫자에 해당하는 css
        &__label {
          position: absolute;
          width: 10%;
          top: 22%;
          right: 3.8%;
          font-size: 2rem;
        }
      }
    }
  }
}
</style>