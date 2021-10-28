package com.eduardo.store.controller;

import com.eduardo.store.dto.ProductDTO;
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
        if (!(productItemCode == null || productService.findByItemCode(productItemCode).isEmpty())) {
            return ResponseEntity.ok(productService.findByItemCode(productItemCode));
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

    @PutMapping("/{itemCode}")
    @ResponseBody
    public ResponseEntity<ProductDTO> update(@RequestBody ProductDTO productDTO,
                          @PathVariable Long itemCode) {
        if (productDTO == null || itemCode == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(productService.update(productDTO, itemCode));
    }

    @DeleteMapping("/{itemCode}")
    @ResponseBody
    public ResponseEntity<Void> delete(@PathVariable Long itemCode){
        if (itemCode == null) {
            return ResponseEntity.badRequest().build();
        }
        productService.deleteByItemCode(itemCode);
        return ResponseEntity.ok().build();
    }

    @PatchMapping({"/{itemCode}"})
    @ResponseBody
    public ResponseEntity<ProductDTO> updateState(@PathVariable Long itemCode) {
        if (itemCode == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(productService.deactivate(itemCode));
    }
}


