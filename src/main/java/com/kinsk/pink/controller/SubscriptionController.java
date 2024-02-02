package com.kinsk.pink.controller;


import com.kinsk.pink.model.PricingCategory;
import com.kinsk.pink.model.Product;
import com.kinsk.pink.model.Subscription;
import com.kinsk.pink.model.User;
import com.kinsk.pink.service.SubscriptionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subs")
public class SubscriptionController {

    @Autowired
    private SubscriptionService subscriptionService;

    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @GetMapping
    @Operation(summary = "Get All", description = "Retrieve all subscriptions from the database")
    public ResponseEntity<List<Subscription>> findAll(){
        return new ResponseEntity<List<Subscription>>(subscriptionService.findAll(),
                HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Create a subscription", description = "Add a new subscription to the " +
            "database")
    public ResponseEntity<Subscription> postSubscription(@RequestBody Subscription subscription)
            throws ChangeSetPersister.NotFoundException {
        Subscription createdSubscription = subscriptionService.save(subscription);
        return ResponseEntity.ok(createdSubscription);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a subscription", description = "Update a subscription in the database")
    public ResponseEntity<Subscription> updateSubs(@PathVariable Long id, @RequestBody Subscription subscription) {
        Subscription subsUpdate = subscriptionService.update(id, subscription);
        return new ResponseEntity<>(subsUpdate, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete/Update a subscription", description = "Change a subscription " +
            "status and endDate")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        subscriptionService.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
