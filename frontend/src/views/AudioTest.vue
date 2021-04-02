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
            reader.onloadend = function () {
            }
          })
          this.mediaRecorder.start() //녹음 시작
        }).catch((error) => {
          this.$alert({ title:'error', text: 'Failed to turn on the microphone! ' + error })
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
          // Blob 데이터 다운로드
          var ffmpeg = require('ffmpeg');
          var process = new ffmpeg(path);
          process.then(function (audio) {
            audio.fnExtractSoundToMP3('./assets/record.mp3', function (err, file){
              if (!err) {
                console.log(file)
              }
            });
          }, function (err) {
            console.log(err)
          })
        })
        .catch(err => console.log(err))
    }
  }
}

</script>

<style>

</style>