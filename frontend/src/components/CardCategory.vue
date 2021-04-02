<template>
  <v-container fluid fill-height>
    <div class="languages">
      <div class="language" v-for="(flag, name) in flags" :key="name">
        <img @click="checkLanguage(flag)" :src="require(`@/assets/images/flags/${flag.icon}`)">
      </div>
    </div>
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
      ],
      selectedLanguage: '',
    }
  },
  methods: {
    checkLanguage: function (lang) {
      this.selectedLanguage = lang.name
    },
    startGame: function (info) {
      this.$store.dispatch("CardGameStore/fetchCards", info.id)
      this.$router.push({
        name: "PlayGame",
        params: {
          category: info,
        }
      });
    },
    audioTest: function () {
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
    .language{
      display: flex;
      justify-content: center;
      width: 6%;
      margin: 0 1%;
      cursor: pointer;
      img{
        width: 100%;
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