package com.gamesys.app.repository;

import com.gamesys.app.roulette.domain.model.player.Player;
import com.gamesys.app.roulette.repository.PlayerRepositoryImpl;
import org.junit.Test;

import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.List;

public class PlayerRepositoryImplTest {

    @Test
    public void getPlayersSucess() throws URISyntaxException {
        String filePath = String.valueOf(Paths.get(getClass().getClassLoader().getResource("fileTest.txt").toURI()));
        PlayerRepositoryImpl playerRepository = new PlayerRepositoryImpl(filePath);
        List<Player> players = playerRepository.getPlayers();
        System.out.println(players);
        // TODO: assert

    }

    @Test
    public void getPlayersFail() {

    }
}