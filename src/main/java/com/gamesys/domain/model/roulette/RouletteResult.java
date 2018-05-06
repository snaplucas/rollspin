package com.gamesys.domain.model.roulette;

import com.gamesys.domain.model.player.Player;
import com.gamesys.application.dto.PlayerResultDto;

import java.util.List;

public class RouletteResult {

    private final String skipLine = "\n";
    private final String spaceBetweenWords = "       ";

    String getDefaultGameResult(List<PlayerResultDto> playerResultDtos, int resultNumber) {
        StringBuilder row = new StringBuilder("\n");
        row.append(getDefaultHeader(resultNumber));
        playerResultDtos.forEach(x -> row.append(x.getPlayerName())
                .append(spaceBetweenWords)
                .append(x.getBet()).append(spaceBetweenWords)
                .append(spaceBetweenWords)
                .append(x.getOutcome())
                .append(spaceBetweenWords)
                .append(x.getWinnings())
                .append(skipLine));

        return row.toString();
    }

    private String getDefaultHeader(int resultNumber) {
        StringBuilder row = new StringBuilder(skipLine);
        row.append("Number: ").append(resultNumber)
                .append(skipLine)
                .append("Player")
                .append(spaceBetweenWords)
                .append("Bet")
                .append(spaceBetweenWords)
                .append("Outcome")
                .append(spaceBetweenWords)
                .append("Winnings")
                .append(skipLine)
                .append("- - -")
                .append(skipLine);
        return row.toString();
    }


    String getPlayerGameResult(List<Player> players) {
        StringBuilder row = new StringBuilder("\n");
        row.append(getPlayersHeader());
        players.forEach(x-> row.append(x.getName())
                .append(spaceBetweenWords)
                .append(x.getTotalWin())
                .append(spaceBetweenWords)
                .append(x.getTotalBet())
                .append(skipLine));
        return row.toString();
    }

    private String getPlayersHeader() {
        StringBuilder row = new StringBuilder(skipLine);
        row.append(skipLine)
                .append("Player")
                .append(spaceBetweenWords)
                .append("TotalWin")
                .append(spaceBetweenWords)
                .append("TotalBet")
                .append(skipLine)
                .append("- - -")
                .append(skipLine);
        return row.toString();
    }
}
