package com.eduardo.store.controller;

import com.eduardo.store.dto.SupplierDTO;
import com.eduardo.store.model.Supplier;
import com.eduardo.store.repo.SupplierRepository;
import com.eduardo.store.service.ISupplierService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:8082")
@RestController
@RequestMapping("/api/supplier")
public class SupplierController {

    @Autowired
    private ISupplierService supplierService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/name/{supplierName}")
    @ResponseBody
    public List<SupplierDTO> findByName(@PathVariable String supplierName){
        List<Supplier> suppliers = supplierService.findByName(supplierName);
        return suppliers.stream().map(this::convertToDto)
                .collect((Collectors.toList()));
    }

    @GetMapping("/country/{supplierCountry}")
    public List<Supplier> findByCountry(@PathVariable String supplierCountry){
        return supplierService.findByCountry(supplierCountry);
    }

    private SupplierDTO convertToDto(Supplier supplier) {
        return modelMapper.map(supplier, SupplierDTO.class);
    }

}
