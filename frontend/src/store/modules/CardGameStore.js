import axios from "axios";

const SmartCameraStore = {
  namespaced: true,
  state: {
    playingCards: [],
  },
  getters: {
  },
  mutations: {
    SET_CARDS (state, payload) {
      state.playingCards = payload; 
    },
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
  },
};

export default SmartCameraStore;
