const SmartCameraStore = {
  namespaced: true,
  state: {
    score: 0,
    category: "",
    is_correct: false,
    is_done: false,
  },
  getters: {
    getScore(state) {
      return state.score
    },
    getCategory(state) {
      return state.category
    },
    getIsCorrect(state) {
      return state.is_correct
    },
    getIsDone(state) {
      return state.is_done
    },
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
      // console.log(state.is_done)
    },
    INITIALIZE_INFO: function (state) {
      state.score= 0;
      state.category= "";
      state.is_correct= false;
      state.is_done= false;
    }
  },
  actions: {
    setInfo({ commit }, data) {
      commit("SET_INFO", data)
    },
    initializeInfo({commit}){
      commit("INITIALIZE_INFO")
    }
  },
};

export default SmartCameraStore;
