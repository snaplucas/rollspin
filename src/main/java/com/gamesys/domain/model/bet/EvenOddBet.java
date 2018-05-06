package com.gamesys.domain.model.bet;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EvenOddBet implements BetKind {

    private final BetType type;

    @Override
    public double payoff() {
        return 2.0;
    }

    @Override
    public String toString() {
        return type.toString();
    }
}
