<template>
  <div class="frame">
    <div class="center">
      <input type="checkbox" id="audio-record" v-model="isRecorded">
      <label for="audio-record">
        <svg
          width="100px"
          height="100px"
          viewBox="0 0 100 100"
        >
        <circle class="circle" cx="50" cy="50" r="47"/>
      </svg>
        <div class="icone">
          <div></div>			
          <div></div>
          <div></div>
        </div>
        <div class="dots">
          <div></div>			
          <div></div>
          <div></div>
        </div>
      </label>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
export default {
  name: "Audio",
  data: function () {
    return {
      isRecorded: false,
      blob: {}
    }
  },
  watch: {
    isRecorded: function (val) {
      console.log(val)
      if (val === true) {
        this.startRecord();
      } else {
        this.endRecord();
      }
    }
  },
  methods: {
    startRecord: function () {
      navigator.mediaDevices.getUserMedia({ audio: true })
        .then((stream) => {
          this.mediaRecorder = new MediaRecorder(stream)
          this.mediaRecorder.addEventListener('dataavailable', (event) => {
            //Blob 객체 저장
            this.blob = event.data
          })
          this.mediaRecorder.start() //녹음 시작
        }).catch((error) => {
          console.log(error)
        })
    },

    endRecord: function () {
      this.mediaRecorder.stop() //녹음 중단하면 audio tag에 저장도 멈춤
      this.upload()
    },

    upload: function () {
      const formData = new FormData();
      formData.append('file', this.blob);

      const headers = {'Content-Type': 'multipart/form-data'}
            
      axios.post("https://j4a201.p.ssafy.io/card-api/file/upload", formData, headers)
        .then(res => {
          console.log(res)
          const path = res.data.object;
          // Blob 데이터 다운로드
          // var ffmpeg = require('ffmpeg');
          // var process = new ffmpeg(path);
          // process.then(function (audio) {
          //   audio.fnExtractSoundToMP3('./assets/record.mp3', function (err, file){
          //     if (!err) {
          //       console.log(file)
          //     }
          //   });
          // }, function (err) {
          //   console.log(err)
          // })
        })
        .catch(err => console.log(err))
    }
  }

}
</script>

<style lang="scss" scoped>
// delete the following line if no text is used
// edit the line if you wanna use other fonts
@import url(https://fonts.googleapis.com/css?family=Open+Sans:700,300);

// use only the available space inside the 400x400 frame
.frame {
  position: relative;
  top: 80px;
  left: 50%;
  width: 400px;
  height: 400px;
  margin-top: -200px;
  margin-left: -200px;
  border-radius: 2px;
  overflow: hidden;
  background: transparent;
  color: #333;
	font-family: 'Open Sans', Helvetica, sans-serif;
	-webkit-font-smoothing: antialiased;
	-moz-osx-font-smoothing: grayscale;
}

.center {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%,-50%);
}
#audio-record{
	position:absolute;
	left:-999px;
}
#audio-record:checked + label{
  /*animation: loadingCircle 2s infinite 0s ease-in-out both,
		rotateCircle  2s infinite 0s ease-in-out ;*/
	.circle {
			animation: circle 5s linear infinite;
		}
	.icone{
		animation: icone 1s alternate 0s both ;
	}
	.dots div{
		animation: bubling 1s infinite 1s ease-in-out alternate;
	}
	.dots div:nth-child(1){
		animation-delay:1s;
	}
	.dots div:nth-child(2){
		animation-delay:1.2s;
	}
	.dots div:nth-child(3){
		animation-delay:1.4s;
	}
}

.circle {
		fill: none;
		stroke: #fff;
		stroke-width: 3;
		stroke-linecap: round;
		transform-origin: 50% 50%;
	}
label{
	display:block;
	width:100px;
	height:100px;
	/*border: 2px solid #fff;*/
	border-radius: 50%;
	cursor:pointer;
}
.icone{
	position:absolute;
	top:calc(50% - 35px);
	left:calc(50% - 12.5px);
}
.dots{
	position:absolute;
	top:45%;
	left:30%;
}
.dots div{
	width:8px;
	height:8px;
	background: #fff;
	border-radius: 50%;
	display:inline-block;
	margin:2px;
	opacity:0;
  /*animation: bubling 1s infinite ease-in-out alternate;*/
}
.icone div:nth-child(1){
	width:25px;
	height:50px;
	background: #fff;
	border-radius: 50px;
}
.icone div:nth-child(1):before{
	content: "";
	display:block;
	width:6px;
	height:15px;
	background: #684595;
	border-radius: 50px;
	position:absolute;
	left:10px;
	top:10px;
}
.icone div:nth-child(2){
	width:3px;
	height:10px;
	background: #fff;
	margin-left:11px;
}
.icone div:nth-child(3){
	width:25px;
	height:3px;
	background: #fff;
	border-radius: 50px;
}
@keyframes rotateCircle{
	0%{
		/*transform: rotate(0deg);*/
	}
	100%{
		transform: rotate(360deg);
	}
}
@keyframes bubling{
	0% {
		opacity:1;
		transform: translateY(5px);
	}
	50% {
		transform: translateY(-5px);
	}
	100% {
		opacity:1;
		transform: translateY(5px)
	}
}
@keyframes icone{
	0% {
		opacity:1;
		transform: scale(1);
	}
	50% {
		transform: scale(1.2);
		opacity:1;
	}
	100% {
		opacity:0;
		transform: scale(0.1);
	}
}
@keyframes loadingCircle{
	0%{
	border-top: 1px solid transparent;
	border-right: 2px solid transparent;
	border-bottom: 2px solid transparent;
	border-left: 2px solid transparent;
	}
	25%{
		border-top: 2px solid #fff;
	border-right: 1px solid transparent;
	border-bottom: 2px solid transparent;
	border-left: 2px solid transparent;
	}
	50%{
			border-top: 2px solid #fff;
	border-right: 2px solid #fff;
	border-bottom: 1px solid transparent;
	border-left: 2px solid transparent;
	}
	75%{
	border-top: 2px solid #fff;
	border-right: 2px solid #fff;
	border-bottom: 2px solid #fff;
	border-left: 1px solid transparent;
	}
	100%{
		border-top: 2px solid #fff;
	border-right: 2px solid #fff;
	border-bottom: 2px solid #fff;
	border-left: 2px solid #fff;
	}
}

@keyframes circle {
	0% {
		stroke-dashoffset: 0;
		stroke-dasharray: 296 296;
		transform: rotate(0deg);
	}
	100% {
		stroke-dashoffset: (-296 * 2);
		stroke-dasharray: 296 296;
		transform: rotate(360deg);
	}
}


</style>
