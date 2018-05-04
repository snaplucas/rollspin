package com.gamesys.app.roulette.domain.model.roulette;

import com.gamesys.app.roulette.domain.model.bet.*;
import com.gamesys.app.roulette.domain.model.player.Outcome;
import com.gamesys.app.roulette.domain.model.player.PlayerResult;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class Roulette {

    private final List<Bet> betList;

    public static final int MAX_ROULETTE_NUMBER = 36;
    private static final int INTERVAL = 30 * 1000;

    public Roulette(List<Bet> betList) {
        this.betList = betList;
    }

    public void spin() {
        ScheduledExecutorService execService = Executors.newScheduledThreadPool(5);
        execService.scheduleAtFixedRate(() -> {
            int resultNumber = getResultNumber();
            List<PlayerResult> playerResults = betList.stream().map(x -> getPlayerResult(x, resultNumber)).collect(Collectors.toList());
            playerResults.forEach(x -> System.out.println(x.toString()));
        }, 0, INTERVAL, TimeUnit.SECONDS);
    }

    private int getResultNumber() {
        Random random = new Random();
        return random.nextInt(MAX_ROULETTE_NUMBER + 1);
    }

    private PlayerResult getPlayerResult(Bet bet, int resultNumber) {
        Outcome outcome = Outcome.LOSE;
        double winnings = 0.0;
        if (hasPlayerWon(bet.getBetKind(), resultNumber)) {
            winnings = bet.getBetKind().payoff() * bet.getAmount();
            outcome = Outcome.WIN;
        }
        return new PlayerResult(bet.getPlayer().getName(), bet.getBetKind().valueToString(), outcome, winnings);
    }


    private boolean hasPlayerWon(BetKind betKind, int resultNumber) {
        return getWinningKinds(resultNumber).stream().anyMatch(x -> x.equals(betKind));
    }

    private List<BetKind> getWinningKinds(int number) {
        return Arrays.asList(new NumberBet(number), evenOrOdd(number));

    }

//TODO: update player totalWin and totalBet

    private EvenOddBet evenOrOdd(int number) {
        if (number % 2 == 0) {
            return new EvenOddBet(BetType.EVEN);
        }
        return new EvenOddBet(BetType.ODD);
    }
}
