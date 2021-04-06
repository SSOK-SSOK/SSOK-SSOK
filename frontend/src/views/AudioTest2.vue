<template>
  <div>
    <v-btn @click="load">로드</v-btn>
    <v-btn @click="record">녹음</v-btn>
    <v-btn @click="end">멈춤</v-btn>
    <v-btn @click="store">저장</v-btn>
  </div>
</template>

<script>
import axios from 'axios'
import  "@/store/p5.sound.js"
import p5 from 'p5';
let mic, recorder, soundFile;

export default {
  name: "AudioTest2",
  data: function () {
    return {
      data: {},
    }
  },
  methods: {
    load: function () {
      mic = new p5.AudioIn();
      mic.start();
      recorder = new p5.SoundRecorder();
      recorder.setInput(mic);
      soundFile = new p5.SoundFile();
    },

    record: function () {
      if (mic.enabled) {
        let audio_p5 = new p5();
        audio_p5.getAudioContext().resume();
        recorder.record(soundFile);
      }
    },

    end: function () {
      recorder.stop();
    },

    store: function () {
      let myp5 = new p5();
      console.log(soundFile);
      myp5.saveSound(soundFile, './assets/mySound.wav');
    },

    writeFile: function (dataToDownload, filename, extension) {
      var type = 'application/octet-stream';
      if (p5.prototype._isSafari()) {
        type = 'text/plain';
      }
      var blob = new Blob(dataToDownload, {
        type: type
      });

      const formData = new FormData();
      formData.append('file', blob);

      const headers = {'Content-Type': 'multipart/form-data'}
      axios.post("https://j4a201.p.ssafy.io/card-api/file/upload", formData, headers)
        .then(res => {
          console.log(res)
        })
        .catch(err => console.log(err))
    },
    
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
      this.writeUTFBytes(view, 0, 'RIFF');
      view.setUint32(4, 36 + interleaved.length * 2, true);
      this.writeUTFBytes(view, 8, 'WAVE');
      // FMT sub-chunk
      this.writeUTFBytes(view, 12, 'fmt ');
      view.setUint32(16, 16, true);
      view.setUint16(20, 1, true);
      // stereo (2 channels)
      view.setUint16(22, 2, true);
      view.setUint32(24, 44100, true);
      view.setUint32(28, 44100 * 4, true);
      view.setUint16(32, 4, true);
      view.setUint16(34, 16, true);
      // data sub-chunk
      this.writeUTFBytes(view, 36, 'data');
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
      for (var index = 0; index < length;) {
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
  }
  }
}
</script>

<style>

</style>