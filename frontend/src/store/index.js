import axios from "axios";
import Vue from "vue";
import Vuex from "vuex";
import axios from "axios";
// import { findByToken, googleLogin } from "@/api/account.js";
import { ACCESS_TOKEN } from "@/config/index.js";
import { API_BASE_URL } from "../config";
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
      axios
        .get(`${API_BASE_URL}/user/me`)
        .then((res) => {
          commit(SET_USER, res);
        })
        .catch((err) => {
          console.log("currentUser 없음");
          console.log(err);
        });
    },
    logout({ commit }) {
      localStorage.removeItem(ACCESS_TOKEN);
      commit(LOGOUT);
    },
  },
  getters: {
    isAuthenticated: (state) => {
      return state.currentUser.toString().includes("name");
    },
  },
});
