package com.kinsk.pink.model;

import io.swagger.v3.oas.annotations.media.Schema;
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
    @Column(name = "PRODUCT_ID")
    @Schema(hidden = true)
    private Long id;

    @Column(name = "NAME")
    private String name;

    // Represents all subscriptions associated with this product.
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    @Schema(hidden = true)
    private List<Subscription> subscriptions;


}
