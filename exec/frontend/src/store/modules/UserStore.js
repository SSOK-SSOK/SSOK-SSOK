import axios from "axios";
import { API_BASE_URL, ACCESS_TOKEN } from "@/config/index.js";

const UserStore = {
  namespaced: true,
  state: {
    authenticated: false,
    currentUser: null,
    userName: null,
  },
  mutations: {
    SET_USER(state, payload) {
      state.authenticated = true;
      state.currentUser = payload;
      state.userName = payload.data.name;
    },
    LOGOUT(state) {
      state.authenticated = false;
      state.currentUser = null;
    },
  },
  getters: {
    getCheckLogin(state) {
      return state.authenticated;
    },
  },
  actions: {
    fetchUser({ commit }) {
      axios({
        url: "https://ssokssok.site/api" + "/user/me",
        method: "GET",
        headers: {
          Authorization: "Bearer " + localStorage.getItem(ACCESS_TOKEN),
        },
      })
        .then((res) => {
          commit("SET_USER", res);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    logout({ commit }) {
      localStorage.removeItem(ACCESS_TOKEN);
      commit("LOGOUT");
    },
  },
};

export default UserStore;
