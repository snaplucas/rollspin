package com.gamesys.app;

import com.gamesys.app.roulette.domain.model.player.Player;
import com.gamesys.app.roulette.domain.model.player.PlayerRepository;
import com.gamesys.app.roulette.domain.model.roulette.Table;
import com.gamesys.app.roulette.repository.PlayerRepositoryImpl;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Game {

    private Table table;

    public Game(String[] args) {
        String arg = getFirstArgument(args);
        if (Objects.equals(arg, "")) {
            System.out.println("No input file was provided");
            System.exit(0);
        }
        PlayerRepository playerRepository = new PlayerRepositoryImpl(getFirstArgument(args));
        List<Player> players = playerRepository.getPlayers();
        this.table = new Table(players);
    }

    private String getFirstArgument(String[] args) {
        if (args != null && args.length == 1) {
            return args[0];
        }
        return "";
    }

    public static void main(String[] args) throws IOException {
        System.out.println("Console Table Game");
        System.out.println("Type 'quit' to exit the game");
        Game game = new Game(args);
        game.play();
    }


    private void play() throws IOException {
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning) {
            table.spin();
            final String line = scanner.nextLine();
            if (line.equals("quit")) {
                isRunning = false;
            } else {
                table.placeBet(line);
            }
        }
    }
}
