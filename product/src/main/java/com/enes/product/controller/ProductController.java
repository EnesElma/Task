package com.enes.product.controller;

import com.enes.product.entity.Product;
import com.enes.product.service.IProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("product")
public class ProductController {

    private final IProductService service;

    public ProductController(IProductService service) {
        this.service = service;
    }

    @PostMapping("create")
    public ResponseEntity<?> create(@RequestBody Product request){
        return new ResponseEntity<>(service.createProduct(request) , HttpStatus.CREATED);
    }

    @PutMapping("update")
    public ResponseEntity<?> update(@RequestBody Product request){
        Product product = service.updateProduct(request);
        return ResponseEntity.ok(product);
    }


    @DeleteMapping("delete/{id}/{username}")
    public ResponseEntity<?> delete(@PathVariable Long id, @PathVariable String username){
        service.deleteProduct(id,username);
        return new ResponseEntity<>("Product delete successful",HttpStatus.ACCEPTED);
    }

    @GetMapping("list")
    public ResponseEntity<?> list(){
        return ResponseEntity.ok(service.listAllProduct());
    }
}
