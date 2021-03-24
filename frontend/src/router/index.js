import Vue from "vue";
import VueRouter from "vue-router";
import MainPage from "@/views/MainPage.vue";
import SmartCamera from "@/views/SmartCamera.vue";
<<<<<<< HEAD
import WelcomePage from "@/views/WelcomePage.vue";
=======
import WelcomPage from "@/views/WelcomePage.vue";
>>>>>>> ef039cc176d0ebb03a02b6e77739610c950c4852

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "WelcomePage",
<<<<<<< HEAD
    component: WelcomePage,
=======
    component: WelcomPage,
>>>>>>> ef039cc176d0ebb03a02b6e77739610c950c4852
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
  routes,
});

export default router;
