package com.kinsk.pink.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "PRODUCT")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "NAME")
    private String name; //PINK PURPLE BLUE
    @Column(name = "PRICE")
    private Double priceRoot;

    //Collection
    @OneToMany(mappedBy = "product")
    private List<Subscription> subscriptions;

}
