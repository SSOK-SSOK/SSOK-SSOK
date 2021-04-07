<template>
  <div>
    <div class="flip-card" v-bind:class="{ active: flip }">
      <div class="flip-card-inner">
        <div class="flip-card-front" @click="flipCard">
          <div class="overlay" style="border-radius: 30px">
            <h1 class="text">정답을 보고 싶으면 카드를 더블클릭하세요</h1>
          </div>
          <v-img
            class="image"
            :src="cardImg"
            height="100%"
            style="border-radius: 30px"
          ></v-img>
        </div>
        <div class="flip-card-back" style="border-radius: 30px">
          <h2 v-if="flip" class="text-center">{{ cardAnswer }}</h2>
        </div>
      </div>
      <div class="d-flex justify-content-center">
        <button v-if="flip" class="auth-button mx-auto" @click="goNext">
          다음 문제
        </button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "QuizCard",
  props: {
    currentQuiz: Object,
    flipped: Boolean,
  },
  data() {
    return {
      flip: false,
    };
  },
  computed: {
    cardImg: function () {
      return this.currentQuiz.path;
    },
    cardAnswer: function () {
      return this.currentQuiz.word;
    },
  },
  watch: {
    flipped(newValue) {
      // 시간초과라서 카드를 뒤집으라는 prop이오면 카드를 뒤집습니다.
      if (newValue === true) {
        this.flipCard();
      }
    },
  },
  methods: {
    // 카드 뒤집기
    flipCard() {
      this.flip = !this.flip;
      this.$emit("is_flipped", true);
    },
    // 다음 카드로 이동
    goNext() {
      console.log("다음 카드");
      this.flipCard();
      this.$emit("nextCard", true);
    },
  },
};
</script>

<style lang="scss" scoped>
@import "@/style/auth-button.scss";
.flip-card {
  background-color: transparent;
  width: 600px;
  height: 400px;
  perspective: 1000px; /* Remove this if you don't want the 3D effect */
  transition: 0.5s ease;
  backface-visibility: hidden;
}

/* This container is needed to position the front and back side */
.flip-card-inner {
  position: relative;
  width: 100%;
  height: 100%;
  margin-bottom: 5%;
  transition: transform 0.8s;
  transform-style: preserve-3d;
}

/* Do an horizontal flip when you move the mouse over the flip box container */
.active .flip-card-inner {
  transform: rotateY(180deg);
}

/* Position the front and back side */
.flip-card-front,
.flip-card-back {
  position: absolute;
  width: 100%;
  height: 100%;
  -webkit-backface-visibility: hidden;
  backface-visibility: hidden;
}

/* Style the back side */
.flip-card-back {
  z-index: 100;
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: dodgerblue;
  color: white;
  font-size: 3rem;
  transform: rotateY(180deg);
}

.overlay {
  position: absolute;
  z-index: 5;
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100%;
  width: 100%;
  opacity: 0;
  transition: 0.5s ease;
  background-color: rgb(0, 0, 0);
}

.flip-card-front:hover .overlay {
  opacity: 0.7;
}
</style>
