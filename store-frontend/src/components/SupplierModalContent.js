import React, {useRef} from 'react'
import {Col, Form, Modal, Row, Button} from 'react-bootstrap';
import ProductService from "../services/product.service";

const SupplierModalContent = (props) => {

  const supplierCode = useRef(null);
  const name = useRef(null);
  const country = useRef(null);

  const handleInputChange = (event) => {
    event.preventDefault();
  }

  const handleSubmit = (event) => {
    const newSupplier = {
      supplierCode: supplierCode.current.value,
      name: name.current.value,
      country: country.current.value,
    }
    ProductService.addSupplier(props.productCode, newSupplier);
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
              <Form.Label>Supplier code</Form.Label>
              <Form.Control type="text" ref={supplierCode} onChange={handleInputChange} ></Form.Control>
            </Col>
            <Col>
              <Form.Label>Name</Form.Label>
              <Form.Control type="text" ref={name} onChange={handleInputChange} ></Form.Control>
            </Col>
            <Col>
              <Form.Label>Country</Form.Label>
              <Form.Control type="text" ref={country} onChange={handleInputChange} ></Form.Control>
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

export default SupplierModalContent;