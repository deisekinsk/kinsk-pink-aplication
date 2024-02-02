package com.kinsk.pink.service;

import com.kinsk.pink.model.*;
import com.kinsk.pink.repository.SubscriptionRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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

    public Subscription save(Subscription subscription)
            throws ChangeSetPersister.NotFoundException {
        if (subscription.getStartDate() == null) {
            subscription.setStartDate(new Date());
        }

        return subscriptionRespository.save(subscription);

    }
}


