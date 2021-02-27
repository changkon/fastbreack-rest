package com.changkon.fastbreackrest.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class Season {
    @Getter
    @Setter
    private List<Team> teams;

    @Getter
    @Setter
    private String season;

    @Getter
    @Setter
    private Team winner;
}
