import React, {useState, useEffect, useRef} from 'react'
import {Col, Form, Modal, Row, Button} from 'react-bootstrap';
import ProductService from "../services/product.service";
import "bootstrap/dist/css/bootstrap.min.css";


const Table = () => {
  const [products, setProducts] = useState([]);
  const [modalInfo, setModalInfo] = useState([]);

  const [showModal, setShowModal] = useState([]);
  const [createShowModal, setCreateShowModal] = useState([]);

  const [createShow, setCreateShow] = useState(false);
  const handleCreateClose = () => setCreateShow(false);
  const handleCreateShow = () => setCreateShow(true);

  const [show, setShow] = useState(false);
  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);

  const productDescription = useRef(null);
  const price = useRef(null);
  const productCode = useRef(null);
  const creator = useRef(null);
  const state = useRef(null);
  const creationDate = useRef(null);

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

  const create = () => {
    toggleTrueFalseCreate();
  }

  const toggleTrueFalse = () => {
    setShowModal(handleShow);
  }

  const toggleTrueFalseCreate = () => {
    setCreateShowModal(handleCreateShow);
  }

  const handleInputChange = (event) => {

  }

  const handleSubmit = (event) => {
    event.preventDefault();
    const newProduct = {
      productCode: productCode.current.value,
      productDescription: productDescription.current.value,
      price: price.current.value,
      state: state.current.value,
      creationDate: Date.parse(creationDate.current.value),
      creator: creator.current.value
    }
    ProductService.editProduct(newProduct.productCode, newProduct).then(() => {
      getData();
    });
    handleClose();
  }

  const handleCreateSubmit = (event) => {
    event.preventDefault();
    const newProduct = {
      productCode: productCode.current.value,
      productDescription: productDescription.current.value,
      price: price.current.value,
      state: state.current.value,
      creationDate: Date.parse(creationDate.current.value),
      creator: creator.current.value
    }
    ProductService.createProduct(newProduct).then(() => {
      getData();
    });
    handleCreateClose();
  }


  const CreateModalContent = () => {
    return (
      <Modal show={createShow} onHide={handleCreateClose} dialogClassName="my-modal">
        <Modal.Header closeButton>
          <Modal.Title>Create product</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <Form onSubmit={handleCreateSubmit}>
            <Row>
              <Col>
                <Form.Label>Product code</Form.Label>
                <Form.Control type="text" ref={productCode} onChange={handleInputChange} ></Form.Control>
              </Col>
              <Col>
                <Form.Label>Product description</Form.Label>
                <Form.Control type="text" ref={productDescription} onChange={handleInputChange} ></Form.Control>
              </Col>
              <Col>
                <Form.Label>Price</Form.Label>
                <Form.Control type="text" ref={price} onChange={handleInputChange} ></Form.Control>
              </Col>
            </Row>
            <br />
            <Row>
              <Col>
                <Form.Label>Status</Form.Label>
                <Form.Select aria-label="Product status select" defaultValue="ACTIVE" ref={state}>
                  <option value="ACTIVE">ACTIVE</option>
                  <option value="DISCONTINUED">DISCONTINUED</option>
                </Form.Select>
              </Col>
              <Col>
                <Form.Label>Creation date</Form.Label>
                <Form.Control type="date" ref={creationDate} defaultValue={toDateInputValue(new Date())} onChange={handleInputChange} ></Form.Control>
              </Col>
              <Col>
                <Form.Label>Creator</Form.Label>
                <Form.Control type="text" ref={creator} onChange={handleInputChange} ></Form.Control>
              </Col>
            </Row>
            <br />
            <Button type="submit" className="mb-2">
              Submit
            </Button>
          </Form>
        </Modal.Body>
        <Modal.Footer>
        </Modal.Footer>
      </Modal >
    )
  }

  const DetailsModalContent = () => {
    return (
      <Modal show={show} onHide={handleClose} dialogClassName="my-modal">
        <Modal.Header closeButton>
          <Modal.Title>{modalInfo[0].productDescription}</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <Form onSubmit={handleSubmit}>
            <Row>
              <Col>
                <Form.Label>Product code</Form.Label>
                <Form.Control type="text" readOnly ref={productCode} value={modalInfo[0].productCode} onChange={handleInputChange} ></Form.Control>
              </Col>
              <Col>
                <Form.Label>Product description</Form.Label>
                <Form.Control type="text" ref={productDescription} defaultValue={modalInfo[0].productDescription} onChange={handleInputChange} ></Form.Control>
              </Col>
              <Col>
                <Form.Label>Price</Form.Label>
                <Form.Control type="text" ref={price} defaultValue={modalInfo[0].price} onChange={handleInputChange} ></Form.Control>
              </Col>
            </Row>
            <br />
            <Row>
              <Col>
                <Form.Label>Status</Form.Label>
                <Form.Select aria-label="Product status select" defaultValue={modalInfo[0].state} ref={state}>
                  <option value="ACTIVE">ACTIVE</option>
                  <option value="DISCONTINUED">DISCONTINUED</option>
                </Form.Select>
              </Col>
              <Col>
                <Form.Label>Creation date</Form.Label>
                <Form.Control type="date" defaultValue={toDateInputValue(new Date(modalInfo[0].creationDate))} ref={creationDate} onChange={handleInputChange} ></Form.Control>
              </Col>
              <Col>
                <Form.Label>Creator</Form.Label>
                <Form.Control type="text" defaultValue={modalInfo[0].creator} ref={creator} onChange={handleInputChange} ></Form.Control>
              </Col>
            </Row>
            <br />
            <Button type="submit" className="mb-2">
              Submit
            </Button>
          </Form>
          <hr />
          <h4>Suppliers</h4>
          <table id='product'>
            <thead>
              <tr>{renderSupplierHeader()}</tr>
            </thead>
            <tbody>
              {renderSuppliers(modalInfo[0].suppliers)}
            </tbody>
          </table>
          <hr />
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
          {show ? <DetailsModalContent /> : null}
          {createShow ? <CreateModalContent /> : null}
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

  const toDateInputValue = ((date) => {
    var local = new Date(date);
    local.setMinutes(date.getMinutes() - date.getTimezoneOffset());
    return local.toJSON().slice(0, 10);
  });

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
              <button className='button' onClick={() => create()} >Create new product</button>
            </td>
          </tr>
        </tfoot>
      </table>
    </>
  )
}

export default Table