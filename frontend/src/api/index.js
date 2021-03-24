import axios from 'axios';
import { API_BASE_URL } from '@/config/index.js';

const baseHeaders = new Headers({
  'Content-Type': 'application/json',
});



const customHeaders = {
  const get_token = localStorage.getItem(ACCESS_TOKEN);
  
  if (get_token) {
    return baseHeaders.append('Authorization', 'Bearer ' + localStorage.getItem(ACCESS_TOKEN);
  } else {

  }
}

function createInstance() {
  const instance = axios.create({
    baseURL: API_BASE_URL,
    headers: customHeaders,
  });
  return instance;
}

export { createInstance };
