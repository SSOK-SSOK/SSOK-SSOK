import Vue from "vue";
import VueRouter from "vue-router";
import MainPage from "@/views/MainPage.vue";
import SmartCameraGame from "@/views/SmartCameraGame.vue";
import WelcomePage from "@/views/WelcomePage.vue";
import SelectCardGame from "@/views/SelectCardGame.vue";
import PlayCardGame from "@/views/PlayCardGame.vue";
import AudioTest from "@/views/AudioTest.vue";

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
    path: "/smartcameragame",
    name: "SmartCameraGame",
    component: SmartCameraGame,
  },
  {
    path: "/selectcardgame",
    name: "SelectCardGame",
    component: SelectCardGame,
  },
  {
    path: "/playcardgame",
    name: "PlayCardGame",
    component: PlayCardGame,
  },
  {
    path: "/audiotest",
    name: "AudioTest",
    component: AudioTest,
  },
];

const router = new VueRouter({
  mode: "history",
  routes,
});

export default router;
