package com.kinsk.pink.repository;

import com.kinsk.pink.model.PricingCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PricingCategoryRepository extends JpaRepository<PricingCategory, Long> {

}
