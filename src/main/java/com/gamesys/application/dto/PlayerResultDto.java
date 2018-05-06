package com.gamesys.application.dto;

import com.gamesys.domain.model.player.Outcome;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PlayerResultDto {

    private String playerName;
    private String bet;
    private Outcome outcome;
    private double winnings;

    public PlayerResultDto() {
        this.playerName = "";
        this.bet = "0.0";
        this.outcome = Outcome.LOSE;
        this.winnings = 0.0;
    }

}
