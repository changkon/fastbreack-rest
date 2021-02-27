package com.changkon.fastbreackrest.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

public class Scoreboard {
    @Getter
    @Setter
    private String league;

    @Getter
    @Setter
    private String gameId;

    @Getter
    @Setter
    private LocalDateTime dateTimeEst;

    @Getter
    @Setter
    private boolean isFinished;

    @Getter
    @Setter
    private String season;

    @Getter
    @Setter
    private Team homeTeam;

    @Getter
    @Setter
    private Team awayTeam;

    @Getter
    @Setter
    private String arenaName;

    public static class Team {
        @Getter
        @Setter
        private String city;
        @Getter
        @Setter
        private String abbr;
        @Getter
        @Setter
        private String name;
        @Getter
        @Setter
        private String teamId;
        @Getter
        @Setter
        private int points;
        @Getter
        @Setter
        private int[] pointsByQuarter;
        @Getter
        @Setter
        private int wins;
        @Getter
        @Setter
        private int loss;
        @Getter
        @Setter
        private int homeWins;
        @Getter
        @Setter
        private int homeLoss;
        @Getter
        @Setter
        private int awayWins;
        @Getter
        @Setter
        private int awayLoss;
        @Getter
        @Setter
        private int currentSeed;
        @Getter
        @Setter
        private String conference;
    }

}
