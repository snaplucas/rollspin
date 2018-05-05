package com.gamesys.app.domain.model.player;

import com.gamesys.app.application.dto.PlayerResultDto;
import com.gamesys.app.application.service.PlayerResultService;
import com.gamesys.app.domain.model.bet.Bet;
import com.gamesys.app.domain.model.bet.BetType;
import com.gamesys.app.domain.model.bet.EvenOddBet;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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

    @Test
    public void updatePlayerTotalWinAndBet() {
        List<Player> playerList = Arrays.asList(new Player("Barbara", 2.0, 1.0), new Player("Tiki_Monkey", 1.0, 3.0));
        List<Bet> betList = Collections.singletonList(new Bet(playerList.get(0), new EvenOddBet(BetType.EVEN), 3.0));
        PlayerResultService playerResultService = new PlayerResultService();
        List<PlayerResultDto> playerResultDtos = betList.stream().map(x -> playerResultService.getPlayerResult(x, 12)).collect(Collectors.toList());


        playerList.forEach(x -> x.setTotalWin(x.getTotalWin()
                + playerResultDtos.stream()
                .filter(y -> Objects.equals(y.getPlayerName(), x.getName())).findFirst().orElse(new PlayerResultDto()).getWinnings()));


        playerList.forEach(x -> x.setTotalBet(x.getTotalBet()
                + betList.stream()
                .filter(y -> Objects.equals(y.getPlayer().getName(), x.getName())).findFirst().orElse(new Bet()).getAmount()));

        Assert.assertTrue(playerList.size() == 2);
        Player player = playerList.get(0);
        Assert.assertTrue(player.getTotalWin() == 8.0);
        Assert.assertTrue(player.getTotalBet() == 4.0);
    }
}