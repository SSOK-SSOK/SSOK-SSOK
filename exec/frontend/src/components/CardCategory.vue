<template>
  <v-container fluid fill-height>
    <v-btn-toggle
      class="languages-big d-none d-md-flex"
      v-model="selected_language"
      mandatory
      dark
      color="deep-purple accent-3"
    >
      <v-btn class="language" v-for="(language, name) in languages" :key="name">
        {{ language.name }}
      </v-btn>
    </v-btn-toggle>
    <v-btn-toggle
      class="languages-small d-flex d-md-none mx-auto"
      v-model="selected_language"
      mandatory
      dark
      color="deep-purple accent-3"
    >
      <v-btn class="language" v-for="(language, name) in languages" :key="name">
        {{ language.name }}
      </v-btn>
    </v-btn-toggle>

    <v-row class="category-field">
      <div class="col-lg-6">
        <SelectCard :category="categories[0]" />
      </div>
      <div class="col-lg-6">
        <SelectCard :category="categories[1]" />
      </div>
    </v-row>
  </v-container>
</template>

<script>
import SelectCard from "@/components/SelectCard.vue";
export default {
  name: "CardCategory",
  components: {
    SelectCard,
  },
  watch: {
    selected_language: function (val) {
      const lanArray = [
        "ko-KR",
        "en-US",
        "zh",
        "ja-JP",
        "vi-VN",
        "fr-FR",
        "es-ES",
      ];
      this.$store.dispatch("CardGameStore/fetchLanguage", lanArray[val]);
    },
  },
  data() {
    return {
      selected_language: undefined,
      languages: [
        { id: 0, name: "한국어" },
        { id: 1, name: "영  어" },
        { id: 2, name: "중국어" },
        { id: 3, name: "일본어" },
        { id: 4, name: "베트남어" },
        { id: 5, name: "프랑스어" },
        { id: 6, name: "스페인어" },
      ],
      categories: [
        {
          id: 1,
          name: "Animal",
          sub: "동물",
          path:
            "https://i.pinimg.com/originals/d6/3c/de/d63cded9d8454a5b57ee8bef31c3ee71.gif",
        },
        {
          id: 2,
          name: "Fruit",
          sub: "과일",
          path: "https://acegif.com/wp-content/gifs/apple-8.gif",
        },
      ],
      page: 1,
    };
  },
};
</script>

<style lang="scss" scoped>
.container {
  width: 100%;
  height: 85%;
  padding: 0 1% !important;

  .languages-big {
    display: flex;
    justify-content: center;
    align-items: center;
    width: 100%;
    height: 15%;
    background: none !important;
    .language {
      width: 8vw;
      height: 8vh !important;
      font-size: 1.2vw;
      margin: 0;
      overflow: hidden;
      background-color: rgba(22, 18, 0, 0.4);
      box-shadow: 0 2px 3px 0 rgba(151, 150, 146, 0.4);
      cursor: pointer;
      img {
        width: 100%;
        height: auto;
      }
    }
  }
  .languages-small {
    display: flex;
    justify-content: center;
    align-items: center;
    width: 100%;
    height: 15%;
    background: none !important;
    .language {
      width: 4vw !important;
      height: 7vh !important;
      font-size: 1.2vw;
      margin: 0;
      overflow: hidden;
      background-color: rgba(22, 18, 0, 0.4);
      box-shadow: 0 2px 3px 0 rgba(151, 150, 146, 0.4);
      cursor: pointer;
      img {
        width: 100%;
        height: auto;
      }
    }
  }

  .category-field {
    width: 100%;
    height: 80%;
    padding: 0 15%;
  }
}
</style>
