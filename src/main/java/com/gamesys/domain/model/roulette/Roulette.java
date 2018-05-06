package com.gamesys.domain.model.roulette;

import com.gamesys.application.dto.PlayerResultDto;
import com.gamesys.application.exceptions.BetException;
import com.gamesys.application.service.BetService;
import com.gamesys.application.interfaces.IBetService;
import com.gamesys.application.interfaces.IPlayerResultService;
import com.gamesys.application.interfaces.IPlayerService;
import com.gamesys.domain.model.bet.Bet;
import com.gamesys.domain.model.player.Player;

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
    private final IBetService IBetService;
    private final IPlayerResultService IPlayerResultService;
    private final IPlayerService IPlayerService;
    private final RouletteResult rouletteResult;

    public Roulette(List<Player> players, IPlayerResultService IPlayerResultService, IPlayerService IPlayerService) {
        this.players = players;
        this.IBetService = new BetService(players);
        this.IPlayerResultService = IPlayerResultService;
        this.IPlayerService = IPlayerService;
        this.rouletteResult = new RouletteResult();
    }

    public void spin() {
        ScheduledExecutorService execService = Executors.newScheduledThreadPool(5);
        execService.scheduleAtFixedRate(() -> {
            int resultNumber = getResultNumber();

            List<PlayerResultDto> playerResultDtos = getPlayerResults(resultNumber);
            showDefaultGameResult(resultNumber, playerResultDtos);

            players = IPlayerService.getPlayerTotalWinAndBet(players, playerResultDtos, betList);
            showPlayersGameResult();

            betList.clear();
        }, INTERVAL, INTERVAL, TimeUnit.SECONDS);
    }

    private List<PlayerResultDto> getPlayerResults(int resultNumber) {
        return betList.stream().map(x -> IPlayerResultService.getPlayerResult(x, resultNumber)).collect(Collectors.toList());
    }

    private int getResultNumber() {
        Random random = new Random();
        return random.nextInt(MAX_ROULETTE_NUMBER + 1);
    }

    public void placeBet(String betLine) {
        try {
            Bet bet = IBetService.getBetFromLine(betLine);
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
