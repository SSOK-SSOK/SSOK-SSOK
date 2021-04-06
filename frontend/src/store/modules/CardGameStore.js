import axios from "axios";

const CardGameStore = {
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
    },
  },
  actions: {
    fetchCards({ commit }, data) {
      const idx = data[0];
      const lan = data[1];
      axios.get(`https://j4a201.p.ssafy.io/card-api/card/${idx}/${lan}`)
        .then((res) => {
          console.log(res);
          commit("SET_CARDS", res.data.object)
        })
        .catch((err) => {
          console.log(err)
        })
    },
    fetchLanguage({ commit }, data) {
      commit("SET_LANGUAGE", data)
    },
  },
};

export default CardGameStore;
