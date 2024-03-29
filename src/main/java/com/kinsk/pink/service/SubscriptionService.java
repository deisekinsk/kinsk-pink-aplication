package com.kinsk.pink.service;

import com.kinsk.pink.handler.BusinessException;
import com.kinsk.pink.model.*;
import com.kinsk.pink.repository.SubscriptionRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class SubscriptionService {

    @Autowired
    private SubscriptionRespository subscriptionRespository;

    @Autowired
    private PrincingCategoryService pricingCategoryService;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;


    public SubscriptionService(SubscriptionRespository subscriptionRespository){
        this.subscriptionRespository = subscriptionRespository;
    }

    public List<Subscription> findAll(){
        return subscriptionRespository.findAll();
    }

    public Subscription findSubsById(Long id) {
        if (id != null) {
            Optional<Subscription> subsOpt = subscriptionRespository.findById(id);
            return subsOpt.orElseThrow(() -> new NotFoundException("Subscription not found with ID: " + id));
        } else {
            throw new IllegalArgumentException("Subscription ID cannot be null");
        }
    }

    public Subscription save(Subscription subscription, Long pricingCategoryId,
                             Long productId, Long userId)
            throws ChangeSetPersister.NotFoundException {
        // Set startDate and lastUpdate if not provided.
        if (subscription.getStartDate() == null) {
            subscription.setStartDate(new Date());
            subscription.setLastUpdate(new Date());
        }
        // Set Subscription Status to ACTIVE if not provided.
        if (subscription.getSubscriptionSTS() == null) {

            subscription.setSubscriptionSTS(SubscriptionSTS.ACTIVE);
        }
        // PricingCategory | // Associate the Subscription with a PricingCategory.
        if (pricingCategoryId != null) {
            PricingCategory pricingCategory = pricingCategoryService.findPricingById(pricingCategoryId);
            subscription.setPricingCategory(pricingCategory);
        } else {
            throw new RuntimeException("PricingCategoryId is required");
        }

        //Product | // Associate the Subscription with a Product.
        if (productId != null) {
            Product product = productService.findProductById(productId);
            subscription.setProduct(product);
        } else {
            throw new RuntimeException("ProductId is required");
        }

        // User | Associate the Subscription with a User.
        if (userId != null) {
            User user = userService.findUserById(userId);
            subscription.setUser(user);
        } else {
            throw new RuntimeException("UserId is required");
        }

        return subscriptionRespository.save(subscription);
    }

    public Subscription update(Long id, Subscription subscription) throws NotFoundException {

        Subscription subsID = findSubsById(id);

        if(subsID != null){
            return subscriptionRespository.save(conversion(subscription, subsID));
        }else throw new RuntimeException("Subscription ID not found");
    }

    public Subscription conversion(Subscription subscription, Subscription subsAux){
        //Preserve the original startDate
        subsAux.setStartDate(subsAux.getStartDate());
        // Update the last update date to the current time
        subsAux.setLastUpdate(new Date());
        //Update subscription status
        subsAux.setSubscriptionSTS(subscription.getSubscriptionSTS());

        return subsAux;
    }

    public void deleteById(Long id) {
        Subscription subsID = findSubsById(id);

        if (subsID != null) {
            // Check if the subscription status is not "CANCEL"
            if (!SubscriptionSTS.CANCEL.equals(subsID.getSubscriptionSTS())) {
                // If not "CANCEL", update the status, last update date, and end date
                subsID.setSubscriptionSTS(SubscriptionSTS.CANCEL);
                subsID.setLastUpdate(new Date());
                subsID.setEndDate(new Date());
                subscriptionRespository.save(subsID);
            } else {
                // If the status is "CANCEL", throw an exception indicating that the subscription cannot be deleted
                throw new BusinessException("Subscription with status 'CANCEL' cannot be " +
                        "canceled again.");
            }
        } else {
            throw new RuntimeException("Subscription ID not found");
        }
    }


}


