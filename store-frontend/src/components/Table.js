import React, {useState, useEffect} from 'react'
import {Button} from 'react-bootstrap';
import ProductService from "../services/product.service";
import CreateModalContent from './CreateModalContent';
import DetailsModalContent from './DetailsModalContent'
import "bootstrap/dist/css/bootstrap.min.css";


const Table = () => {
  const [products, setProducts] = useState([]);
  const [selectedProduct, setSelectedProduct] = useState('');

  const [, setShowModal] = useState([]);
  const [, setCreateShowModal] = useState([]);

  const [createShow, setCreateShow] = useState(false);
  const handleCreateClose = () => setCreateShow(false);
  const handleCreateShow = () => setCreateShow(true);

  const [show, setShow] = useState(false);
  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);

  useEffect(() => {
    getData();
  }, [])

  const getData = async () => {
    const response = await ProductService.getPublicContent()
    setProducts(response.data);
  }

  const deactivate = (productCode) => {
    ProductService.deactivate({productCode}).then(res => {
      getData();
    })
  }

  const details = (productCode) => {
    setSelectedProduct(productCode);
    toggleTrueFalse();
  }

  const create = () => {
    toggleTrueFalseCreate();
  }

  const toggleTrueFalse = () => {
    setShowModal(handleShow);
  }

  const toggleTrueFalseCreate = () => {
    setCreateShowModal(handleCreateShow);
  }

  const renderHeader = () => {
    let headerElement = ['name', 'price', 'status',
      'created', 'creator', 'details', 'deactivate']

    return headerElement.map((key, index) => {
      return <th key={index}>{key.toUpperCase()}</th>
    })
  }

  const renderBody = () => {
    return products && products.map(({productCode, productDescription, price,
      state, creationDate, creator}) => {
      return (
        <tr key={productCode} >
          <td>{productDescription}</td>
          <td>{price}</td>
          <td>{state}</td>
          <td>{new Date(creationDate).toLocaleDateString()}</td>
          <td>{creator}</td>
          <td className='opration'>
            <Button variant="info" onClick={() => details(productCode)} >Details</Button>
          </td>
          <td className='opration'>
            <Button variant="danger" onClick={() => deactivate(productCode)} >Deactivate</Button>
          </td>
        </tr >
      )
    })
  }

  return (
    <>
      <h1 id='title'>Products</h1>
      <table id='product'>
        <thead>
          <tr>{renderHeader()}</tr>
        </thead>
        <tbody>
          {renderBody()}
        </tbody>
        <tfoot>
          <tr>
            <td>
              <Button className='Button' onClick={() => create()} >Create new product</Button>
            </td>
          </tr>
        </tfoot>
      </table>
      {show ? <DetailsModalContent show={show} onHide={handleClose} getData={getData} productCode={selectedProduct} /> : null}
      {createShow ? <CreateModalContent show={createShow} onHide={handleCreateClose} getData={getData} /> : null}
    </>
  )
}

export default Table