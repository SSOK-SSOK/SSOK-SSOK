import Vue from "vue";
import Vuex from "vuex";
import createPersistedState from "vuex-persistedstate";
import UserStore from "@/store/modules/UserStore.js";
import SmartCameraStore from "@/store/modules/SmartCameraStore.js";
Vue.use(Vuex);

export default new Vuex.Store({
  modules: {
    UserStore: UserStore,
    SmartCameraStore: SmartCameraStore,
  },
  plugins: [
    createPersistedState({
      paths: ["UserStore"],
      paths: ["SmartCameraStore"],
    }),
  ],
});
