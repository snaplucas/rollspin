package com.gamesys.application.service;

import com.gamesys.application.exceptions.BetException;
import com.gamesys.application.interfaces.IBetService;
import com.gamesys.domain.model.bet.Bet;
import com.gamesys.domain.model.bet.BetKind;
import com.gamesys.domain.model.bet.BetKindFactory;
import com.gamesys.domain.model.player.Player;

import java.util.List;

public class BetService implements IBetService {

    private final List<Player> players;

    private final static int PLAYER_NAME_ARG = 0;
    private final static int BET_ARG = 1;
    private final static int AMOUNT_ARG = 2;

    public BetService(List<Player> players) {
        this.players = players;
    }

    @Override
    public Bet getBetFromLine(String betLine) throws BetException {
        String[] elements = betLine.trim().split("\\s+");

        if (isAllArgumentsPresent(elements)) {
            Player player = getPlayerFromLine(elements[PLAYER_NAME_ARG]);
            BetKind betKind = getBetKind(elements[BET_ARG]);
            double amount = getAmount(elements[AMOUNT_ARG]);
            return new Bet(player, betKind, amount);
        }
        throw new BetException("Missing elements");
    }

    private Player getPlayerFromLine(String element) throws BetException {
        return players.stream().filter(x -> x.getName().equals(element)).findFirst().orElseThrow(() -> new BetException("Player not found"));
    }

    private boolean isAllArgumentsPresent(String[] elements) {
        return elements.length == 3;
    }

    private double getAmount(String element) throws BetException {
        try {
            return Double.parseDouble(element);
        } catch (NumberFormatException e) {
            throw new BetException("Malformated amount");
        }
    }

    private BetKind getBetKind(String element) {
        BetKindFactory betKindFactory = new BetKindFactory(element);
        return betKindFactory.getBetKind();
    }

}
