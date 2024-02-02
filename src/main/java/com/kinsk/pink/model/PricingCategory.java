package com.kinsk.pink.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

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
    @Schema(hidden = true)
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

