<template>
  <v-container>
    <NavBar />
    <div id="microphone-test">
      <audio ref="recordPlayer" controls src="">
      </audio>
      <v-btn @click="startRecord">녹음</v-btn>
      <v-btn @click="endRecord">중단</v-btn>
      <v-btn @click="upload">업로드</v-btn>
    </div>
  </v-container>
</template>

<script>
import NavBar from "@/components/NavBar.vue";
import axios from 'axios';
export default {
  name: "AudioTest",
  data: function () {
    return {
      blob: {}
    }
  },
  components: {
    NavBar
  },
  methods: {
    startRecord: function () {
      navigator.mediaDevices.getUserMedia({ audio: true })
        .then((stream) => {
          this.mediaRecorder = new MediaRecorder(stream)
          this.mediaRecorder.addEventListener('dataavailable', (event) => {
            //녹음이 끝나면 Blob 객체를 가리키는 URL을 생성해서 audio에 저장
            this.$refs.recordPlayer.src = URL.createObjectURL(event.data)
            console.log(this.$refs.recordPlayer.src)
            
            //Blob 객체 저장
            this.blob = event.data
            var reader = new FileReader();

            console.log(this.blob)
            console.log(stream)

            // reader.readAsDataURL(this.blob);
            // reader.onload = () => {
            //   const base64AudioMessage = reader.result.split(',')[1];
            //   console.log(base64AudioMessage)

            //   const headers = { 'Content-Type': 'application/json' }
            //   axios.post("https://j4a201.p.ssafy.io/card-api/file/upload",base64AudioMessage, headers)
            //     .then(res => console.log(res))
            //     .catch(error => console.log(error))
            // }

            // reader.onloadend = function () {
            // }
          })
          this.mediaRecorder.start() //녹음 시작
        }).catch((error) => {
          console.log(error)
        })
    },

    endRecord: function () {
      this.mediaRecorder.stop() //녹음 중단하면 audio tag에 저장도 멈춤
    },

    upload: function () {
      const formData = new FormData();
      console.log(this.blob)
      formData.append('file', this.blob);

      const headers = {'Content-Type': 'multipart/form-data'}
            
      axios.post("https://j4a201.p.ssafy.io/card-api/file/upload", formData, headers)
        .then(res => {
          console.log(res)
          const path = res.data.object;
        })
        .catch(err => console.log(err))
    }
  }
}
</script>

<style>

</style>