package com.kinsk.pink.controller;


import com.kinsk.pink.model.Product;
import com.kinsk.pink.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> findAllProducts(){
        System.out.println("R READ");
        return new ResponseEntity<List<Product>>(productService.findAll(),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Product>> findById(@PathVariable Long id)
            throws ChangeSetPersister.NotFoundException {
        System.out.println("R READ BY ID");
        return new ResponseEntity(productService.findProductById(id),
                HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Product> updateProduct(@RequestBody Product product)
            throws ChangeSetPersister.NotFoundException {
        System.out.println("U UPDATE");
        return new ResponseEntity<Product>(productService.update(product),HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Product> postProduct(@RequestBody Product product){
        System.out.println("C CREATE");
        return new ResponseEntity<Product>(productService.save(product),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteById(@PathVariable Long id)
            throws ChangeSetPersister.NotFoundException {
        if (id != null) {
            System.out.println("D DELETE");
            productService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else throw new ChangeSetPersister.NotFoundException();

    }
}
