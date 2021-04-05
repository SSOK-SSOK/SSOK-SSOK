<template>
  <v-container fluid fill-height>
    <v-btn-toggle
      class="languages"
      v-model="selected_language"
      mandatory dark color="deep-purple accent-3" group>
      <v-btn class="language" v-for="(language, name) in languages" :key="name">
        {{language.name}}
      </v-btn>
    </v-btn-toggle>
    <div class="category-field">
      <SpreadCard :category="categories[0]"/>
      <SpreadCard :category="categories[1]"/>
    </div>
    <!-- <v-btn @click="audioTest">오디오 테스트</v-btn> -->
  </v-container>
</template>

<script>
import SpreadCard from "@/components/SpreadCard.vue";
export default {
  name: 'CardCategory',
  components: {
    SpreadCard,
  },
  data () {
    return {
      selected_language: undefined,
      languages: [
        { id: 1, name: "us"},
        { id: 2, name: "cn"},
        { id: 3, name: "jp"},
        { id: 4, name: "vn"},
        { id: 5, name: "fr"},
        { id: 6, name: "es"},
      ],
      categories: [
        { id: 1, name: "Animal", sub: "동물", path: "https://i.pinimg.com/originals/d6/3c/de/d63cded9d8454a5b57ee8bef31c3ee71.gif"},
        { id: 2, name: "Fruit", sub: "과일", path: "https://acegif.com/wp-content/gifs/apple-8.gif"}
      ],
    }
  },
  methods: {
    checkLanguage(lang) {
      this.selectedLanguage = lang.name
    },
    startGame(info) {
      // 언어 내용까지 합해서 넘어가야할 것 같습니다. 
      this.$store.dispatch("CardGameStore/fetchCards", info.id)
      this.$router.push({
        name: "PlayGame",
        params: {
          category: info,
        }
      });
    },
    audioTest() {
      this.$router.push({ name: "AudioTest" })
    }
  }
}
</script>

<style lang="scss" scoped>
.container{
  padding: 0 !important;
  .languages{
    display: flex;
    justify-content: center;
    align-items: center;
    width: 100%;
    height: 10%;
    margin-bottom: 5%;
    background: none !important;
    .language{
      width: 6vw;
      height: 70vh;
      margin: 0;
      overflow: hidden;
      background-color: rgba(22, 18, 0, 0.4);
      box-shadow: 0 2px 3px 0 rgba(151, 150, 146, 0.4);
      cursor: pointer;
      img{
        width: 100%;
        height: auto;
      }
    }
  }
  .category-field{
    display: flex;
    justify-content: space-around;
    perspective: 4500px;
    width: 100%;
    height: 70%;
  }
}
</style>