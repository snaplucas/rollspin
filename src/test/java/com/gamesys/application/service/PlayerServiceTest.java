package com.gamesys.application.service;


import com.gamesys.application.dto.PlayerResultDto;
import com.gamesys.application.interfaces.IPlayerResultService;
import com.gamesys.application.interfaces.IPlayerService;
import com.gamesys.domain.model.bet.Bet;
import com.gamesys.domain.model.bet.BetType;
import com.gamesys.domain.model.bet.EvenOddBet;
import com.gamesys.domain.model.player.Player;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class PlayerServiceTest {


    @Test
    public void getPlayerTotalWinAndBet_01() {

        List<Player> playerList = Arrays.asList(new Player("Barbara", 2.0, 1.0), new Player("Tiki_Monkey", 1.0, 3.0));
        List<Bet> betList = Collections.singletonList(new Bet(playerList.get(0), new EvenOddBet(BetType.EVEN), 3.0));
        IPlayerResultService IPlayerResultService = new PlayerResultService();
        List<PlayerResultDto> playerResultDtos = betList.stream().map(x -> IPlayerResultService.getPlayerResult(x, 12)).collect(Collectors.toList());

        IPlayerService IPlayerService = new PlayerService();
        playerList = IPlayerService.getPlayerTotalWinAndBet(playerList, playerResultDtos, betList);

        Assert.assertTrue(playerList.size() == 2);
        Player player = playerList.get(0);
        Assert.assertTrue(player.getTotalWin() == 8.0);
        Assert.assertTrue(player.getTotalBet() == 4.0);
    }


    @Test
    public void getPlayerTotalWinAndBet_02() {

    }
}