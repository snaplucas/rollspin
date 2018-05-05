package com.gamesys.app.domain.model.player;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PlayerResult {

    private String playerName;
    private String bet;
    private Outcome outcome;
    private double winnings;

}
