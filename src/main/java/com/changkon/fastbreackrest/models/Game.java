package com.changkon.fastbreackrest.models;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

public class Game {
    @Getter
    @Setter
    private Team homeTeam;
    @Getter
    @Setter
    private Team awayTeam;
    @Getter
    @Setter
    private String season;
    @Getter
    @Setter
    private LocalDateTime dateTimeEst;
    @Getter
    @Setter
    private String arenaName;
    @Getter
    @Setter
    private boolean isFinished;
    @Getter
    @Setter
    private int homeTeamScore;
    @Getter
    @Setter
    private int[] homeTeamPointsByQuarter;
    @Getter
    @Setter
    private int awayTeamScore;
    @Getter
    @Setter
    private int[] awayTeamPointsByQuarter;
}
