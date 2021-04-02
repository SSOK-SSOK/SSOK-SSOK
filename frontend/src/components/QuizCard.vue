<template>
  <v-card
    class="mx-auto"
    max-width="344"
  >
    <v-img
      :src=cardImg
      height="200px"
    ></v-img>

    <v-card-title>
      {{ cardAnswer }}
    </v-card-title>

    <v-card-subtitle>
      1,000 miles of wonder
    </v-card-subtitle>
  </v-card>
</template>

<script>
// import { mapState } from 'vuex';
export default {
  name: 'QuizCard',
  components: {
  },
  props: {
    currentQuiz: Object
  },
  data() {
    return {
      cardImg: this.currentQuiz.path,
      cardAnswer: this.currentQuiz.word,
    };
  },
  created() {
  },
  computed: {
    // cardImg: function () {
    //   return this.currentCard.path
    // },
    // cardAnswer: function () {
    //   return this.currentCard.word
    // }
  },
  methods: {
  },
}
</script>

<style lang="scss" scoped>

</style>