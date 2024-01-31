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
    @Column(name = "LAST_UPDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdate;

    @Enumerated(EnumType.STRING)
    @Column(name = "Status")
    private SubscriptionSTS status; //ACTIVATE, CANCEL,PENDING

//    @OneToOne
//    @PrimaryKeyJoinColumn(name = "USER_ID")
//    private User user;
//
//    @OneToOne
//    @PrimaryKeyJoinColumn(name = "PRODUCT_ID")
//    private Product product;


}

