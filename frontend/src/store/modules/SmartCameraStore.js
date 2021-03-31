const SmartCameraStore = {
  namespaced: true,
  state: {
    score: 0,
    category: "",
    is_correct: false,
    is_done: false,
  },
  mutations: {
    SET_INFO: function (state, data) {
      var info = data.info;
      state.is_correct = data.is_correct;

      if (typeof info === "number") {
        state.score = info;
      } else {
        state.category = info;
      }
      state.is_done = true;
      console.log(state.is_done)
    },
  },
  getters: {
    getCheckLogin(state) {
      return state.authenticated;
    },
  },
  actions: {
    setInfo({ commit }, data) {
      commit("SET_INFO", data)
    },
  },
};

export default SmartCameraStore;
