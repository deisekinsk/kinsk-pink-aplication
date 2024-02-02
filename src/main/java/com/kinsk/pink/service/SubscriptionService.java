package com.kinsk.pink.service;

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


    public SubscriptionService(SubscriptionRespository subscriptionRespository){
        this.subscriptionRespository = subscriptionRespository;
    }

    public List<Subscription> findAll(){
        return subscriptionRespository.findAll();
    }

    public Subscription findSubsById( Long id){
        if(id != null){
            Optional<Subscription> subsOpt = subscriptionRespository.findById(id);
            return  subsOpt.get();
        }else throw new RuntimeException("Subscription ID not found");
    }

    public Subscription save(Subscription subscription)
            throws ChangeSetPersister.NotFoundException {
        if (subscription.getStartDate() == null) {
            subscription.setStartDate(new Date());
            subscription.setLastUpdate(new Date());
        }

        if (subscription.getSubscriptionSTS() == null) {
            // SubscriptionSTS.ACTIVE
            subscription.setSubscriptionSTS(SubscriptionSTS.ACTIVE);
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
        //Update subscription status
        subsAux.setSubscriptionSTS(subscription.getSubscriptionSTS());
        //Preserve the original startDate
        subsAux.setStartDate(subsAux.getStartDate());
        // Update the last update date to the current time
        subsAux.setLastUpdate(new Date());

        return subsAux;
    }


    public void deleteById(Long id) {
        Subscription subsID = findSubsById(id);

        if (subsID != null) {
            // SubscriptionSTS.CANCEL
            subsID.setSubscriptionSTS(SubscriptionSTS.CANCEL);
            // Update the last update date to the current time
            subsID.setLastUpdate(new Date());
            subsID.setEndDate(new Date());
            subscriptionRespository.save(subsID);
        } else {
            throw new RuntimeException("Subscription ID not found");
        }
    }

}


