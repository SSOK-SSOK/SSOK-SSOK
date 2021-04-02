<template>
  <v-layout align-center justify-center>
    <v-card flat width="1400" style="background-color: transparent;">
      <v-container>
        <!------------ 국기 아이콘 리스트 ------------->
        <v-row class="my-10 mx-15 px-10">
          <v-col v-for="(flag, name) in flags" :key="name" align="center">
            <v-btn icon width="140" height="140">
              <v-avatar class="mr-2" size="140">
                <img :src="require(`@/assets/images/flags/${flag.icon}`)">
              </v-avatar>
            </v-btn>
          </v-col>
        </v-row>
        <!--------------- 카드 리스트 ---------------->
        <v-row>
          <v-col
            v-for="(card, idx) in cards"
            :key="idx"
          >
            <v-card
              color="white"
              rounded="xl"
            >
              <v-card-title class="display-3 font-weight-bold" style="color: #5254ac">
                {{ card.name }}
              </v-card-title>

              <v-card-text class="d-flex justify-space-between">
                <p class="display-1 font-weight-bold" style="color: #5254ac">{{ card.sub }}</p>
                <v-spacer></v-spacer>
                <p class="title">15cards</p>
              </v-card-text>

              <v-card-actions>
                <v-btn
                  rounded
                  elevation="0"
                  color="#f1ca21"
                  large
                  width="50%"
                  class="ml-4"
                  @click="startGame(card)"
                >
                  게임하기
                </v-btn>
              </v-card-actions>
            </v-card>
          </v-col>
        </v-row>
        <v-btn @click="audioTest">오디오 테스트</v-btn>
      </v-container>
    </v-card>
  </v-layout>
</template>

<script>
export default {
  name: 'CardCategory',
  data: function () {
    return {
      flags: [
        { name: "ko", icon: "kr.png"},
        { name: "us", icon: "us.png"},
        { name: "cn", icon: "cn.png"},
        { name: "jp", icon: "jp.png"},
        { name: "fr", icon: "fr.png"},
        { name: "es", icon: "es.png"},
        { name: "vn", icon: "vn.png"}
      ],
      cards: [
          { id: 1, name: "Animals", sub: "동물"},
          { id: 2, name: "Fruits", sub: "과일"}
      ]
    }
  },
  methods: {
    startGame: function (info) {
      this.$store.dispatch("CardGameStore/fetchCards", info.id)
      this.$router.push({
        name: "PlayGame",
        params: {
          card: info,
        }
      });
    },
    audioTest: function () {
      this.$router.push({ name: "AudioTest" })
    }
  }
}
</script>

<style>

</style>