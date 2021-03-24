import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import vuetify from "./plugins/vuetify";
import GAuth from "vue-google-oauth2";

Vue.use(GAuth, {
  clientId:
    "943641916006-me9dbk59g6gkdiha6oke6cjl2g6fjkap.apps.googleusercontent.com",
  scope: "profile email",
  prompt: "select_account",
});

Vue.config.productionTip = false;

new Vue({
  router,
  store,
  vuetify,
  render: (h) => h(App),
}).$mount("#app");
