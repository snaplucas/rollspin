package com.gamesys.domain.model.bet;

import com.gamesys.domain.model.player.Player;
import org.junit.Assert;
import org.junit.Test;

public class BetTest {

    @Test
    public void betAmountValid() {
        Bet bet = new Bet(new Player("Barbara"), new EvenOddBet(BetType.EVEN), 6.0);
        Assert.assertTrue(bet.isValidAmount());

    }

    @Test
    public void betAmountInvalid() {
        Bet bet = new Bet(new Player("Barbara"), new EvenOddBet(BetType.EVEN), 0.0);
        Assert.assertFalse(bet.isValidAmount());
    }

}