package com.gamesys.repository;

import com.gamesys.application.exceptions.PlayerException;
import com.gamesys.domain.model.player.Player;
import com.gamesys.domain.model.player.IPlayerRepository;
import lombok.AllArgsConstructor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class PlayerRepository implements IPlayerRepository {

    private final String playersFilePath;

    @Override
    public List<Player> getPlayers() throws PlayerException {
        try {
            File file = new File(playersFilePath);
            Path path = Paths.get(file.toURI());
            List<String> lines = Files.lines(path).collect(Collectors.toList());
            if (lines.size() > 0) {
                return lines.stream().map(Player::buildPlayer).collect(Collectors.toList());
            }
            throw new PlayerException("Could not load players file");
        } catch (IOException e) {
            throw new PlayerException("Error trying load players");
        }
    }

}
