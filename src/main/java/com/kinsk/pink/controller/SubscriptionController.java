package com.kinsk.pink.controller;

import com.kinsk.pink.model.Product;
import com.kinsk.pink.model.Subscription;
import com.kinsk.pink.model.User;
import com.kinsk.pink.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subscriptions")
public class SubscriptionController {

    @Autowired
    private SubscriptionService subscriptionService;

    @GetMapping
    public ResponseEntity<List<Subscription>> getAllSubscriptions() {
        return ResponseEntity.ok(subscriptionService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Subscription> getSubscriptionById(@PathVariable Long id) {
        return ResponseEntity.ok(subscriptionService.findSubscriptionById(id));
    }

    @PostMapping
    public ResponseEntity<Subscription> createSubscription(
            @RequestBody Subscription subscription,
            @RequestParam Long userId,
            @RequestParam Long productId) {

        User user = new User();
        user.setId(userId);

        Product product = new Product();
        product.setId(productId);

        subscription.setUser(user);
        subscription.setProduct(product);

        return ResponseEntity.ok(subscriptionService.save(subscription));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Subscription> updateSubscription(@PathVariable Long id, @RequestBody Subscription subscription) {
        subscription.setId(id);
        return ResponseEntity.ok(subscriptionService.update(subscription));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubscription(@PathVariable Long id) {
        subscriptionService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
