package com.kinsk.pink.repository;


import com.kinsk.pink.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionRespository extends JpaRepository<Subscription, Long> {
}
