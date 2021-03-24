import Vue from "vue";
import Vuex from "vuex";
import { API_BASE_URL, ACCESS_TOKEN } from "@/config/index.js";
import { createInstance } from "@/api/index.js";

const instance = createInstance();

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    authenticated: false,
    currentUser: null,
  },
  mutations: {
    SET_USER(state, payload) {
      state.authenticated = true;
      state.currentUser = payload;
    },
    LOGOUT(state) {
      state.authenticated = false;
      state.currentUser = null;
    },
  },
  actions: {
    fetchUser({ commit }) {
      console.log("펫치");
      if (localStorage.getItem(ACCESS_TOKEN)) {
        instance
          .get("/user/me")
          .then((res) => {
            commit(SET_USER, res);
          })
          .catch((err) => {
            console.log("currentUser 없음");
            console.log(err);
          });
      }
    },
    logout({ commit }) {
      localStorage.removeItem(ACCESS_TOKEN);
      commit(LOGOUT);
    },
  },
});
