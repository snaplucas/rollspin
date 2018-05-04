package com.gamesys.app.roulette.domain.model.player;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@Getter
public class Player {

    private final String name;
    private double totalWin;
    private double totalBet;

    public Player(final String name) {
        this(name, 0.0, 0.0);
    }

    public static Player buildPlayer(String playerString) {
        List<String> list = Arrays.asList(playerString.split(","));
        if (list.size() == 1) {
            return new Player(list.get(0));
        } else {
            return getAllArgumentsPlayer(list);
        }
    }

    private static Player getAllArgumentsPlayer(List<String> list) {
        if (list.size() == 3) {
            return new Player(list.get(0), Double.parseDouble(list.get(1)), Double.parseDouble(list.get(1)));
        } else {
            // TODO: fix it
            return null;
        }
    }

}
