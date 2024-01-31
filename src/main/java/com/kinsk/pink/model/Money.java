package com.kinsk.pink.model;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Money {
    private BigDecimal amount;
    private String currency;
    private String formattedAmount;
    private int precision;
    private BigDecimal exchangeRate;
}

