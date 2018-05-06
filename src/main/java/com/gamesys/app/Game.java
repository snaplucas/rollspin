package com.gamesys.app;

import com.gamesys.app.application.exceptions.PlayerException;
import com.gamesys.app.application.service.PlayerResultService;
import com.gamesys.app.application.service.PlayerService;
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

    public Game(String[] args) throws PlayerException {
        isValidArguments(args);

        PlayerRepository playerRepository = new PlayerRepositoryImpl(getFirstArgument(args));
        try {
            List<Player> players = playerRepository.getPlayers();
            this.roulette = new Roulette(players, new PlayerResultService(), new PlayerService());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }

    private void isValidArguments(String[] args) {
        String arg = getFirstArgument(args);
        if (Objects.equals(arg, "")) {
            playersNotFound();
        }
    }

    private void playersNotFound() {
        System.out.println("No input file was provided");
        System.exit(0);
    }

    private String getFirstArgument(String[] args) {
        if (args != null && args.length == 1) {
            return args[0];
        }
        return "";
    }

    public static void main(String[] args) throws IOException, PlayerException {
        System.out.println("Console Roulette Game");
        System.out.println("Type 'quit' to exit the game");
        Game game = new Game(args);
        game.play();
    }

    private void play() throws IOException {
        Scanner scanner = new Scanner(System.in);
        roulette.spin();

        while (true) {
            final String line = scanner.nextLine();
            if (line.equals("quit")) {
                System.exit(0);
            } else {
                roulette.placeBet(line);
            }
        }
    }
}
