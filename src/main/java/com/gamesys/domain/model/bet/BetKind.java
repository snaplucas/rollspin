package com.gamesys.domain.model.bet;

public interface BetKind {

    double payoff();

    String toString();

    default boolean equals(BetKind betKind) {
        return this.toString().equals(betKind.toString());
    }
}
