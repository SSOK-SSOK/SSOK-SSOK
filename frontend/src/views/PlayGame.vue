<template>
  <v-container>
    <NavBar />
    <div>
      <audio-recorder ref="recorder" 
        :after-recording="setRecorded" 
        :before-recording="startRecord"
      />
    </div>
  </v-container>
</template>

<script>
import NavBar from "@/components/NavBar.vue";
export default {
  name: "PlayGame",
  data: function () {
    return {

    }
  },
  components: {
    NavBar
  },
  methods: {
    callback (data) {
      console.debug(data)
    },
    startRecord() {
      console.log('start recording')
    },
    setRecorded() {
      console.log('finish recording')
      console.log(this.recordList)
    },
    uploadurl : function () {

    }
  }
}
</script>

<style>

</style>