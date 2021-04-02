<template>
  <v-layout fluid fill-height>
    <!------------ 국기 아이콘 리스트 ------------->
    <!-- <div class="languages">
      <div class="language" v-for="(flag, name) in flags" :key="name">
        <v-btn icon>
          <img :src="require(`@/assets/images/flags/${flag.icon}`)">
        </v-btn>
      </div>
    </div> -->
    <div class="category-field">
      <SpreadCard/>
      <!-- <div v-for="(category, idx) in categories" :key="idx">
        <SpreadCard :category="category"/>
      </div> -->
    </div>
    <!--------------- 카드 리스트 ---------------->
    <!-- <v-row>
      <v-col
        v-for="(category, idx) in categories"
        :key="idx"
      >
        <v-card
          color="white"
          rounded="xl"
        >
          <v-card-title class="display-3 font-weight-bold" style="color: #5254ac">
            {{ category.name }}
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
              @click="startGame(category)"
            >
              게임하기
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-col>
    </v-row> -->
  </v-layout>
</template>

<script>
import SpreadCard from "@/components/SpreadCard.vue";
export default {
  name: 'CardCategory',
  components: {
    SpreadCard,
  },
  data: function () {
    return {
      flags: [
        { name: "kr", icon: "kr.png"},
        { name: "us", icon: "us.png"},
        { name: "cn", icon: "cn.png"},
        { name: "jp", icon: "jp.png"},
        { name: "fr", icon: "fr.png"},
        { name: "es", icon: "es.png"},
        { name: "vn", icon: "vn.png"}
      ],
      categories: [
          { id: 1, name: "Animal", sub: "동물", path: "https://i.pinimg.com/originals/d6/3c/de/d63cded9d8454a5b57ee8bef31c3ee71.gif"},
          { id: 2, name: "Fruit", sub: "과일", path: "https://acegif.com/wp-content/gifs/apple-8.gif"}
      ]
    }
  },
  methods: {
    // startGame: function (info) {
    //   this.$store.dispatch("CardGameStore/fetchCards", info.id)
    //   this.$router.push({
    //     name: "PlayGame",
    //     params: {
    //       card: info,
    //     }
    //   });
    // }
  }
}
</script>

<style lang="scss" scoped>
.layout{
  .languages{
    display: flex;
    justify-content: center;
    align-items: center;
    width: 100%;
    height: 15%;
    .language{
      display: flex;
      justify-content: center;
      width: 8%;
      .v-btn{
        .v-btn__content{
          img{
            width: 20%;

          }
        }
      }
    }
  }
  .category-field{
    width: 100%;
    height: 60%;
    display: flex;
    align-items: center;
    div{
      //SpreadCard
    }
  }
}
</style>