package com.kinsk.pink.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "SUBSCRIPTION")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SUBSCRIPTION_ID")
    @Schema(hidden = true)
    private Long id;

    @Column(name = "START_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    @Schema(hidden = true)
    private Date startDate;

    @Column(name = "END_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    @Schema(hidden = true)
    private Date endDate;

    @Column(name = "LAST_UPDATE")
    @Temporal(TemporalType.TIMESTAMP)
    @Schema(hidden = true)
    private Date lastUpdate;

    //set a value, null is not available
    @Column(name = "SUBS_STATUS", nullable = false)
    @Enumerated(EnumType.STRING)
    private SubscriptionSTS subscriptionSTS;

    // Many-to-one relationship with PricingCategory
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRICING_CATEGORY_ID")
    private PricingCategory pricingCategory;


}