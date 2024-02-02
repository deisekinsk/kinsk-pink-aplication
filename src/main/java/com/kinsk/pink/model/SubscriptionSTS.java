package com.kinsk.pink.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public enum SubscriptionSTS {
    ACTIVE("ACTIVE"),
    CANCEL("CANCEL"),
    PENDING("PENDING");

    private String menuSTS;

    SubscriptionSTS(String menuSTS) {
        this.menuSTS = menuSTS;
    }
}
