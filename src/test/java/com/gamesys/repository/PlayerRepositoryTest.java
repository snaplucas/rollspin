package com.gamesys.repository;

import com.gamesys.application.exceptions.PlayerException;
import com.gamesys.domain.model.player.Player;
import org.junit.Assert;
import org.junit.Test;

import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

public class PlayerRepositoryTest {

    @Test
    public void getPlayersSucess() throws URISyntaxException, PlayerException {
        String filePath = String.valueOf(Paths.get(getClass().getClassLoader().getResource("fileTest.txt").toURI()));
        PlayerRepository playerRepository = new PlayerRepository(filePath);
        List<Player> players = playerRepository.getPlayers();

        Assert.assertTrue(players.size() == 2);
        Assert.assertTrue(players.stream().anyMatch(x -> Objects.equals(x.getName(), "Barbara")));
    }

    @Test(expected = PlayerException.class)
    public void getPlayersFail() throws URISyntaxException, PlayerException {
        String filePath = String.valueOf(Paths.get(getClass().getClassLoader().getResource("fileError.txt").toURI()));
        PlayerRepository playerRepository = new PlayerRepository(filePath);
        playerRepository.getPlayers();
    }
}