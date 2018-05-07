package com.gamesys.domain.model.player;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
@AllArgsConstructor
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
        }
        return getAllArgumentsPlayer(list);
    }

    private static Player getAllArgumentsPlayer(List<String> list) {
        if (list.size() == 3) {
            return new Player(list.get(0), Double.parseDouble(list.get(1)), Double.parseDouble(list.get(2)));
        }
        return null;
    }
}
