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

//    @Column(name = "Status")
//    private SubscriptionSTS Status; //activate cancel pending expiated
//    @Column(name = "PINKUSER")
//    private User user;
//    @Column(name = "Status")
//    private Product product;

}

