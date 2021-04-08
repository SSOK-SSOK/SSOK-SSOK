<template>
  <div class="stepper">
    <p class="howToPlay">게임 방법</p>
    <v-stepper v-model="e1">
      <v-stepper-header>
        <v-stepper-step :complete="e1 > 1" step="1">
          언어 & 카드 선택
        </v-stepper-step>
        <v-divider></v-divider>
        <v-stepper-step :complete="e1 > 2" step="2"> 게임 시작 </v-stepper-step>
        <v-divider></v-divider>
        <v-stepper-step :complete="e1 > 3" step="3"> 정답 보기 </v-stepper-step>
        <v-divider></v-divider>
        <v-stepper-step step="4"> 게임 즐기기 </v-stepper-step>
      </v-stepper-header>

      <v-stepper-items>
        <v-stepper-content step="1">
          <v-card class="mb-6" color="grey lighten-1" height="300px">
            <img src="@/assets/images/cardStep1.jpg" />
          </v-card>
          <p>🌍 원하는 언어와 카드를 선택해주세요</p>
          <div class="d-flex">
            <v-spacer></v-spacer>
            <v-btn outlined color="indigo" class="text--white" @click="e1 = 2">
              다음
            </v-btn>
          </div>
        </v-stepper-content>

        <v-stepper-content step="2">
          <v-card class="mb-6" color="grey lighten-1" height="300px">
            <img src="@/assets/images/cardStep2.jpg" />
          </v-card>
          <p>
            🎙 [녹음] 버튼을 눌러 단어를 말해보세요! ❗❕(주의)❗❕ 한 번 더
            눌러야 정답이 제출됩니다
          </p>
          <div class="d-flex">
            <v-btn outlined color="indigo" @click="e1 = 1"> 이전 </v-btn>
            <v-spacer></v-spacer>
            <v-btn outlined color="indigo" @click="e1 = 3"> 다음 </v-btn>
          </div>
        </v-stepper-content>

        <v-stepper-content step="3">
          <v-card class="mb-6" color="grey lighten-1" height="300px">
            <img src="@/assets/images/cardStep3.jpg" />
          </v-card>
          <p>
            ✅ 목소리를 들려주면 정답 여부를 알려주면서 카드 뒷면의 답을 볼 수
            있습니다
          </p>
          <div class="d-flex">
            <v-btn outlined color="indigo" @click="e1 = 2"> 이전 </v-btn>
            <v-spacer></v-spacer>
            <v-btn outlined color="indigo" @click="e1 = 4"> 다음 </v-btn>
          </div>
        </v-stepper-content>

        <v-stepper-content step="4">
          <v-card class="mb-6" color="grey lighten-1" height="300px">
            <img src="@/assets/images/cardStep4.jpg" />
          </v-card>
          <p>🎇 [다음 문제] 버튼을 눌러 게임을 마음껏 즐겨보세요</p>
          <div class="d-flex">
            <v-btn outlined color="indigo" @click="e1 = 3"> 이전 </v-btn>
            <v-spacer></v-spacer>
            <v-btn color="warning" @click="playGame"> 게임 시작 </v-btn>
          </div>
        </v-stepper-content>
      </v-stepper-items>
    </v-stepper>
  </div>
</template>

<script>
export default {
  data() {
    return {
      e1: 1,
    };
  },
  methods: {
    playGame() {
      this.$emit("stepperDone", true);
    },
  },
};
</script>

<style lang="scss" scoped>
.stepper {
  margin-top: 2%;
  width: 40%;
  .howToPlay {
    z-index: 100;
    width: 70%;
    margin: 3% auto;
    background: rgba(22, 18, 0, 0.5);
    color: white;
    text-align: center;
    font-size: 2vw;
  }
  p {
    font-size: 1vw;
    color: black;
  }
  v-stepper-step {
    color: deep-purple darken-4;
  }
  img {
    width: 100%;
    height: 100%;
  }
}
</style>
