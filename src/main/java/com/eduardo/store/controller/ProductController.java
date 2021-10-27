package com.eduardo.store.controller;

import com.eduardo.store.dto.ProductDTO;
import com.eduardo.store.service.IProductService;
import com.eduardo.store.service.ISupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8082")
@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private IProductService productService;

    @Autowired
    private ISupplierService supplierService;

    @GetMapping("/findAll")
    @ResponseBody
    public List<ProductDTO> findAll(){
        return productService.findAll();
    }

    @GetMapping("/itemCode/{productItemCode}")
    @ResponseBody
    public List<ProductDTO> findByItemCode(@PathVariable Long productItemCode){
        return productService.findByItemCode(productItemCode);
    }

    @GetMapping("/state/{productState}")
    @ResponseBody
    public List<ProductDTO> findByState(@PathVariable String productState){

        return productService.findByState(productState);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ProductDTO save(@RequestBody ProductDTO product){
        return productService.save(product);
    }

    @PutMapping("/{itemCode}")
    @ResponseBody
    public ProductDTO update(@RequestBody ProductDTO productDTO,
                          @PathVariable Long itemCode) {
        return productService.update(productDTO, itemCode);
    }

    @DeleteMapping("/{itemCode}")
    public void delete(@PathVariable Long itemCode){
        productService.deleteByItemCode(itemCode);
    }

    @PutMapping({"/deactivate/{itemCode}"})
    @ResponseBody
    public ProductDTO deactivate(@PathVariable Long itemCode) {
        return productService.deactivate(itemCode);
    }
}


