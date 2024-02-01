package com.kinsk.pink.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "PRICING_CATEGORY")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PricingCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRICING_CATEGORY_ID")
    private Long id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "FIXED_RATE")
    private Double fixedRate;
    @Column(name = "DISCOUNT")
    private Double discount;
    @Column(name = "USAGE_RATE")
    private Double usageRate;
}

