package com.kinsk.pink.controller;


import com.kinsk.pink.model.Product;
import com.kinsk.pink.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public ResponseEntity<List<Product>> findAllProducts(){
        System.out.println("FindALL");
        return new ResponseEntity<List<Product>>(productService.findAll(),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Product>> findById(@PathVariable Long ID)
            throws ChangeSetPersister.NotFoundException {

        System.out.println("FindById");
        return new ResponseEntity(productService.findProductById(ID).getId(),
                HttpStatus.OK);
    }
}
