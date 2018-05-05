package com.gamesys.app.domain.model.roulette;

import com.gamesys.app.application.PlayerResultService;
import com.gamesys.app.domain.model.bet.Bet;
import com.gamesys.app.domain.model.bet.BetKind;
import com.gamesys.app.domain.model.bet.BetKindFactory;
import com.gamesys.app.domain.model.player.Player;
import com.gamesys.app.domain.model.player.PlayerResult;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class Roulette {

    private static final int MAX_ROULETTE_NUMBER = 36;
    private static final int INTERVAL = 30;

    private List<Bet> betList = new CopyOnWriteArrayList<>();
    private List<Player> players;
    private final PlayerResultService playerResultService;
    private final RouletteResult rouletteResult = new RouletteResult();

    public Roulette(List<Player> players, PlayerResultService playerResultService) {
        this.players = players;
        this.playerResultService = playerResultService;
    }

    public void spin() {
        ScheduledExecutorService execService = Executors.newScheduledThreadPool(5);
        execService.scheduleAtFixedRate(() -> {
            int resultNumber = getResultNumber();
            List<PlayerResult> playerResults = betList.stream().map(x -> playerResultService.getPlayerResult(x, resultNumber)).collect(Collectors.toList());
            System.out.println(rouletteResult.getDefaultGameResult(playerResults, resultNumber));
            players = updatePlayerTotalWinAndBet(playerResults);
            System.out.println(rouletteResult.getPlayerGameResult(players));
            betList.clear();
        }, 10, INTERVAL, TimeUnit.SECONDS);
    }

    private int getResultNumber() {
        Random random = new Random();
        return random.nextInt(MAX_ROULETTE_NUMBER + 1);
    }

    private List<Player> updatePlayerTotalWinAndBet(List<PlayerResult> playerResults) {
        List<Player> playerList = new ArrayList<>(players);
        playerList.forEach(x -> x.setTotalWin(x.getTotalWin() + playerResults.stream().filter(y -> Objects.equals(y.getPlayerName(), x.getName())).findFirst().get().getWinnings()));
        playerList.forEach(x -> x.setTotalWin(x.getTotalWin() + betList.stream().filter(y -> Objects.equals(y.getPlayer().getName(), x.getName())).findFirst().get().getAmount()));
        return playerList;
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
