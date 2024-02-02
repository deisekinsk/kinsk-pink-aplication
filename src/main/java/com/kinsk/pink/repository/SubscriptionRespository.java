package com.kinsk.pink.repository;


import com.kinsk.pink.model.Subscription;
import com.kinsk.pink.model.SubscriptionSTS;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubscriptionRespository extends JpaRepository<Subscription, Long> {
}
