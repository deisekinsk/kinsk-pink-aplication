package com.kinsk.pink.controller;


import com.kinsk.pink.model.Subscription;
import com.kinsk.pink.service.SubscriptionService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.webjars.NotFoundException;

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
        System.out.println("R READ Subscription");
        return new ResponseEntity<List<Subscription>>(subscriptionService.findAll(),
                HttpStatus.OK);
    }


    @GetMapping("/{id}")
    @Operation(summary = "Get a user By Id", description = "Retrieve a user from the database by ID")
    public ResponseEntity<Subscription> findById(@PathVariable Long id) {
        System.out.println("R READ BY ID");
        Subscription subsById = subscriptionService.findSubsById(id);
        if (subsById != null) {
            //cover unuseful response
            subsById.getProduct().setSubscriptions(null);
            subsById.getUser().setPass("*****");
            return ResponseEntity.ok(subsById);
        } else {
            throw new NotFoundException("User not found with ID: " + id);
        }
    }


    @PostMapping("/{pricingCategoryId}")
    @Operation(summary = "Create a subscription", description = "Add a new subscription to the " +
            "database")
    public ResponseEntity<Subscription> postSubscription(@PathVariable Long pricingCategoryId,
                                                         @RequestParam(required = false) Long productId,
                                                         @RequestParam(required = false) Long userId,
                                                         @RequestBody Subscription subscription)
            throws ChangeSetPersister.NotFoundException {
        Subscription createdSubscription = subscriptionService.save(subscription,
                pricingCategoryId, productId, userId);
        System.out.println("C CREATE Subscription id nº " + createdSubscription.getId());
        return ResponseEntity.ok(createdSubscription);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a subscription", description = "Update a subscription in the database")
    public ResponseEntity<Subscription> updateSubs(@PathVariable Long id, @RequestBody Subscription subscription) {

        Subscription subsUpdate = subscriptionService.update(id, subscription);
        System.out.println("U UPDATE Subscription | startUser " + subsUpdate.getStartDate());
        System.out.println("         Subscription| lastUpdate " + subsUpdate.getLastUpdate());
        return new ResponseEntity<>(subsUpdate, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete/Update a subscription", description = "Change a subscription " +
            "status and endDate")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {

        subscriptionService.deleteById(id);
        System.out.println("D DELETE Subscription nº" + id);
        return ResponseEntity.ok().build();
    }

}
