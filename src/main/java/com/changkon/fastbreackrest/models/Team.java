package com.changkon.fastbreackrest.models;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

public class Team {
    @Getter
    @Setter
    private BasketballLeague league;

    @Getter
    @Setter
    private String teamId;

    @Getter
    @Setter
    private String arenaName;

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
    private String conference;

    @Getter
    @Setter
    private LocalDate inceptionDate;

    @Getter
    @Setter
    private List<Player> currentPlayers;

    @Getter
    @Setter
    private List<Coach> currentCoaches;
}
