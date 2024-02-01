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
    private Long id;

    @Column(name = "START_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;

    @Column(name = "END_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;

    @Column(name = "LAST_UPDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdate;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private SubscriptionSTS status;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    @Schema(hidden = true)
    private User user;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    @Schema(hidden = true)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID")
    @Schema(hidden = true)
    private PricingCategory pricingCategory;
}

