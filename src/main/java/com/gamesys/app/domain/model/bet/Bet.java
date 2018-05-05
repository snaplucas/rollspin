package com.gamesys.app.domain.model.bet;

import com.gamesys.app.domain.model.player.Player;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Bet {

    private Player player;
    private BetKind betKind;
    private double amount;

    public Bet() {
        this.amount = 0.0;
    }

    public boolean isValidAmount() {
        return amount > 0;
    }

}
