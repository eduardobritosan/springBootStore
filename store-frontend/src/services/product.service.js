import axios from "axios";

const API_URL = "http://localhost:8082/products";

const getPublicContent = () => {
  return axios.get(API_URL);
};

export default {
  getPublicContent,
};