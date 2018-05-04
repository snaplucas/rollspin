package com.gamesys.app.roulette.domain.model.bet;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EvenOddBet implements BetKind {

    private final BetType type;

    @Override
    public double payoff() {
        return 2.0;
    }

    @Override
    public String valueToString() {
        return type.toString();
    }
}
