package com.eduardo.store;

import com.eduardo.store.dto.ProductDTO;
import com.eduardo.store.enums.ProductStateEnum;
import com.eduardo.store.model.Product;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductDtoUnitTest {
    private ModelMapper modelMapper = new ModelMapper();

    @Test
    void whenConvertProductEntityToProductDTO_thenCorrect() {
        Product product = new Product(1L, 999L, "Product 1", 5D,
                ProductStateEnum.ACTIVE, new Date(0), "Pepe", new ArrayList<>());

        ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);
        assertEquals(productDTO.getIdProduct(), product.getIdProduct());
        assertEquals(productDTO.getItemCode(), product.getItemCode());
        assertEquals(productDTO.getProductDescription(), product.getProductDescription());
        assertEquals(productDTO.getPrice(), product.getPrice());
        assertEquals(productDTO.getCreator(), product.getCreator());
        assertEquals(productDTO.getCreationDate(), product.getCreationDate());
    }

    @Test
    void whenConvertProductDTOToProductEntity_thenCorrect() {
        ProductDTO productDTO = new ProductDTO(1L, 999L, "Product 1", 5D,
                ProductStateEnum.ACTIVE, new Date(0), "Pepe", new ArrayList<>());

        Product product = modelMapper.map(productDTO, Product.class);
        assertEquals(product.getIdProduct(), product.getIdProduct());
        assertEquals(product.getItemCode(), product.getItemCode());
        assertEquals(product.getProductDescription(), product.getProductDescription());
        assertEquals(product.getPrice(), product.getPrice());
        assertEquals(product.getCreator(), product.getCreator());
        assertEquals(product.getCreationDate(), product.getCreationDate());
    }


}
