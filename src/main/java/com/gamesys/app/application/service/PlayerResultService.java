package com.gamesys.app.application.service;

import com.gamesys.app.domain.model.bet.*;
import com.gamesys.app.domain.model.player.Outcome;
import com.gamesys.app.application.dto.PlayerResultDto;

import java.util.Arrays;
import java.util.List;

public class PlayerResultService {

    public PlayerResultDto getPlayerResult(Bet bet, int resultNumber) {
        Outcome outcome = Outcome.LOSE;
        double winnings = 0.0;
        if (hasPlayerWon(bet.getBetKind(), resultNumber)) {
            winnings = bet.getBetKind().payoff() * bet.getAmount();
            outcome = Outcome.WIN;
        }
        return new PlayerResultDto(bet.getPlayer().getName(), bet.getBetKind().toString(), outcome, winnings);
    }

    private boolean hasPlayerWon(BetKind betKind, int resultNumber) {
        return getWinningKinds(resultNumber).stream().anyMatch(x -> x.equals(betKind));
    }

    private List<BetKind> getWinningKinds(int number) {
        return Arrays.asList(new NumberBet(number), evenOrOdd(number));
    }

    private EvenOddBet evenOrOdd(int number) {
        if (number % 2 == 0) {
            return new EvenOddBet(BetType.EVEN);
        }
        return new EvenOddBet(BetType.ODD);
    }
}
