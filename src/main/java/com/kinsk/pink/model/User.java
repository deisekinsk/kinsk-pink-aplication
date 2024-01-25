package com.kinsk.pink.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "PINKUSER")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "PASS")
    private String pass;

    @Column(name = "STARTER")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startUser;
    @Column(name = "LAST_UPDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdate;

    @OneToOne(mappedBy = "user")
    private Subscription subscription;
}
