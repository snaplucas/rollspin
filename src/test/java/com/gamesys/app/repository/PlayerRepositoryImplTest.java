package com.gamesys.app.repository;

import com.gamesys.app.domain.model.player.Player;
import org.junit.Assert;
import org.junit.Test;

import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

public class PlayerRepositoryImplTest {

    @Test
    public void getPlayersSucess() throws URISyntaxException {
        String filePath = String.valueOf(Paths.get(getClass().getClassLoader().getResource("fileTest.txt").toURI()));
        PlayerRepositoryImpl playerRepository = new PlayerRepositoryImpl(filePath);
        List<Player> players = playerRepository.getPlayers();

        Assert.assertTrue(players.size() == 2);
        Assert.assertTrue(players.stream().anyMatch(x -> Objects.equals(x.getName(), "Barbara")));
    }

    @Test
    public void getPlayersFail() throws URISyntaxException {
        String filePath = String.valueOf(Paths.get(getClass().getClassLoader().getResource("fileError.txt").toURI()));
        PlayerRepositoryImpl playerRepository = new PlayerRepositoryImpl(filePath);
        List<Player> players = playerRepository.getPlayers();

        Assert.assertEquals(players, null);
    }
}