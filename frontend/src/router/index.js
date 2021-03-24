import Vue from "vue";
import VueRouter from "vue-router";
import MainPage from "@/views/MainPage.vue";
import SmartCamera from "@/views/SmartCamera.vue";
import WelcomePage from "@/views/WelcomePage.vue";

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
];

const router = new VueRouter({
  mode: "history",
  routes,
});

export default router;
