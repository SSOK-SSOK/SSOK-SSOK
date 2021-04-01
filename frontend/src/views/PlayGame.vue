<template>
  <div>
    <v-btn @click="button">버튼</v-btn>
  </div>
</template>

<script>
import axios from 'axios'
export default {
  name: 'PlayGame',
  methods: {
    button: function () {
      const data = { "id": "1" }
      axios.get("https://j4a201.p.ssafy.io/card-api/v2/api-docs/card/1", data)
        .then(res => console.log(res))
        .catch(err => console.log(err))
    }
  }

}
</script>

<style>

</style>