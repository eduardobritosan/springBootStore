import React, {useState, useEffect} from 'react'
import {Modal} from 'react-bootstrap';
import ProductService from "../services/product.service";


const Table = () => {
  const [products, setProducts] = useState([]);
  const [modalInfo, setModalInfo] = useState([]);
  const [showModal, setShowModal] = useState([]);

  const [show, setShow] = useState(false);
  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);


  useEffect(() => {
    getData()
  }, [])

  const getData = async () => {
    const response = await ProductService.getPublicContent()
    setProducts(response.data)
  }

  const deactivate = (productCode) => {

    ProductService.deactivate({productCode}).then(res => {
      getData()
    })
  }

  const details = (productCode) => {
    ProductService.getProductByCode(productCode).then(res => {
      setModalInfo(res.data);
      toggleTrueFalse();
    })
  }

  const toggleTrueFalse = () => {
    setShowModal(handleShow);
  }

  const ModalContent = () => {
    return (
      <Modal show={show} onHide={handleClose}>
        <Modal.Header closeButton>
          <Modal.Title>{modalInfo[0].productDescription}</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <h4>Suppliers</h4>
          <table id='product'>
            <thead>
              <tr>{renderSupplierHeader()}</tr>
            </thead>
            <tbody>
              {renderSuppliers(modalInfo[0].suppliers)}
            </tbody>
          </table>
          <h4>Price reductions</h4>
          <table id='product'>
            <thead>
              <tr>{renderPriceRedHeader()}</tr>
            </thead>
            <tbody>
              {renderPriceRed(modalInfo[0].priceReductions)}
            </tbody>
          </table>
        </Modal.Body>
        <Modal.Footer>
        </Modal.Footer>
      </Modal >
    )
  }

  const renderSupplierHeader = () => {
    let headerElement = ['name', 'country']

    return headerElement.map((key, index) => {
      return <th key={index}>{key.toUpperCase()}</th>
    })
  }

  const renderPriceRedHeader = () => {
    let headerElement = ['new price', 'start date', 'end date']

    return headerElement.map((key, index) => {
      return <th key={index}>{key.toUpperCase()}</th>
    })
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
            <button className='button' onClick={() => details(productCode)} >Details</button>
          </td>
          <td className='opration'>
            <button className='button' onClick={() => deactivate(productCode)} >Deactivate</button>
          </td>
          {show ? <ModalContent /> : null}
        </tr >
      )
    })
  }

  const renderSuppliers = (suppliers) => {
    return suppliers && suppliers.map(element => {
      return (
        <tr key={element.supplierDTO.supplierCode}>
          <td>{element.supplierDTO.name}</td>
          <td>{element.supplierDTO.country}</td>
        </tr>
      )
    });
  }

  const renderPriceRed = (priceReductions) => {
    return priceReductions && priceReductions.map(element => {
      return (
        <tr key={element.priceReductionCode}>
          <td>{element.newPrice}</td>
          <td>{new Date(element.startDate).toLocaleDateString()}</td>
          <td>{new Date(element.endDate).toLocaleDateString()}</td>
        </tr>
      )
    });
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
      </table>
    </>
  )
}


export default Table