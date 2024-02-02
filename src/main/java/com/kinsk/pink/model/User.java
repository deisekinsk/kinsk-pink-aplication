package com.kinsk.pink.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "PINKUSER")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    @Schema(hidden = true)
    private Long id;

    @Column(name = "NAME")
    private String name;
    @Column(name = "PASS")
    private String pass;

    @Column(name = "STARTER")
    @Temporal(TemporalType.TIMESTAMP)
    @Schema(hidden = true)
    private Date startUser;

    @Column(name = "LAST_UPDATE")
    @Temporal(TemporalType.TIMESTAMP)
    @Schema(hidden = true)
    private Date lastUpdate;

}
