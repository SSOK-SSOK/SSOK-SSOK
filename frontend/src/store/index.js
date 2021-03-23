import Vue from "vue";
import Vuex from "vuex";
// import { googleLogin } from "@/api/account.js";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {},
  mutations: {},
  actions: {
    //   async GOOGLE_LOGIN(context, payload) {
    //     var oauthResult = false;
    //     await googleLogin(
    //       payload, //user정보
    //       (response) => {
    //         localStorage.setItem("auth-token", response.data["auth-token"]);
    //         setAuthTokenToHeader(response.data["auth-token"]);
    //         context.dispatch("GET_MEMBER_INFO", response.data["auth-token"]);
    //         oauthResult = true;
    //       },
    //       (error) => {
    //         console.log("구글 로그인 실패");
    //         oauthResult = false;
    //       }
    //     );
    //     return oauthResult;
    //   },
  },
  modules: {},
});
