package com.gamesys.app.domain.model.player;

import org.junit.Assert;
import org.junit.Test;

public class PlayerTest {

    @Test
    public void buildPlayerAllArgumentsTest() {
        String playerAsString = "Barbara,2.0,1.0";

        Player player = Player.buildPlayer(playerAsString);
        Player expectedPlayer = new Player("Barbara", 2.0, 1.0);

        Assert.assertEquals(player, expectedPlayer);
    }

    @Test
    public void buildPlayerOneArgumentTest() {
        String playerAsString = "Barbara";

        Player player = Player.buildPlayer(playerAsString);
        Player expectedPlayer = new Player("Barbara", 0.0, 0.0);

        Assert.assertEquals(player, expectedPlayer);
    }

    @Test
    public void buildPlayerFailsTest() {
        String playerAsString = "Barbara,2.0";
        Player player = Player.buildPlayer(playerAsString);

        Assert.assertEquals(player, null);
    }
}