package com.eduardo.store.controller;

import com.eduardo.store.dto.SupplierDTO;
import com.eduardo.store.service.ISupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8082")
@RestController
@RequestMapping("/api/supplier")
public class SupplierController {

    @Autowired
    private ISupplierService supplierService;

    @GetMapping("/name/{supplierName}")
    @ResponseBody
    public List<SupplierDTO> findByName(@PathVariable String supplierName){
        return supplierService.findByName(supplierName);
    }

    @GetMapping("/country/{supplierCountry}")
    public List<SupplierDTO> findByCountry(@PathVariable String supplierCountry){
        return supplierService.findByCountry(supplierCountry);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public SupplierDTO save(@RequestBody SupplierDTO supplier){
        return supplierService.save(supplier);
    }



}
