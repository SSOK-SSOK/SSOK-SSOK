import  "@/store/p5.sound.js"
import p5 from 'p5';
let mic, recorder, soundFile;

let state = 0;

export function setup() {

  mic = new p5.AudioIn();
  mic.start();
  recorder = new p5.SoundRecorder();
  recorder.setInput(mic);

  soundFile = new p5.SoundFile();
}

export function mousePressed() {
  if (state === 0 && mic.enabled) {
    recorder.record(soundFile);

    state++;
  } else if (state === 1) {
    recorder.stop();

    state++;
  } else if (state === 2) {
    soundFile.play();
    saveSound(soundFile, '../assets/mySound.wav'); // 파일 저장하기
    state++;
  }
}
