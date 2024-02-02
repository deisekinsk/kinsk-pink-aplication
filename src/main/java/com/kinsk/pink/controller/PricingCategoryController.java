package com.kinsk.pink.controller;


import com.kinsk.pink.model.PricingCategory;
import com.kinsk.pink.service.PrincingCategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pricingCat")
public class PricingCategoryController {


    @Autowired
    private PrincingCategoryService princingCategoryService;

    public PricingCategoryController(PrincingCategoryService princingCategoryService) {
        this.princingCategoryService = princingCategoryService;
    }

    @GetMapping
    @Operation(summary = "Get All", description = "Retrieve all from the database")
    public ResponseEntity<List<PricingCategory>> findAllPrincingCat(){
        return new ResponseEntity<List<PricingCategory>>(princingCategoryService.findAll(),
                HttpStatus.OK);
    }


    //*check collections
    @GetMapping("/{id}")
    @Operation(summary = "Get a pricing category By Id", description = "Retrieve a pricing" +
            " from the database by ID")
    public ResponseEntity<PricingCategory> findById(@PathVariable Long id)
            throws ChangeSetPersister.NotFoundException {
        return new ResponseEntity<PricingCategory>(princingCategoryService.findPricingById(id),
                HttpStatus.OK);

    }


    @PostMapping
    @Operation(summary = "Create a pricing category", description = "Add a new pricing category to the " +
            "database")
    public ResponseEntity<PricingCategory> postPricingCat(@RequestBody PricingCategory postPricingCat){
        return new ResponseEntity<PricingCategory>(princingCategoryService.save(postPricingCat),
                HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a pricing category", description = "Remove a pricing category from the " +
            "database" +
            " by ID")
    public ResponseEntity<PricingCategory> deleteById(@PathVariable Long id)
            throws ChangeSetPersister.NotFoundException {
        if (id != null) {
            princingCategoryService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else throw new ChangeSetPersister.NotFoundException();

    }
}
