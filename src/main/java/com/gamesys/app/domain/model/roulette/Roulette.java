package com.gamesys.app.domain.model.roulette;

import com.gamesys.app.application.dto.PlayerResultDto;
import com.gamesys.app.application.exceptions.BetException;
import com.gamesys.app.application.service.BetService;
import com.gamesys.app.application.service.PlayerResultService;
import com.gamesys.app.application.service.PlayerService;
import com.gamesys.app.domain.model.bet.Bet;
import com.gamesys.app.domain.model.player.Player;

import java.util.List;
import java.util.Random;
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
    private final BetService betService;
    private final PlayerResultService playerResultService;
    private final RouletteResult rouletteResult;
    private final PlayerService playerService;

    public Roulette(List<Player> players, PlayerResultService playerResultService, PlayerService playerService) {
        this.players = players;
        this.betService = new BetService(players);
        this.playerResultService = playerResultService;
        this.playerService = playerService;
        this.rouletteResult = new RouletteResult();
    }

    public void spin() {
        ScheduledExecutorService execService = Executors.newScheduledThreadPool(5);
        execService.scheduleAtFixedRate(() -> {
            int resultNumber = getResultNumber();

            List<PlayerResultDto> playerResultDtos = getPlayerResults(resultNumber);
            showDefaultGameResult(resultNumber, playerResultDtos);

            players = playerService.getPlayerTotalWinAndBet(players, playerResultDtos, betList);
            showPlayersGameResult();

            betList.clear();
        }, INTERVAL, INTERVAL, TimeUnit.SECONDS);
    }

    private List<PlayerResultDto> getPlayerResults(int resultNumber) {
        return betList.stream().map(x -> playerResultService.getPlayerResult(x, resultNumber)).collect(Collectors.toList());
    }

    private int getResultNumber() {
        Random random = new Random();
        return random.nextInt(MAX_ROULETTE_NUMBER + 1);
    }

    public void placeBet(String betLine) {
        try {
            Bet bet = betService.getBetFromLine(betLine);
            if (bet.isValidAmount()) {
                betList.add(bet);
            } else {
                System.out.println("Invalid bet amount");
            }
        } catch (BetException e) {
            System.out.println(e.getMessage());
        }
    }

    private void showDefaultGameResult(int resultNumber, List<PlayerResultDto> playerResultDtos) {
        System.out.println(rouletteResult.getDefaultGameResult(playerResultDtos, resultNumber));
    }

    private void showPlayersGameResult() {
        System.out.println(rouletteResult.getPlayerGameResult(players));
    }

}
