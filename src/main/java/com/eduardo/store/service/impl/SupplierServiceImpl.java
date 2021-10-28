package com.eduardo.store.service.impl;

import com.eduardo.store.dto.SupplierDTO;
import com.eduardo.store.model.Supplier;
import com.eduardo.store.repo.SupplierRepository;
import com.eduardo.store.service.ISupplierService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SupplierServiceImpl implements ISupplierService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private SupplierRepository supplierRepository;

    public List<SupplierDTO> findByName(String supplierName){
        return supplierRepository.findByName(supplierName).stream().map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<SupplierDTO> findByCountry(String supplierCountry){
        return supplierRepository.findByCountry(supplierCountry).stream().map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<SupplierDTO> findSupplierCode(Long supplierCode) {
        return supplierRepository.findBySupplierCode(supplierCode).stream().map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public SupplierDTO save(SupplierDTO supplier){
        return convertToDto(supplierRepository.save(convertToPojo(supplier)));
    }

    public SupplierDTO convertToDto(Supplier supplier) {
        return modelMapper.map(supplier, SupplierDTO.class);
    }

    public Supplier convertToPojo(SupplierDTO supplierDTO) {
        return modelMapper.map(supplierDTO, Supplier.class);
    }
}
