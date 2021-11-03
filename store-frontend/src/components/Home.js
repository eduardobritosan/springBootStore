import React, {useState, useEffect} from "react";

import ProductService from "../services/product.service";

const Home = () => {
  const [content, setContent] = useState([]);

  useEffect(() => {
    ProductService.getPublicContent().then(
      (response) => {
        setContent(response.data.map(({productCode, productDescription, price, state,
          creationDate, creator, suppliers, priceReductions}) => (
          <tr key={productCode}>{productDescription}, {price}, {state}, {new Date(creationDate).toLocaleDateString()}, {creator}</tr>
        )));
      }
    );
  }, []);

  return (
    <div className="container">
      <header className="jumbotron">
        <>
        </>
        <table>{content}</table>
      </header>
    </div>
  );
};

export default Home;