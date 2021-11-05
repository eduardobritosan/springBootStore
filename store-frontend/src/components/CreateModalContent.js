import React, {useRef} from 'react'
import {Col, Form, Modal, Row, Button} from 'react-bootstrap';
import ProductService from "../services/product.service";

const CreateModalContent = (props) => {

  const productDescription = useRef(null);
  const price = useRef(null);
  const productCode = useRef(null);
  const creator = useRef(null);
  const state = useRef(null);
  const creationDate = useRef(null);

  const handleSubmit = (event) => {
    const newProduct = {
      productCode: productCode.current.value,
      productDescription: productDescription.current.value,
      price: price.current.value,
      state: state.current.value,
      creationDate: Date.parse(creationDate.current.value),
      creator: creator.current.value
    }
    ProductService.createProduct(newProduct).then(() => {
      props.getData();
    });
  }

  const handleInputChange = (event) => {
    event.preventDefault();
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

export default CreateModalContent;


const toDateInputValue = ((date) => {
  var local = new Date(date);
  local.setMinutes(date.getMinutes() - date.getTimezoneOffset());
  return local.toJSON().slice(0, 10);
});