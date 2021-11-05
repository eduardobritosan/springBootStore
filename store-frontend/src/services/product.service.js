import axios from "axios";
import authHeader from "./auth-header";

const API_URL = "http://localhost:8082/products";

const getPublicContent = () => {
  return axios.get(API_URL);
};

const getProductByCode = (productCode) => {
  return axios.get(API_URL + `?item-code=${productCode}`);
};

const deactivate = (productCode) => {
  return axios.patch(API_URL + `/${productCode.productCode}`, {}, {headers: authHeader()});
}

const createProduct = (newProduct) => {
  return axios.post(API_URL, newProduct, {headers: authHeader()});
}

const editProduct = (productCode, newProduct) => {
  return axios.put(API_URL + `/${productCode}`, newProduct, {headers: authHeader()});
}

const addSupplier = (productCode, newSupplier) => {
  return axios.put(API_URL + `/new-supplier/${productCode}`, newSupplier, {headers: authHeader()});
}

const ProductService = {
  getPublicContent,
  deactivate,
  getProductByCode,
  createProduct,
  editProduct,
  addSupplier
};

export default ProductService;