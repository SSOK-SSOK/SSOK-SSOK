import axios from "axios";

const CardGameStore = {
  namespaced: true,
  state: {
    playingCards: [],
    language: "",
    alertMessage: "",
    alertDialog: false,
  },
  getters: {},
  mutations: {
    SET_CARDS(state, payload) {
      state.playingCards = payload;
    },
    SET_LANGUAGE(state, data) {
      state.language = data;
    },
    SET_ALERT_MESSAGE(state, msg) {
      state.alertDialog = true;
      state.alertMessage = msg;
    },
    CANCEL_ALERT_MESSAGE: function (state) {
      state.alertDialog = false;
    },
  },
  actions: {
    fetchCards({ commit }, data) {
      const idx = data[0];
      const lan = data[1];
      axios
        .get(`https://ssokssok.site/card-api/card/${idx}/${lan}`)
        .then((res) => {
          commit("SET_CARDS", res.data.object);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    fetchLanguage({ commit }, data) {
      commit("SET_LANGUAGE", data);
    },
    fetchAlertMessage({ commit }, msg) {
      commit("SET_ALERT_MESSAGE", msg);
      setTimeout(function () {
        commit("CANCEL_ALERT_MESSAGE");
      }, 2000);
    },
  },
};

export default CardGameStore;
