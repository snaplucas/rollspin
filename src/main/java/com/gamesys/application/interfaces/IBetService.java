package com.gamesys.application.interfaces;

import com.gamesys.application.exceptions.BetException;
import com.gamesys.domain.model.bet.Bet;

public interface IBetService {

    Bet getBetFromLine(String betLine) throws BetException;
}
