package com.gamesys.application.service;

import com.gamesys.application.exceptions.BetException;
import com.gamesys.domain.model.bet.Bet;
import com.gamesys.domain.model.bet.IBetService;
import com.gamesys.domain.model.bet.NumberBet;
import com.gamesys.domain.model.player.Player;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class BetServiceTest {

    private List<Player> players = Arrays.asList(new Player("Barbara"), new Player("Tiki_Monkey"));


    @Test
    public void getBetSucessTest() throws BetException {
        IBetService IBetService = new BetService(players);
        String betLine = "Tiki_Monkey 2 1.0";

        Bet bet = IBetService.getBetFromLine(betLine);

        Assert.assertTrue(bet != null);
        Assert.assertTrue(bet.getAmount() == 1.0);
        Assert.assertTrue(bet.getBetKind().equals(new NumberBet(2)));
    }


    @Test(expected = BetException.class)
    public void getBetPlayerNotFoundTest() throws BetException {
        IBetService IBetService = new BetService(players);
        String betLine = "Tiki_Tiki 2 1.0";

        IBetService.getBetFromLine(betLine);
    }

    @Test(expected = BetException.class)
    public void getBetMissingElementsTest() throws BetException {
        IBetService IBetService = new BetService(players);
        String betLine = "Tiki_Monkey";

        IBetService.getBetFromLine(betLine);
    }
}