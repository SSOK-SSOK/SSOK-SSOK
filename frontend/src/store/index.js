import Vue from "vue";
import Vuex from "vuex";
import createPersistedState from "vuex-persistedstate";
import UserStore from "@/store/modules/UserStore.js";
Vue.use(Vuex);

export default new Vuex.Store({
  modules: {
    UserStore: UserStore,
  },
  plugins: [
    createPersistedState({
      paths: ["UserStore"],
    }),
  ],
});
