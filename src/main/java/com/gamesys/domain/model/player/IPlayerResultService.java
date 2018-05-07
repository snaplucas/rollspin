package com.gamesys.domain.model.player;

import com.gamesys.application.dto.PlayerResultDto;
import com.gamesys.domain.model.bet.Bet;

public interface IPlayerResultService {

    PlayerResultDto getPlayerResult(Bet bet, int resultNumber);
}
