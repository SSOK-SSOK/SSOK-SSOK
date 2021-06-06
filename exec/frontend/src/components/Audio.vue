<template>
  <div class="frame">
    <div class="center">
      <input type="checkbox" id="audio-record" v-model="isRecorded" />
      <label for="audio-record">
        <svg width="100px" height="100px" viewBox="0 0 100 100">
          <circle class="circle" cx="50" cy="50" r="47" />
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
import axios from "axios";
import { mapState } from "vuex";

// 실시간 음성 녹음 & 파일로 변환 하는 라이브러리
import "@/plugins/p5.sound.js";
import p5 from "p5";
let mic, recorder, soundFile;

export default {
  name: "Audio",
  data: function () {
    return {
      isRecorded: false,
    };
  },
  props: {
    quizIdx: Number
  },
  computed: {
    ...mapState("CardGameStore", ["playingCards", "language"]),
  },
  watch: {
    isRecorded: function (val) {
      if (val === true) {
        this.load();
        setTimeout(() => {
          this.startRecord();
        }, 500);
      } else {
        this.endRecord();
      }
    },
  },
  methods: {
    // 1. 녹음을 위한 마이크 접근
    load: function () {
      mic = new p5.AudioIn();
      mic.start();
      recorder = new p5.SoundRecorder();
      recorder.setInput(mic);
      soundFile = new p5.SoundFile();
    },

    // 2. load되어 있다면 녹음 시작
    startRecord: function () {
      if (mic.enabled) {
        let audio_p5 = new p5();
        audio_p5.getAudioContext().resume();
        recorder.record(soundFile);
      }
    },

    // 3. 녹음을 멈춤
    endRecord: function () {
      recorder.stop();
      setTimeout(() => {
        this.store();
      }, 200);
    },

    // 4. 파일을 서버로 전송
    store: function () {
      if (soundFile.buffer == null) {
        this.$store.dispatch(
          "CardGameStore/fetchAlertMessage",
          "소리를 잘 못들었어요ㅠㅠ"
        );
        this.$emit("is_loading", false);
        this.$emit("is_flipped", true);
      } else {
        this.$emit("is_loading", true);
        const view = this.convertToWav(soundFile.buffer);
        setTimeout(() => {
          this.writeFile([view], "record", "wav");
        }, 200);
      }
    },

    // 4-1. 녹음한 데이터를 Wav 파일형식의 buffer로 저장. chunk로 조각내어서 array를 반환
    convertToWav: function (audioBuffer) {
      var leftChannel, rightChannel;
      leftChannel = audioBuffer.getChannelData(0);
      // handle mono files
      if (audioBuffer.numberOfChannels > 1) {
        rightChannel = audioBuffer.getChannelData(1);
      } else {
        rightChannel = leftChannel;
      }
      var interleaved = this.interleave(leftChannel, rightChannel);
      var buffer = new window.ArrayBuffer(44 + interleaved.length * 2);
      var view = new window.DataView(buffer);
      this.writeUTFBytes(view, 0, "RIFF");
      view.setUint32(4, 36 + interleaved.length * 2, true);
      this.writeUTFBytes(view, 8, "WAVE");
      // FMT sub-chunk
      this.writeUTFBytes(view, 12, "fmt ");
      view.setUint32(16, 16, true);
      view.setUint16(20, 1, true);
      // stereo (2 channels)
      view.setUint16(22, 2, true);
      view.setUint32(24, 44100, true);
      view.setUint32(28, 44100 * 4, true);
      view.setUint16(32, 4, true);
      view.setUint16(34, 16, true);
      // data sub-chunk
      this.writeUTFBytes(view, 36, "data");
      view.setUint32(40, interleaved.length * 2, true);
      // write the PCM samples
      var lng = interleaved.length;
      var index = 44;
      var volume = 1;
      for (var i = 0; i < lng; i++) {
        view.setInt16(index, interleaved[i] * (32767 * volume), true);
        index += 2;
      }
      return view;
    },

    interleave: function (leftChannel, rightChannel) {
      var length = leftChannel.length + rightChannel.length;
      var result = new Float32Array(length);
      var inputIndex = 0;
      for (var index = 0; index < length; ) {
        result[index++] = leftChannel[inputIndex];
        result[index++] = rightChannel[inputIndex];
        inputIndex++;
      }
      return result;
    },

    writeUTFBytes: function (view, offset, string) {
      var lng = string.length;
      for (var i = 0; i < lng; i++) {
        view.setUint8(offset + i, string.charCodeAt(i));
      }
    },

    //4-2. array buffer 형식의 데이터를 Blob으로 변환하여 multipart로 전송.
    writeFile: function (dataToDownload) {
      var type = "application/octet-stream";
      if (p5.prototype._isSafari()) {
        type = "text/plain";
      }
      var blob = new Blob(dataToDownload, {
        type: type,
      });

      const formData = new FormData();
      formData.append("file", blob);
      formData.append("code", this.language);

      const headers = { "Content-Type": "multipart/form-data" };

      axios
        .post(
          "https://ssokssok.site/card-api/stt/convert",
          formData,
          headers
        )
        .then((res) => {
          const cards = this.playingCards;
          const idx = this.quizIdx;
          let translatedWord = res.data.object;

          if (translatedWord == null) {
            this.$store.dispatch(
              "CardGameStore/fetchAlertMessage",
              "소리를 잘 못들었어요ㅠㅠ"
            );
            this.$emit("is_loading", false);
            this.$emit("is_flipped", true);
          } else {
            if (this.language === "en-US" || this.language === "vi-VN" || this.language === "fr-FR" || this.language === "es-ES") {
              translatedWord = translatedWord.toLowerCase();
            }
            if (translatedWord === cards[idx].word) {
              this.$store.dispatch(
                "CardGameStore/fetchAlertMessage",
                "정답입니다!"
              );
              this.$emit("audioResult", true);
              this.$emit("is_loading", false);
              this.$emit("is_flipped", true);
            } else {
              this.$store.dispatch(
                "CardGameStore/fetchAlertMessage",
                "틀렸어요ㅠㅠ"
              );
              this.$emit("is_loading", false);
              this.$emit("is_flipped", true);
            }
          }
        })
        .catch((err) => console.log(err));
    },
  },
};
</script>

<style lang="scss" scoped>
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
  font-family: "Open Sans", Helvetica, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
}

.center {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
}
#audio-record {
  position: absolute;
  left: -999px;
}
#audio-record:checked + label {
  /*animation: loadingCircle 2s infinite 0s ease-in-out both,
		rotateCircle  2s infinite 0s ease-in-out ;*/
  .circle {
    animation: circle 5s linear infinite;
  }
  .icone {
    animation: icone 1s alternate 0s both;
  }
  .dots div {
    animation: bubling 1s infinite 1s ease-in-out alternate;
  }
  .dots div:nth-child(1) {
    animation-delay: 1s;
  }
  .dots div:nth-child(2) {
    animation-delay: 1.2s;
  }
  .dots div:nth-child(3) {
    animation-delay: 1.4s;
  }
}

.circle {
  fill: none;
  stroke: #fff;
  stroke-width: 3;
  stroke-linecap: round;
  transform-origin: 50% 50%;
}
label {
  display: block;
  width: 100px;
  height: 100px;
  border-radius: 50%;
  cursor: pointer;
}
.icone {
  position: absolute;
  top: calc(50% - 35px);
  left: calc(50% - 12.5px);
}
.dots {
  position: absolute;
  top: 45%;
  left: 30%;
}
.dots div {
  width: 8px;
  height: 8px;
  background: #fff;
  border-radius: 50%;
  display: inline-block;
  margin: 2px;
  opacity: 0;
}
.icone div:nth-child(1) {
  width: 25px;
  height: 50px;
  background: #fff;
  border-radius: 50px;
}
.icone div:nth-child(1):before {
  content: "";
  display: block;
  width: 6px;
  height: 15px;
  background: #684595;
  border-radius: 50px;
  position: absolute;
  left: 10px;
  top: 10px;
}
.icone div:nth-child(2) {
  width: 3px;
  height: 10px;
  background: #fff;
  margin-left: 11px;
}
.icone div:nth-child(3) {
  width: 25px;
  height: 3px;
  background: #fff;
  border-radius: 50px;
}
@keyframes rotateCircle {
  0% {
  }
  100% {
    transform: rotate(360deg);
  }
}
@keyframes bubling {
  0% {
    opacity: 1;
    transform: translateY(5px);
  }
  50% {
    transform: translateY(-5px);
  }
  100% {
    opacity: 1;
    transform: translateY(5px);
  }
}
@keyframes icone {
  0% {
    opacity: 1;
    transform: scale(1);
  }
  50% {
    transform: scale(1.2);
    opacity: 1;
  }
  100% {
    opacity: 0;
    transform: scale(0.1);
  }
}
@keyframes loadingCircle {
  0% {
    border-top: 1px solid transparent;
    border-right: 2px solid transparent;
    border-bottom: 2px solid transparent;
    border-left: 2px solid transparent;
  }
  25% {
    border-top: 2px solid #fff;
    border-right: 1px solid transparent;
    border-bottom: 2px solid transparent;
    border-left: 2px solid transparent;
  }
  50% {
    border-top: 2px solid #fff;
    border-right: 2px solid #fff;
    border-bottom: 1px solid transparent;
    border-left: 2px solid transparent;
  }
  75% {
    border-top: 2px solid #fff;
    border-right: 2px solid #fff;
    border-bottom: 2px solid #fff;
    border-left: 1px solid transparent;
  }
  100% {
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
