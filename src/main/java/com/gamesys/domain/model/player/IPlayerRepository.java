package com.gamesys.domain.model.player;

import com.gamesys.application.exceptions.PlayerException;

import java.util.List;

public interface IPlayerRepository {

    List<Player> getPlayers() throws PlayerException;
}
