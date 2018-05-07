package com.gamesys.domain.model.player;

import com.gamesys.application.dto.PlayerResultDto;
import com.gamesys.domain.model.bet.Bet;
import com.gamesys.domain.model.player.Player;

import java.util.List;

public interface IPlayerService {

    List<Player> getPlayerTotalWinAndBet(List<Player> players, List<PlayerResultDto> playerResultDtos, List<Bet> betList);
}
