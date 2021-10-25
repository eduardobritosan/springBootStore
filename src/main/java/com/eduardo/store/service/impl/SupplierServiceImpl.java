package com.eduardo.store.service.impl;

import com.eduardo.store.model.Supplier;
import com.eduardo.store.repo.SupplierRepository;
import com.eduardo.store.service.ISupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierServiceImpl implements ISupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    public List<Supplier> findByName(String supplierName){
        return supplierRepository.findByName(supplierName);
    }

    public List<Supplier> findByCountry(String supplierCountry){
        return supplierRepository.findByName(supplierCountry);
    }
}
