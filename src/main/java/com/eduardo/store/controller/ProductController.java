package com.eduardo.store.controller;

import com.eduardo.store.dto.PriceReductionDTO;
import com.eduardo.store.dto.ProductDTO;
import com.eduardo.store.dto.SupplierDTO;
import com.eduardo.store.service.IProductService;
import com.eduardo.store.service.ISupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8082")
@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private IProductService productService;

    @Autowired
    private ISupplierService supplierService;

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<ProductDTO>> getByFilter(
            @RequestParam(value = "item-code", required = false) Long productItemCode,
            @RequestParam(value = "state", required = false) String productState) {
        if (!(productItemCode == null || productService.findByProductCode(productItemCode).isEmpty())) {
            return ResponseEntity.ok(productService.findByProductCode(productItemCode));
        } else if (!(productState == null || productService.findByState(productState).isEmpty())) {
            return ResponseEntity.ok(productService.findByState(productState));
        } else if (!productService.findAll().isEmpty() && productState == null && productItemCode == null) {
            return ResponseEntity.ok(productService.findAll());
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ResponseEntity<ProductDTO> create(@RequestBody ProductDTO product){
        if (product == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(productService.save(product));
    }

    @PutMapping("/{productCode}")
    @ResponseBody
    public ResponseEntity<ProductDTO> update(@RequestBody ProductDTO productDTO,
                          @PathVariable Long productCode) {
        if (productDTO == null || productCode == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(productService.update(productDTO, productCode));
    }

    @DeleteMapping("/{productCode}")
    @ResponseBody
    public ResponseEntity<Void> delete(@PathVariable Long productCode){
        if (productCode == null) {
            return ResponseEntity.badRequest().build();
        }
        productService.deleteByProductCode(productCode);
        return ResponseEntity.ok().build();
    }

    @PatchMapping({"/{productCode}"})
    @ResponseBody
    public ResponseEntity<ProductDTO> updateState(@PathVariable Long productCode) {
        if (productCode == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(productService.deactivate(productCode));
    }

    @PutMapping("/new-supplier/{productCode}")
    @ResponseBody
    public ResponseEntity<ProductDTO> putSupplier(@RequestBody SupplierDTO supplierDTO,
                                                  @PathVariable Long productCode) {
        if (supplierDTO == null || productCode == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(productService.addSupplier(supplierDTO, productCode));
    }

    @PutMapping("/new-price-reduction/{productCode}")
    @ResponseBody
    public ResponseEntity<ProductDTO> putPriceReduction(@RequestBody PriceReductionDTO priceReductionDTO,
                                                  @PathVariable Long productCode) {
        if (priceReductionDTO == null || productCode == null) {
            return ResponseEntity.badRequest().build();
        }
        productService.addPriceReduction(priceReductionDTO, productCode);
        return ResponseEntity.ok(productService.findByProductCode(productCode).get(0));
    }
}


