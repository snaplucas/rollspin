package com.gamesys.app.domain.model.roulette;

import com.gamesys.app.domain.model.player.Outcome;
import com.gamesys.app.domain.model.player.Player;
import com.gamesys.app.application.dto.PlayerResultDto;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class RouletteResultTest {

    private RouletteResult rouletteResult = new RouletteResult();


    @Test
    public void getDefaultGameResultTest() {
        List<PlayerResultDto> playerResultDtos = Arrays.asList(new PlayerResultDto("Tiki_Monkey", "2", Outcome.LOSE, 0.0)
                , new PlayerResultDto("Barbara", "EVEN", Outcome.WIN, 6.0));

        String result = rouletteResult.getDefaultGameResult(playerResultDtos, 4);
        System.out.println(result);
    }

    @Test
    public void getPlayerGameResultTest() {
        List<Player> playerList = Arrays.asList(new Player("Tiki_Monkey", 1.0, 3.0)
                , new Player("Barbara", 8.0, 4.0));

        String result = rouletteResult.getPlayerGameResult(playerList);
        System.out.println(result);
    }
}