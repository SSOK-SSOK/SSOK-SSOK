<template>
  <div class="base-timer">
    <svg
      class="base-timer__svg"
      viewBox="0 0 100 100"
      xmlns="http://www.w3.org/2000/svg"
    >
      <g class="base-timer__circle">
        <circle
          class="base-timer__path-elapsed"
          cx="50"
          cy="50"
          r="45"
        ></circle>
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
    <h1 class="label">{{ formattedTimeLeft }}</h1>
  </div>
</template>

<script>
const FULL_DASH_ARRAY = 283;
const WARNING_THRESHOLD = 10;
const ALERT_THRESHOLD = 5;
const TIME_LIMIT = 15;
const COLOR_CODES = {
  info: {
    color: "green",
  },
  warning: {
    color: "orange",
    threshold: WARNING_THRESHOLD,
  },
  alert: {
    color: "red",
    threshold: ALERT_THRESHOLD,
  },
};


export default {
  name: "Timer",
  props: { quizIdx: Number, resetTime: Boolean },
  data() {
    return {
      timePassed: 0,
      timerInterval: null,
    }
  },
  computed: {
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
    },
  },
  watch: {
    // 카드가 바뀔 때마다 타이머를 시작합니다.
    quizIdx(newValue) {
      if (newValue < 15) {
        console.log(newValue + 1 + "번째 카드");
        this.start();
      } else {
        console.log("끝!");
      }
    },
    // 시간이 15초가 지나면 타이머를 초기화하고 문제를 못 품 (false)을 상위 컴포넌트에 전달한다.
    timePassed(newValue) {
      if (newValue === 15) {
        this.onTimesUp();
        this.$emit("solvingStatus", false);
      }
    },
    resetTime(newValue) {
      if (newValue === true) {
        this.onTimesUp();
      }
    },
  },
  mounted() {
    if (this.quizIdx === 0) {
      console.log("첫번째 카드");
      this.start();
    }
  },
  methods: {
    moveNext() {
      this.onTimesUp();
    },
    // 타이머 정지 및 초기화
    onTimesUp() {
      clearInterval(this.timerInterval);
      this.timePassed = 15;
    },
    // 타이머 시작
    start() {
      this.timePassed = 0;
      this.timerInterval = setInterval(() => (this.timePassed += 1), 1000);
    },
  },
};
</script>

<style lang="scss" scoped>
.base-timer {
  position: relative;
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
  height: 100%;
  svg {
    width: 30%;
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
  // 숫자
  .label {
    position: absolute;
    font-size: 3rem;
  }
}
</style>
