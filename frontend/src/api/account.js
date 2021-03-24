import { ACCESS_TOKEN } from "@/config/index.js";

// function getCurrentUser() {
//   if (!localStorage.getItem(ACCESS_TOKEN)) {
//     return Promise.reject("토큰이 없습니다.");
//   }
// }

//OAUTH2REDIRECTHANDLER
function oauth() {
  function getUrlParameter(name) {
    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)");
    var results = regex.exec(this.props.location.search);

    return results === null
      ? ""
      : decodeURIComponent(results[1].replace(/\+/g, " "));
  }

  const token = getUrlParameter("token");
  if (token) {
    localStorage.setItem(ACCESS_TOKEN, token);
    this.$router.push({ name: "MainPage" });
  }
}

// async function findByToken(token, success, fail) {
//   axios.defaults.headers["auth-token"] = token;
//   // 주소 바꿔주기
//   await axios.get("account/info").then(success).catch(fail);
// }

// function googleLogin(user, success, fail) {
//   axios
//     .post(`${SERVER_URL}/oauth2/authorization/google`, user)
//     .then(success)
//     .catch(fail);
// }

export { oauth };
