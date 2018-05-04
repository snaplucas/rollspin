package com.gamesys.app.roulette.repository;

import com.gamesys.app.roulette.domain.model.player.Player;
import com.gamesys.app.roulette.domain.model.player.PlayerRepository;
import lombok.AllArgsConstructor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@AllArgsConstructor
public class PlayerRepositoryImpl implements PlayerRepository {

    private final String playersFilePath;

    @Override
    public List<Player> getPlayers() {
        try {
            File file = new File(playersFilePath);
            Path path = Paths.get(file.toURI());
            Stream<String> lines = Files.lines(path);
            return lines.map(Player::buildPlayer).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();

        }
        // TODO: fix it
        return null;
    }

}
