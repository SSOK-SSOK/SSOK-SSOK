<template>
  <v-container>
    <NavBar />
    <div id="microphone-test">
      <audio ref="recordPlayer" controls src="">

      </audio>
      <v-btn @click="startRecord">녹음</v-btn>
      <v-btn @click="endRecord">중단</v-btn>
    </div>
  </v-container>
</template>

<script>
import NavBar from "@/components/NavBar.vue";
import axios from 'axios';
export default {
  name: "PlayGame",
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
            let blob = event.data

            //base64로 변환할 FileReader
            var reader = new FileReader();
            // blob -> base64
            reader.readAsDataURL(blob);
            reader.onloadend = function () {
              var base64String = reader.result;
              // console.log(base64String)
              var slicebase64 = base64String.slice(35,)
              const data = {
                'access_key': '0d52fa25-2ce8-4c0f-b453-615126f29c56',
                'argument': {
                  'language_code': 'english',
                  'audio': slicebase64
                }
              }
              axios.post('http://aiopen.etri.re.kr:8000/WiseASR/Recognition', data)
                .then(res => console.log(res))
                .catch(err => console.log(err))
            }
          })
          this.mediaRecorder.start() //녹음 시작
        }).catch((error) => {
          this.$alert({ title:'error', text: 'Failed to turn on the microphone! ' + error })
        })
    },

    endRecord: function () {
      this.mediaRecorder.stop() //녹음 중단하면 audio tag에 저장도 멈춤
    }
  }
}

</script>

<style>

</style>