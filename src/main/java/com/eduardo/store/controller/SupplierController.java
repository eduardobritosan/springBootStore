package com.eduardo.store.controller;

import com.eduardo.store.dto.SupplierDTO;
import com.eduardo.store.service.ISupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8082")
@RestController
@RequestMapping("/store")
public class SupplierController {

    @Autowired
    private ISupplierService supplierService;

    @GetMapping("/suppliers")
    @ResponseBody
    public List<SupplierDTO> getByName(@RequestParam String supplierName){
        return supplierService.findByName(supplierName);
    }

    @GetMapping("/suppliers/country")
    @ResponseBody
    public List<SupplierDTO> getByCountry(@RequestParam String supplierCountry){
        return supplierService.findByCountry(supplierCountry);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public SupplierDTO create(@RequestBody SupplierDTO supplier){
        return supplierService.save(supplier);
    }
}
