import React, {useState} from 'react'
import {Col, Form, Modal, Row, Button} from 'react-bootstrap';
import ProductService from "../services/product.service";

const PriceReductionModalContent = (props) => {

  const [priceReduction, setPriceReduction] = useState({});

  const handleInputChange = (event) => {
    setPriceReduction((prevState) => ({
      ...prevState,
      [event.target.name]: event.target.value
    }));
  }

  const handleSubmit = (event) => {
    ProductService.addPriceReduction(props.productCode, priceReduction);
  }

  return (
    <Modal {...props} dialogClassName="my-modal">
      <Modal.Header closeButton>
        <Modal.Title>{props.productDescription}</Modal.Title>
      </Modal.Header>
      <Modal.Body>
        <Form onSubmit={handleSubmit}>
          <Row>
            <Col>
              <Form.Label>Code</Form.Label>
              <Form.Control type="text" name="priceReductionCode" onChange={handleInputChange} ></Form.Control>
            </Col>
            <Col>
              <Form.Label>Name</Form.Label>
              <Form.Control type="text" name="name" onChange={handleInputChange} ></Form.Control>
            </Col>
            <Col>
              <Form.Label>Country</Form.Label>
              <Form.Control type="text" name="country" onChange={handleInputChange} ></Form.Control>
            </Col>
          </Row>
          <Row>
            <Button type="submit" className="mb-2">
              Submit
            </Button>
          </Row>
        </Form>
      </Modal.Body>
      <Modal.Footer>
      </Modal.Footer>
    </Modal >
  )
}

export default PriceReductionModalContent;