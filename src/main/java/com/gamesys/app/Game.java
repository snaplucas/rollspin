package com.gamesys.app;

import com.gamesys.app.application.PlayerResultService;
import com.gamesys.app.domain.model.player.Player;
import com.gamesys.app.domain.model.player.PlayerRepository;
import com.gamesys.app.domain.model.roulette.Roulette;
import com.gamesys.app.repository.PlayerRepositoryImpl;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Game {

    private Roulette roulette;

    public Game(String[] args) {
        String arg = getFirstArgument(args);
        if (Objects.equals(arg, "")) {
            System.out.println("No input file was provided");
            System.exit(0);
        }
        PlayerRepository playerRepository = new PlayerRepositoryImpl(getFirstArgument(args));
        List<Player> players = playerRepository.getPlayers();
        if (players != null) {
            this.roulette = new Roulette(players, new PlayerResultService());
        } else {
            System.out.println("Couldn't load players");
            System.exit(0);
        }
    }

    private String getFirstArgument(String[] args) {
        if (args != null && args.length == 1) {
            return args[0];
        }
        return "";
    }

    public static void main(String[] args) throws IOException {
        System.out.println("Console Roulette Game");
        System.out.println("Type 'quit' to exit the game");
        Game game = new Game(args);
        game.play();
    }

    private void play() throws IOException {
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning) {
            roulette.spin();
            final String line = scanner.nextLine();
            if (line.equals("quit")) {
                isRunning = false;
            } else {
                roulette.placeBet(line);
            }
        }
    }
}
