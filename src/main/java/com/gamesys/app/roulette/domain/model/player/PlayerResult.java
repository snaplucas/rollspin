package com.gamesys.app.roulette.domain.model.player;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PlayerResult {

    private String playerName;
    private String bet;
    private Outcome outcome;
    private double winnings;

    @Override
    public String toString() {
        // TODO: format output
        return "";
    }

}
