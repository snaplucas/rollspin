package com.gamesys.domain.model.bet;


import org.junit.Assert;
import org.junit.Test;

public class BetKindFactoryTest {

    @Test
    public void betKindNumberSucess() {
        BetKindFactory betKindFactory = new BetKindFactory("2");
        BetKind betKind = betKindFactory.getBetKind();
        BetKind expectedBetKind = new NumberBet(2);

        Assert.assertEquals(betKind.toString(), expectedBetKind.toString());

    }

    @Test
    public void betKindEvenOddSucess() {
        BetKindFactory betKindFactory = new BetKindFactory("EVEN");
        BetKind betKind = betKindFactory.getBetKind();
        BetKind expectedBetKind = new EvenOddBet(BetType.EVEN);

        Assert.assertEquals(betKind.toString(), expectedBetKind.toString());
    }

    @Test
    public void betKindFails() {
        BetKindFactory betKindFactory = new BetKindFactory("aaa");
        BetKind betKind = betKindFactory.getBetKind();

        Assert.assertEquals(betKind, null);
    }

}