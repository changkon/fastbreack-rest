package com.changkon.fastbreackrest.models;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

public class Player extends RosterIndividual {

    @Getter
    @Setter
    private int weight;

    @Getter
    @Setter
    private int height;

    @Getter
    @Setter
    private String school;

    @Getter
    @Setter
    private String playerId;

    @Getter
    @Setter
    private List<Position> position;
}
