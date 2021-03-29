<template>
  <v-container>
    <NavBar />
    <div id="microphone-test">
        <!-- controls show audio -->
      <audio ref="recordPlayer" controls src="" />
      <v-btn @click="startRecord">녹음</v-btn>
      <v-btn @click="endRecord">중단</v-btn>
    </div>
  </v-container>
</template>

<script>
import NavBar from "@/components/NavBar.vue";
export default {
  name: "PlayGame",
  components: {
    NavBar
  },
  methods: {
    startRecord() {
      navigator.mediaDevices.getUserMedia({ audio: true }).then((stream) => {
        this.mediaRecorder = new MediaRecorder(stream)
        this.mediaRecorder.addEventListener('dataavailable', (event) => {
          //After monitoring the recording, put the recorded audio into the audio tag
          this.$refs.recordPlayer.src = URL.createObjectURL(event.data)
        })
        this.mediaRecorder.start() //start recording
      }).catch((error) => {
        this.$alert({ title:'error', text: 'Failed to turn on the microphone! ' + error })
      })
    },
    endRecord() {
      this.mediaRecorder.stop() //End recording
    }
  }
}

</script>

<style>

</style>