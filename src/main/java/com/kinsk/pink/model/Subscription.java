package com.kinsk.pink.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
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

    // Indicates the subscription status and ensures it is not nullable.
    @Column(name = "SUBS_STATUS", nullable = false)
    @Enumerated(EnumType.STRING)
    private SubscriptionSTS subscriptionSTS;

    // Establishes a many-to-one relationship with PricingCategory.
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PRICING_CATEGORY_ID")
    @Schema(hidden = true)
    private PricingCategory pricingCategory;

    // Represents the product associated with this subscription.
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PRODUCT_ID")
    @Schema(hidden = true)
    private Product product;

    // Many-to-one relationship with User
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID")
    @Schema(hidden = true)
    @JsonIgnoreProperties("subscriptions") // Assuming there's a "subscriptions" field in the User class
    private User user;

    

}