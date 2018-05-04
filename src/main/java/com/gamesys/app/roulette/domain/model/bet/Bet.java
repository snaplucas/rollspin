package com.gamesys.app.roulette.domain.model.bet;

import com.gamesys.app.roulette.domain.model.player.Player;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Bet {

    private final Player player;
    private final BetKind betKind;
    private final double amount;

    public boolean isValidAmount() {
        return amount > 0;
    }

}
