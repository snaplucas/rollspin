package com.gamesys.app.roulette.domain.model.bet;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class NumberBet implements BetKind {

    private final int number;

    @Override
    public double payoff() {
        return 36.0;
    }

    @Override
    public String valueToString() {
        return String.valueOf(number);
    }
}
