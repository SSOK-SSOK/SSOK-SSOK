const API_BASE_URL = "http://localhost:8080";
const ACCESS_TOKEN = "accessToken";
const OAUTH2_REDIRECT_URI = "http://localhost:8000/oauth2/redirect";
const GOOGLE_AUTH_URL =
  API_BASE_URL + "/oauth2/authorize/google?redirect_uri=" + OAUTH2_REDIRECT_URI;

export { API_BASE_URL, ACCESS_TOKEN, OAUTH2_REDIRECT_URI, GOOGLE_AUTH_URL };
