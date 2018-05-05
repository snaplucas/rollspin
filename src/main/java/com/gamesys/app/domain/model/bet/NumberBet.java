package com.gamesys.app.domain.model.bet;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class NumberBet implements BetKind {

    private final int number;

    @Override
    public double payoff() {
        return 36.0;
    }

    @Override
    public String toString() {
        return String.valueOf(number);
    }
}