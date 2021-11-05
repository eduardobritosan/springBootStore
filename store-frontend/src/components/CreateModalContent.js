import React, {useState} from 'react'
import {Col, Form, Modal, Row, Button} from 'react-bootstrap';
import ProductService from "../services/product.service";

const CreateModalContent = (props) => {

  const [modalInfo, setModalInfo] = useState();

  const handleSubmit = (event) => {
    ProductService.createProduct(modalInfo).then(() => {
      props.getData();
    });
  }

  const handleInputChange = (event) => {
    setModalInfo((prevState) => ({
      ...prevState,
      [event.target.name]: event.target.value
    }));
  }

  return (
    <Modal {...props} dialogClassName="my-modal">
      <Modal.Header closeButton>
        <Modal.Title>Create product</Modal.Title>
      </Modal.Header>
      <Modal.Body>
        <Form onSubmit={handleSubmit}>
          <Row>
            <Col>
              <Form.Label>Code</Form.Label>
              <Form.Control type="text" name="productCode" onChange={handleInputChange} ></Form.Control>
            </Col>
            <Col>
              <Form.Label>Description</Form.Label>
              <Form.Control type="text" name="productDescription" onChange={handleInputChange} ></Form.Control>
            </Col>
            <Col>
              <Form.Label>Price</Form.Label>
              <Form.Control type="text" name="price" onChange={handleInputChange} ></Form.Control>
            </Col>
          </Row>
          <br />
          <Row>
            <Col>
              <Form.Label>Status</Form.Label>
              <Form.Select aria-label="Status select" defaultValue="ACTIVE" name="state" onChange={handleInputChange} >
                <option value="ACTIVE">ACTIVE</option>
                <option value="DISCONTINUED">DISCONTINUED</option>
              </Form.Select>
            </Col>
            <Col>
              <Form.Label>Creation date</Form.Label>
              <Form.Control type="date" name="creationDate" defaultValue={toDateInputValue(new Date())} onChange={handleInputChange} ></Form.Control>
            </Col>
            <Col>
              <Form.Label>Creator</Form.Label>
              <Form.Control type="text" name="creator" onChange={handleInputChange} ></Form.Control>
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

export default CreateModalContent;


const toDateInputValue = ((date) => {
  var local = new Date(date);
  local.setMinutes(date.getMinutes() - date.getTimezoneOffset());
  return local.toJSON().slice(0, 10);
});