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
  </v-container>
</template>

<script>
import SpreadCard from "@/components/SpreadCard.vue";
export default {
  name: 'CardCategory',
  components: {
    SpreadCard,
  },
  watch: {
    selected_language: function (val) {
      const lanArray = ['ko-KR', 'en-US', 'zh', 'ja-JP', 'vi-VN', 'fr-FR', 'es-ES'];
      this.$store.dispatch("CardGameStore/fetchLanguage", lanArray[val]);
    }
  },
  data () {
    return {
      selected_language: undefined,
      languages: [
        { id: 0, name: "한국어"},
        { id: 1, name: "영  어"},
        { id: 2, name: "중국어"},
        { id: 3, name: "일본어"},
        { id: 4, name: "베트남어"},
        { id: 5, name: "프랑스어"},
        { id: 6, name: "스페인어"},
      ],  
      categories: [
        { id: 1, name: "Animal", sub: "동물", path: "https://i.pinimg.com/originals/d6/3c/de/d63cded9d8454a5b57ee8bef31c3ee71.gif"},
        { id: 2, name: "Fruit", sub: "과일", path: "https://acegif.com/wp-content/gifs/apple-8.gif"}
      ],
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
    height: 14%;
    margin-bottom: 1%;
    background: none !important;
    .language{
      width: 8%;
      height: 60% !important;
      font-size: 1.3em;
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