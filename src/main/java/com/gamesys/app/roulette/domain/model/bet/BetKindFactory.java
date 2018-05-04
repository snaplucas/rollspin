package com.gamesys.app.roulette.domain.model.bet;

import lombok.AllArgsConstructor;
import org.apache.commons.lang.math.NumberUtils;

@AllArgsConstructor
public class BetKindFactory {

    private final static int MIN_RANGE = 0;
    private final static int MAX_RANGE = 36;

    private final String bet;

    public BetKind getBetKind() {
        int betAsInt = Integer.valueOf(bet);
        if (isValidBetNumber()) {
            return new NumberBet(betAsInt);
        } else if (isBetEvenOdd()) {
            return new EvenOddBet(checkBetType(bet));
        }

        //TODO: throw exception
        return null;
    }


    private boolean isValidBetNumber() {
        return NumberUtils.isNumber(bet) && checkNumberRange(Integer.valueOf(bet));
    }

    private boolean checkNumberRange(int number) {
        return number >= MIN_RANGE && number <= MAX_RANGE;
    }

    private boolean isBetEvenOdd() {
        return true;
    }

    private BetType checkBetType(String bet) {
        switch (bet) {
            case "EVEN":
                return BetType.EVEN;
            case "ODD":
                return BetType.ODD;
            default:
                return null;
            // TODO: throws exeption
        }
    }
}
