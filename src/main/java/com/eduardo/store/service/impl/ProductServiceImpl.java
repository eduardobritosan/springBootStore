package com.eduardo.store.service.impl;

import com.eduardo.store.dto.ProductDTO;
import com.eduardo.store.enums.ProductStateEnum;
import com.eduardo.store.model.Product;
import com.eduardo.store.repo.ProductRepository;
import com.eduardo.store.service.IProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.stream.Collectors;

@Transactional
@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<ProductDTO> findAll() {
        return productRepository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public List<ProductDTO> findByItemCode(Long itemCode) {
        return productRepository.findByItemCode(itemCode).stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public List<ProductDTO> findByState(String state) {
        return productRepository.findByState(ProductStateEnum.valueOf(state.toUpperCase(Locale.ROOT))).
                stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public ProductDTO save(ProductDTO product) {
        if(!exists(product))
            return convertToDto(productRepository.save(convertToPojo(product)));
        return null;
    }

    public void deleteByItemCode(Long itemCode) {
        productRepository.deleteByItemCode(itemCode);
    }

    public ProductDTO deactivate(Long itemCode) {
        if(existsItemCode(itemCode)) {
            ProductDTO foundProductDTO = findByItemCode(itemCode).get(0);
            foundProductDTO.setState(ProductStateEnum.DISCONTINUED);
            update(foundProductDTO, itemCode);
            return foundProductDTO;
        }
        return null;
    }

    public ProductDTO update(ProductDTO productDTO, Long itemCode) {
        if (!Objects.equals(productDTO.getItemCode(), itemCode)) {
            throw new IllegalArgumentException();
        }
        try{
            if(exists(productDTO)) {
                Product foundProduct = productRepository.findByItemCode(itemCode).get(0);
                BeanUtils.copyProperties(productDTO, foundProduct, "idProduct", "suppliers", "itemCode");
                productRepository.save(foundProduct);
                return convertToDto(foundProduct);
            }
        } catch(EntityExistsException | EntityNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private ProductDTO convertToDto(Product product) {
        return modelMapper.map(product, ProductDTO.class);
    }

    private Product convertToPojo(ProductDTO productDTO) {
        return modelMapper.map(productDTO, Product.class);
    }

    private boolean exists(ProductDTO product) {
        return existsItemCode(product.getItemCode());
    }

    private boolean existsItemCode(Long itemCode) {
        return findByItemCode(itemCode).size() == 1;
    }
}
