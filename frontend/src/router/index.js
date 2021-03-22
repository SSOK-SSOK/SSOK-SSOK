import Vue from "vue";
import VueRouter from "vue-router";
import WelcomePage from "@/views/WelcomePage.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "WelcomePage",
    component: WelcomePage,
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
