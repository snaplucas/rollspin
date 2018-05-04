package com.gamesys.app.roulette.domain.model.roulette;

import com.gamesys.app.roulette.domain.model.bet.*;
import com.gamesys.app.roulette.domain.model.player.Outcome;
import com.gamesys.app.roulette.domain.model.player.Player;
import com.gamesys.app.roulette.domain.model.player.PlayerResult;

import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
// TODO: change name to table
public class Table {

    public static final int MAX_ROULETTE_NUMBER = 36;
    private static final int INTERVAL = 30 * 1000;

    private List<Bet> betList = new ArrayList<>();
    private List<Player> players;

    public Table(List<Player> players) {
        this.players = players;
    }

    public void spin() {
        ScheduledExecutorService execService = Executors.newScheduledThreadPool(5);
        execService.scheduleAtFixedRate(() -> {
            int resultNumber = getResultNumber();
            List<PlayerResult> playerResults = betList.stream().map(x -> getPlayerResult(x, resultNumber)).collect(Collectors.toList());
            playerResults.forEach(x -> System.out.println(x.toString()));
        }, 0, INTERVAL, TimeUnit.SECONDS);
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

    private int getResultNumber() {
        Random random = new Random();
        return random.nextInt(MAX_ROULETTE_NUMBER + 1);
    }

    public void placeBet(String betLine) {
        Bet bet = getBetFromBetLine(betLine);
        if (bet != null && bet.isValidAmount()) {
            betList.add(bet);
        }
    }

    private Bet getBetFromBetLine(String betLine) {
        String[] elements = betLine.trim().split("\\s+");

        // TODO: validations
        // TODO: extract methods
        if (elements.length == 3) {
            String playerName = elements[0];
            Optional<Player> player = players.stream().filter(x -> x.getName().equals(playerName)).findFirst();

            String betKindAsString = elements[1];

            BetKindFactory betKindFactory = new BetKindFactory(betKindAsString);
            BetKind betKind = betKindFactory.getBetKind();

            String amountAsString = elements[2];
            double amount = Double.parseDouble(amountAsString);

            if (player.isPresent()) {
                return new Bet(player.get(), betKind, amount);
            } else {
                System.out.println("Player not found");
                return null;
            }
        }
        System.out.println("Missing elements");
        return null;
    }
}
