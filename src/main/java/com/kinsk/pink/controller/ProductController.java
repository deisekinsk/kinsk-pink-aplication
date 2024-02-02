package com.kinsk.pink.controller;


import com.kinsk.pink.model.Product;
import com.kinsk.pink.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "Get All", description = "Retrieve all from the database")
    public ResponseEntity<List<Product>> findAllProducts(){
        System.out.println("R READ");
        return new ResponseEntity<List<Product>>(productService.findAll(),
                HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a product By Id", description = "Retrieve a product from the" +
            " database by ID")
    public ResponseEntity<Product> findById(@PathVariable Long id)
            throws ChangeSetPersister.NotFoundException {
        System.out.println("R READ BY ID");
        return new ResponseEntity<Product>(productService.findProductById(id),
                HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Create a product", description = "Add a new product to the " +
            "database")
    public ResponseEntity<Product> postProduct(@RequestBody Product product){
        System.out.println("C CREATE");
        return new ResponseEntity<Product>(productService.save(product),
                HttpStatus.OK);
    }
    @PutMapping
    @Operation(summary = "Update a product", description = "Update a product in the " +
            "database")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product)
            throws ChangeSetPersister.NotFoundException {
        System.out.println("U UPDATE");
        return new ResponseEntity<Product>(productService.update(product),
                HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a product", description = "Remove a product from the " +
            "database" +
            " by ID")
    public ResponseEntity<Product> deleteById(@PathVariable Long id)
            throws ChangeSetPersister.NotFoundException {
        if (id != null) {
            System.out.println("D DELETE");
            productService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else throw new ChangeSetPersister.NotFoundException();

    }
}
