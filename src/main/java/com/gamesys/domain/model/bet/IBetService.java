package com.gamesys.domain.model.bet;

import com.gamesys.application.exceptions.BetException;

public interface IBetService {

    Bet getBetFromLine(String betLine) throws BetException;
}
