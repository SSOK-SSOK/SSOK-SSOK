import axios from "axios";

const SERVER_URL = "http://localhost:8080";

function googleLogin(user, success, fail) {
  axios.post(`${SERVER_URL}/login`, user).then(success).catch(fail);
}

export default googleLogin;
