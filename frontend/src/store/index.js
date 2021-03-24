import axios from "axios";
import Vue from "vue";
import Vuex from "vuex";
import axios from "axios";
// import { findByToken, googleLogin } from "@/api/account.js";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    currentUser: JSON.parse(localStorage.getItem("currentUser")) || {},
  },
  mutations: {
    SET_USER(state, payload) {
      state.currentUser = payload;
    },
  },
  actions: {
    fetchUser({ commit }) {
      // withCredentials: true는 CORS 관련 문제를 해결하기 위해 추가하는 것 같다.
      axios
        .get("http://localhost:8000/sessions/me", { withCredentials: true })
        .then((response) => {
          commit(SET_USER, response.data);
          localStorage.setItem(
            "currentUser",
            JSON.stringify(this.state.currentUser)
          );
        })
        .catch(() => {
          commit(SET_USER, {});
        });
    },
    logout({ commit }) {
      commit(SET_USER, {});
      localStorage.setItem("currentUser", {});
      // document.cookie에 값을 할당하면, 브라우저는 해당 값으로 쿠키를 갱신한다.
      document.cookie =
        "backend-session" + "=; expires=Thu, 01 Jan 1970 00:00:01 GMT;";
    },
  },
  getters: {
    isAuthenticated: (state) => {
      return state.currentUser.toString().includes("name");
    },
  },
});

// export default new Vuex.Store({
//   state: {
//     authToken: null,
//     user: {},
//   },
//   mutations: {
//     GET_MEMBER_INFO(state, payload) {
//       state.authToken = payload.token;
//       state.user = payload.user;
//     },
//   },
//   actions: {
//     async getMemberInfo(context, token) {
//       await findByToken(
//         token,
//         (response) => {
//           let user = response.data.user;
//           context.commit("GET_MEMBER_INFO", { token, user });
//         },
//         (error) => {
//           console.log(error);
//         }
//       );
//     },
//     async googleOauth(context, payload) {
//       var oauthResult = false;
//       await googleLogin(
//         payload, //user정보
//         (res) => {
//           console.log(res);
//           localStorage.setItem("auth-token", response.data["auth-token"]);
//           axios.defaults.headers.common[
//             "auth-token"
//           ] = `${response.data["auth-token"]}`;
//           context.dispatch("getMemberInfo", response.data["auth-token"]);
//           oauthResult = true;
//         },
//         (err) => {
//           console.log(err, "구글 로그인 실패");
//           oauthResult = false;
//         }
//       );
//       return oauthResult;
//     },
//   },
//   modules: {},
// });
