<template>
  <div :class=chooseContainer(categoryId) @click="startGame(category)">
    <div class="card card-card1">
      <div class="card__face card__face--top">
        <span class="card__value"> K </span>
        <span class="card__suit">
          <svg
            width="20px"
            height="21px"
            viewBox="0 0 20 21"
            version="1.1"
            xmlns="http://www.w3.org/2000/svg"
            xmlns:xlink="http://www.w3.org/1999/xlink"
          >
            <g
              id="Page-1"
              stroke="none"
              stroke-width="1"
              fill="none"
              fill-rule="evenodd"
            >
              <rect
                id="Rectangle-3"
                stroke="#A68385"
                transform="translate(10.035534, 10.071068) rotate(-315.000000) translate(-10.035534, -10.071068) "
                x="3.43933983"
                y="3.47487373"
                width="13.1923882"
                height="13.1923882"
              ></rect>
            </g>
          </svg>
        </span>
      </div>
      <div class="card__face card__face--btm">
        <span class="card__value"> K </span>
        <span class="card__suit">
          <svg
            width="20px"
            height="21px"
            viewBox="0 0 20 21"
            version="1.1"
            xmlns="http://www.w3.org/2000/svg"
            xmlns:xlink="http://www.w3.org/1999/xlink"
          >
            <g
              id="Page-1"
              stroke="none"
              stroke-width="1"
              fill="none"
              fill-rule="evenodd"
            >
              <rect
                id="Rectangle-3"
                stroke="#A68385"
                transform="translate(10.035534, 10.071068) rotate(-315.000000) translate(-10.035534, -10.071068) "
                x="3.43933983"
                y="3.47487373"
                width="13.1923882"
                height="13.1923882"
              ></rect>
            </g>
          </svg>
        </span>
      </div>
    </div>
    <div class="card card-card2">
      <div class="card__face card__face--top">
        <span class="card__value"> Q </span>
        <span class="card__suit">
          <svg
            width="21px"
            height="18px"
            viewBox="0 0 21 18"
            version="1.1"
            xmlns="http://www.w3.org/2000/svg"
            xmlns:xlink="http://www.w3.org/1999/xlink"
          >
            <g
              id="Page-1"
              stroke="none"
              stroke-width="1"
              fill="none"
              fill-rule="evenodd"
            >
              <circle
                id="Oval"
                stroke="#635F5C"
                cx="4.5"
                cy="13.5"
                r="4"
              ></circle>
              <circle
                id="Oval"
                stroke="#635F5C"
                cx="10.5"
                cy="4.5"
                r="4"
              ></circle>
              <circle
                id="Oval"
                stroke="#635F5C"
                cx="16.5"
                cy="13.5"
                r="4"
              ></circle>
            </g>
          </svg>
        </span>
      </div>
      <div class="card__face card__face--btm">
        <span class="card__value"> Q </span>
        <span class="card__suit">
          <svg
            width="21px"
            height="18px"
            viewBox="0 0 21 18"
            version="1.1"
            xmlns="http://www.w3.org/2000/svg"
            xmlns:xlink="http://www.w3.org/1999/xlink"
          >
            <g
              id="Page-1"
              stroke="none"
              stroke-width="1"
              fill="none"
              fill-rule="evenodd"
            >
              <circle
                id="Oval"
                stroke="#635F5C"
                cx="4.5"
                cy="13.5"
                r="4"
              ></circle>
              <circle
                id="Oval"
                stroke="#635F5C"
                cx="10.5"
                cy="4.5"
                r="4"
              ></circle>
              <circle
                id="Oval"
                stroke="#635F5C"
                cx="16.5"
                cy="13.5"
                r="4"
              ></circle>
            </g>
          </svg>
        </span>
      </div>
    </div>
    <div :class=chooseImage(categoryId) >
      <div class="card__face card__face--top">
        <span class="card__value"> {{categoryName}} </span>
      </div>
      <div class="card__face card__face--btm">
        <span class="card__value"> {{categoryName}} </span>
        <span class="card__suit">
          <i class="far fa-lemon"></i>
        </span>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "SpreadCard",
  props: {
    category: Object,
  },
  data() {
    return {};
  },
  computed: {
    categoryId: function () {
      return this.category.id
    },
    categoryImg: function () {
      return this.category.path
    },
    categoryName: function () {
      return this.category.name
    },
    chooseContainer() {
      return (id) => {
        if (id === 1){
          return 'animal-card-container'
        } else {
          return 'fruit-card-container'
        }
      }
    },
    chooseImage(){
      return (id) => {
        if (id === 1){
          return 'card animal-card-card3'
        } else {
          return 'card fruit-card-card3'
        }
      }
    },
  },
  methods: {
    startGame: function (info) {
      this.$store.dispatch("CardGameStore/fetchCards", info.id);
      this.$router.push({
        name: "PlayCardGame",
        params: {
          category: info,
        },
      });
    },
  },
};
</script>

<style lang="scss" scoped>
@import "@/style/spread-card.scss";
</style>
