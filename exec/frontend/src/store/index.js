import Vue from "vue";
import Vuex from "vuex";
import createPersistedState from "vuex-persistedstate";
import UserStore from "@/store/modules/UserStore.js";
import SmartCameraStore from "@/store/modules/SmartCameraStore.js";
import CardGameStore from "@/store/modules/CardGameStore.js";
Vue.use(Vuex);

export default new Vuex.Store({
  modules: {
    UserStore: UserStore,
    SmartCameraStore: SmartCameraStore,
    CardGameStore: CardGameStore,
  },
  plugins: [
    createPersistedState({
      paths: ["UserStore"],
      paths: ["SmartCameraStore"],
      paths: ["CardGameStore"],
    }),
  ],
});
