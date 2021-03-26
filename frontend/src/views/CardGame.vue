<template>
  <v-container fluid pa-0>
    <v-row class="mt-3 mb-7">
      <v-app-bar
        color="#040431"
        :elevation="0"
        max-height="80"
        class="mb-10"
      >
        <v-btn icon class="ml-2" @click="back">
          <v-icon style="color: white;" size="90">mdi-arrow-left</v-icon>
        </v-btn>
      </v-app-bar>
    </v-row>
    <CardCategory/>
  </v-container>
</template>

<script>
import CardCategory from "@/components/CardCategory.vue";
export default {
  name: 'CardGame',
  components: {
    CardCategory,
  },
  methods: {
    back: function () {
      this.$router.push({ name: "MainPage" });
    },
  }
}
</script>

<style>

</style>