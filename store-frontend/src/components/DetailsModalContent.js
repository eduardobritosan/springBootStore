import React, {useRef, useState} from 'react'
import {Col, Form, Modal, Row, Button} from 'react-bootstrap';
import ProductService from "../services/product.service";
import SupplierModalContent from './SupplierModalContent';

const DetailsModalContent = (props) => {

  const [show, setShow] = useState(false);
  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);
  const [, setShowModal] = useState([]);

  const toggleTrueFalse = () => {
    setShowModal(handleShow);
  }

  const handleInputChange = (event) => {
    props.setModalInfo((prevState) => ({...prevState, [event.target.name]: event.target.value}));
  }

  const handleSubmit = (event) => {
    ProductService.editProduct(props.modalInfo[0].productCode, props.modalInfo[0]).then(() => {
      props.getData();
    });
  }

  const addSupplier = () => {
    toggleTrueFalse();
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
    <Modal {...props} dialogClassName="my-modal">
      <Modal.Header closeButton>
        <Modal.Title>Edit product</Modal.Title>
      </Modal.Header>
      <Modal.Body>
        <Form onSubmit={handleSubmit}>
          <Row>
            <Col>
              <Form.Label>Code</Form.Label>
              <Form.Control type="text" readOnly name="productCode" value={props.modalInfo[0].productCode} onChange={handleInputChange} ></Form.Control>
            </Col>
            <Col>
              <Form.Label>Description</Form.Label>
              <Form.Control type="text" name="productDescription" defaultValue={props.modalInfo[0].productDescription} onChange={handleInputChange} ></Form.Control>
            </Col>
            <Col>
              <Form.Label>Price</Form.Label>
              <Form.Control type="text" name="price" defaultValue={props.modalInfo[0].price} onChange={handleInputChange} ></Form.Control>
            </Col>
          </Row>
          <br />
          <Row>
            <Col>
              <Form.Label>Status</Form.Label>
              <Form.Select aria-label="Product status select" name="state" defaultValue={props.modalInfo[0].state} >
                <option value="ACTIVE">ACTIVE</option>
                <option value="DISCONTINUED">DISCONTINUED</option>
              </Form.Select>
            </Col>
            <Col>
              <Form.Label>Creation date</Form.Label>
              <Form.Control type="date" name="creationDate" defaultValue={toDateInputValue(new Date(props.modalInfo[0].creationDate))} onChange={handleInputChange} ></Form.Control>
            </Col>
            <Col>
              <Form.Label>Creator</Form.Label>
              <Form.Control type="text" name="creator" defaultValue={props.modalInfo[0].creator} onChange={handleInputChange} ></Form.Control>
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
            {renderSuppliers(props.modalInfo[0].suppliers)}
          </tbody>
        </table>
        <br />
        <Button onClick={() => addSupplier(props.modalInfo[0].productCode)}> Add new supplier </Button>
        <hr />
        <h4>Price reductions</h4>
        <table id='product'>
          <thead>
            <tr>{renderPriceRedHeader()}</tr>
          </thead>
          <tbody>
            {renderPriceRed(props.modalInfo[0].priceReductions)}
          </tbody>
        </table>
      </Modal.Body>
      <Modal.Footer>
      </Modal.Footer>
      {show ? <SupplierModalContent onHide={handleClose} show={show} productCode={props.modalInfo[0].productCode} /> : null}
    </Modal >
  )
}

export default DetailsModalContent;

const toDateInputValue = ((date) => {
  var local = new Date(date);
  local.setMinutes(date.getMinutes() - date.getTimezoneOffset());
  return local.toJSON().slice(0, 10);
});