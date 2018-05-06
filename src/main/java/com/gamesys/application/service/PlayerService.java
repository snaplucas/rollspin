package com.gamesys.application.service;

import com.gamesys.application.dto.PlayerResultDto;
import com.gamesys.application.interfaces.IPlayerService;
import com.gamesys.domain.model.bet.Bet;
import com.gamesys.domain.model.player.Player;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

public class PlayerService implements IPlayerService {

    @Override
    public List<Player> getPlayerTotalWinAndBet(List<Player> players, List<PlayerResultDto> playerResultDtos, List<Bet> betList){
        List<Player> playerList = new CopyOnWriteArrayList<>(players);
        if (isResultComming(playerResultDtos)) {
            playerList.forEach(x -> x.setTotalWin(x.getTotalWin()
                    + playerResultDtos.stream()
                    .filter(y -> Objects.equals(y.getPlayerName(), x.getName()))
                    .findFirst()
                    .orElse(new PlayerResultDto()).getWinnings()));

            playerList.forEach(x -> x.setTotalBet(x.getTotalBet()
                    + betList.stream()
                    .filter(y -> Objects.equals(y.getPlayer().getName(), x.getName()))
                    .findFirst()
                    .orElse(new Bet()).getAmount()));
        }
        return playerList;
   }

    private boolean isResultComming(List<PlayerResultDto> playerResultDtos) {
        return playerResultDtos.size() > 0;
    }
}
