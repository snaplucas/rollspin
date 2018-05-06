package com.gamesys.app.domain.model.player;

import com.gamesys.app.application.exceptions.PlayerException;

import java.util.List;

public interface PlayerRepository {

    List<Player> getPlayers() throws PlayerException;
}
