package com.eduardo.store.controller;

import com.eduardo.store.model.Supplier;
import com.eduardo.store.repo.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/supplier")
public class SupplierController {
    @Autowired
    private SupplierRepository supplierRepository;

    @GetMapping("/name/{supplierName}")
    public List<Supplier> findByName(@PathVariable String supplierName){
        return supplierRepository.findByName(supplierName);
    }

}
