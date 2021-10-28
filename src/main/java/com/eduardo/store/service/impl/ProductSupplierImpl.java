package com.eduardo.store.service.impl;

import com.eduardo.store.dto.ProductSupplierDTO;
import com.eduardo.store.model.ProductSupplier;
import com.eduardo.store.repo.ProductSupplierRepository;
import com.eduardo.store.service.IProductSupplierService;
import com.eduardo.store.service.ISupplierService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class ProductSupplierImpl implements IProductSupplierService {

    @Autowired
    private ProductSupplierRepository productSupplierRepository;

    @Autowired
    private ISupplierService supplierService;

    @Autowired
    private ModelMapper modelMapper;

    public ProductSupplierDTO save(ProductSupplierDTO productSupplierDTO) {
        return convertToDto(productSupplierRepository.save(
                convertToPojo(productSupplierDTO)));
    }

    public void delete(ProductSupplierDTO productSupplierDTO) {
        productSupplierRepository.delete(convertToPojo(productSupplierDTO));
    }

    public ProductSupplierDTO convertToDto(ProductSupplier productSupplier) {
        ProductSupplierDTO converted = modelMapper.map(productSupplier, ProductSupplierDTO.class);
        converted.setSupplierDTO(supplierService.convertToDto(productSupplier.getSupplier()));
        return converted;
    }

    private ProductSupplier convertToPojo(ProductSupplierDTO productSupplierDTO) {
        return modelMapper.map(productSupplierDTO, ProductSupplier.class);
    }
}
