import React, {useState, useEffect} from 'react'
import ProductService from "../services/product.service";


const Table = () => {
  const [products, setProducts] = useState([])

  useEffect(() => {
    getData()
  }, [])

  const getData = async () => {
    const response = await ProductService.getPublicContent()
    setProducts(response.data)
  }

  /*   const removeData = (id) => {
  
      axios.delete(`${URL}/${id}`).then(res => {
        const del = products.filter(products => id !== products.id)
        setProducts(del)
      })
    } */

  const renderHeader = () => {
    let headerElement = ['name', 'price', 'status',
      'created', 'creator', 'operation']

    return headerElement.map((key, index) => {
      return <th key={index}>{key.toUpperCase()}</th>
    })
  }

  const renderBody = () => {
    return products && products.map(({productCode, productDescription, price,
      state, creationDate, creator}) => {
      return (
        <tr key={productCode}>
          <td>{productDescription}</td>
          <td>{price}</td>
          <td>{state}</td>
          <td>{new Date(creationDate).toLocaleDateString()}</td>
          <td>{creator}</td>
          <td className='opration'>
            <button className='button' > Delete</button>
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
      </table>
    </>
  )
}


export default Table