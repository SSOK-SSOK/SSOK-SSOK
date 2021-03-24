import axios from "axios";
import { API_BASE_URL, ACCESS_TOKEN } from "@/config/index.js";

const baseHeaders = new Headers({
  "Content-Type": "application/json",
});

function customHeaders() {
  if (localStorage.getItem(ACCESS_TOKEN)) {
    return baseHeaders.append(
      "Authorization",
      "Bearer " + localStorage.getItem(ACCESS_TOKEN)
    );
  } else {
    return baseHeaders;
  }
}

function createInstance() {
  const instance = axios.create({
    baseURL: API_BASE_URL,
    headers: customHeaders(),
  });
  return instance;
}

function createGoogleInstance() {
  const googleInstance = axios.create({
    baseURL: GOOGLE_AUTH_URL,
    headers: customHeaders(),
  });
  return instance;
}

export { createInstance, createGoogleInstance };
