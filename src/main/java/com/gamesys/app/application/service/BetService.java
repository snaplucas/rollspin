package com.gamesys.app.application.service;

import com.gamesys.app.application.exceptions.BetException;
import com.gamesys.app.domain.model.bet.Bet;
import com.gamesys.app.domain.model.bet.BetKind;
import com.gamesys.app.domain.model.bet.BetKindFactory;
import com.gamesys.app.domain.model.player.Player;

import java.util.List;
import java.util.Optional;

public class BetService {

    private final List<Player> players;

    private final static int PLAYER_NAME_ARG = 0;
    private final static int BET_ARG = 1;
    private final static int AMOUNT_ARG = 2;

    public BetService(List<Player> players) {
        this.players = players;
    }

    public Bet getBetFromLine(String betLine) throws BetException {
        String[] elements = betLine.trim().split("\\s+");

        if (isAllArgumentsPresent(elements)) {
            Optional<Player> player = getPlayer(elements[PLAYER_NAME_ARG]);
            BetKind betKind = getBetKind(elements[BET_ARG]);
            double amount = getAmount(elements[AMOUNT_ARG]);

            if (player.isPresent()) {
                return new Bet(player.get(), betKind, amount);
            } else {
                throw new BetException("Player not found");
            }
        }
        throw new BetException("Missing elements");
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

    private Optional<Player> getPlayer(String element) {
        return players.stream().filter(x -> x.getName().equals(element)).findFirst();
    }
}
