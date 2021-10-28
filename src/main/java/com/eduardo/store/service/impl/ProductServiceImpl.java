package com.eduardo.store.service.impl;

import com.eduardo.store.dto.ProductDTO;
import com.eduardo.store.dto.SupplierDTO;
import com.eduardo.store.enums.ProductStateEnum;
import com.eduardo.store.model.Product;
import com.eduardo.store.model.ProductSupplier;
import com.eduardo.store.model.Supplier;
import com.eduardo.store.repo.ProductRepository;
import com.eduardo.store.repo.ProductSupplierRepository;
import com.eduardo.store.repo.SupplierRepository;
import com.eduardo.store.service.IProductService;
import com.eduardo.store.service.ISupplierService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
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
    private SupplierRepository supplierRepository;

    @Autowired
    private ISupplierService supplierService;

    @Autowired
    private ProductSupplierRepository productSupplierRepository;

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
        if(!exists(product)) {
            Date localDate = Date.from(LocalDate.now().atStartOfDay(ZoneId.of("GMT+01:00")).toInstant());
            product.setCreationDate(localDate);
            return convertToDto(productRepository.save(convertToPojo(product)));
        }
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

    public ProductDTO addSupplier(SupplierDTO supplierDTO, Long itemCode) {
        if(existsItemCode(itemCode) &&
                (supplierRepository.findBySupplierCode(supplierDTO.getSupplierCode()).isEmpty())) {
            Product product = productRepository.findByItemCode(itemCode).get(0);
            Supplier supplier = supplierService.convertToPojo(supplierDTO);
            supplierRepository.save(supplier);
            ProductSupplier productSupplier = new ProductSupplier(null, product, supplier);
            productSupplierRepository.save(productSupplier);
            return convertToDto(productRepository.findByItemCode(itemCode).get(0));
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
