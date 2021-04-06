import axios from "axios";

const SmartCameraStore = {
  namespaced: true,
  state: {
    playingCards: [],
    language: '',
  },
  getters: {
  },
  mutations: {
    SET_CARDS (state, payload) {
      state.playingCards = payload; 
    },
    SET_LANGUAGE (state, data) {
      state.language = data;
    }
  },
  actions: {
    fetchCards({ commit }, data) {
      axios.get(`https://j4a201.p.ssafy.io/card-api/card/${data}`)
        .then((res) => {
          commit("SET_CARDS", res.data.object)
        })
        .catch((err) => {
          console.log(err)
        })
    },
    fetchLanguage({ commit }, data) {
      commit("SET_LANGUAGE", data)
    }
  },
};

export default SmartCameraStore;
