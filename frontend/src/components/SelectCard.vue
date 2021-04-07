<template>
  <div class="card-container">
    <div :class=categoryClass(categoryId) @click="startGame(category)">
      <div class="card-top"><span class="card-value">{{categoryName}}</span></div>
      <div class="card-body"></div>
      <div class="card-bottom"><span class="card-value">{{categoryName}}</span></div>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";
export default {
  name: 'SelectCard',
  props: {
    category: Object,
  },
  data() {
    return {
      
    };
  },
  computed: {
    ...mapState("CardGameStore", ["language"]),
    categoryId: function () {
      return this.category.id
    },
    categoryImg: function () {
      return this.category.path
    },
    categoryName: function () {
      return this.category.name
    },
    categoryClass() {
      return (id) => {
        if (id === 1){
          return 'card animal-background'
        } else {
          return 'card fruit-background'
        }
      }
    },  },
  mounted() {
    
  },
  methods: {
    startGame(info) {
      const data = [info.id, this.language];
      this.$store.dispatch("CardGameStore/fetchCards", data);
      this.$router.push({name: "PlayCardGame"});
    },
  },
};
</script>

<style lang="scss" scoped>
.card-container{
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
  height: 100%;
  .card{
    width: 60%;
    height: 62%;
    color: #a68385;
    border-radius: 20px;
    box-shadow: 0 1px 2px 0 rgba(151, 150, 146, 0.4),
      0 2px 4px 0 rgba(151, 150, 146, 0.4);
    cursor: pointer;
    .card-top{
      width: 100%;
      height: 10%;
      padding: 5%;
      .card__value {
        display: block;
        text-align: center;
        font-size: 1em;
      }
    }
    .card-body{
      width: 100%;
      height: 80%;
    }
    .card-bottom{
      width: 100%;
      height: 10%;
      padding: 5%;
      transform: rotate(180deg);
      .card__value {
        display: block;
        text-align: center;
        width: 1rem;
      }
    }
  }
}
.animal-background{
  background-image: url("https://i.pinimg.com/originals/d6/3c/de/d63cded9d8454a5b57ee8bef31c3ee71.gif");
  background-size: 160%;
  background-position: 45%;
}
.fruit-background{
  background-image: url("https://acegif.com/wp-content/gifs/apple-8.gif");
  background-size: 160%;
  background-position: 50%;
}
</style>