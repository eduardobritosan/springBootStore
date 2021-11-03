import axios from "axios";
import authHeader from "./auth-header";


const API_URL = "http://localhost:8082/products/";

const getPublicContent = () => {
  return axios.get(API_URL);
};

const deactivate = (productCode) => {
  return axios.patch(API_URL + `${productCode.productCode}`, {}, {headers: authHeader()})
}

const ProductService = {
  getPublicContent,
  deactivate
};

export default ProductService;