package com.kinsk.pink.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @Column(name = "ID")
    private Long id;
    @Column(name = "STARTDATE")
    private Date startDate;
    @Column(name = "ENDDATE")
    private Date endDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "Status")
    private SubscriptionSTS status; //activate cancel pending expiated

    @OneToOne
    @JoinColumn(name = "PINKUSER_ID")
    private User user;

    @OneToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

}

