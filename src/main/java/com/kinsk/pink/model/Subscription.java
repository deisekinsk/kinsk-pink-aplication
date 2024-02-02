package com.kinsk.pink.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

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
    private Date endDate;

    @Column(name = "LAST_UPDATE")
    @Temporal(TemporalType.TIMESTAMP)
    @Schema(hidden = true)
    private Date lastUpdate;

}