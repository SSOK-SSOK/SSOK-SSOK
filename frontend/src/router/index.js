import Vue from "vue";
import VueRouter from "vue-router";
import MainPage from "@/views/MainPage.vue";
import SmartCamera from "@/views/SmartCamera.vue";
import WelcomePage from "@/views/WelcomePage.vue";
import CardGame from "@/views/CardGame.vue";
import PlayGame from "@/views/PlayGame.vue";
import AudioTest from "@/views/AudioTest.vue";
import Test from "@/views/Test.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "WelcomePage",
    component: WelcomePage,
  },
  {
    path: "/main",
    name: "MainPage",
    component: MainPage,
  },
  {
    path: "/smartcamera",
    name: "SmartCamera",
    component: SmartCamera,
  },
  {
    path: "/card",
    name: "CardGame",
    component: CardGame,
  },
  {
    path: "/game",
    name: "PlayGame",
    component: PlayGame,
  },
  {
    path: "/audiotest",
    name: "AudioTest",
    component: AudioTest,
  },
  {
    path: "/test",
    name: "Test",
    component: Test,
  },
];

const router = new VueRouter({
  mode: "history",
  routes,
});

export default router;
