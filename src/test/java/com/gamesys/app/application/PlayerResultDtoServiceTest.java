package com.gamesys.app.application;

import com.gamesys.app.application.service.PlayerResultService;
import com.gamesys.app.domain.model.bet.*;
import com.gamesys.app.domain.model.player.Outcome;
import com.gamesys.app.domain.model.player.Player;
import com.gamesys.app.application.dto.PlayerResultDto;
import org.junit.Assert;
import org.junit.Test;

public class PlayerResultDtoServiceTest {

    private PlayerResultService playerResultService = new PlayerResultService();

    @Test
    public void getPlayerResultTest_01() {
        String name = "Barbara";
        BetKind betKind = new EvenOddBet(BetType.EVEN);
        Bet bet = new Bet(new Player(name), betKind, 3.0);
        PlayerResultDto playerResultDto = playerResultService.getPlayerResult(bet, 4);

        PlayerResultDto expectedPlayerResultDto = new PlayerResultDto(name, betKind.toString(), Outcome.WIN, 6.0);
        Assert.assertEquals(playerResultDto, expectedPlayerResultDto);
    }

    @Test
    public void getPlayerResultTest_02() {
        String name = "Tiki_Monkey";
        BetKind betKind = new NumberBet(2);
        Bet bet = new Bet(new Player(name), betKind, 1.0);
        PlayerResultDto playerResultDto = playerResultService.getPlayerResult(bet, 12);

        PlayerResultDto expectedPlayerResultDto = new PlayerResultDto(name, betKind.toString(), Outcome.LOSE, 0.0);
        Assert.assertEquals(playerResultDto, expectedPlayerResultDto);
    }

}