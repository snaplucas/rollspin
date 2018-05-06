package com.gamesys.app.application.service;

import com.gamesys.app.application.exceptions.BetException;
import com.gamesys.app.application.service.BetService;
import com.gamesys.app.domain.model.bet.Bet;
import com.gamesys.app.domain.model.bet.NumberBet;
import com.gamesys.app.domain.model.player.Player;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class BetServiceTest {

    private List<Player> players = Arrays.asList(new Player("Barbara"), new Player("Tiki_Monkey"));


    @Test
    public void getBetSucessTest() throws BetException {
        BetService betService = new BetService(players);
        String betLine = "Tiki_Monkey 2 1.0";

        Bet bet = betService.getBetFromLine(betLine);

        Assert.assertTrue(bet != null);
        Assert.assertTrue(bet.getAmount() == 1.0);
        Assert.assertTrue(bet.getBetKind().equals(new NumberBet(2)));
    }


    @Test(expected = BetException.class)
    public void getBetPlayerNotFoundTest() throws BetException {
        BetService betService = new BetService(players);
        String betLine = "Tiki_Tiki 2 1.0";

        betService.getBetFromLine(betLine);
    }

    @Test(expected = BetException.class)
    public void getBetMissingElementsTest() throws BetException {
        BetService betService = new BetService(players);
        String betLine = "Tiki_Monkey";

        betService.getBetFromLine(betLine);
    }
}