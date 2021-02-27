package com.changkon.fastbreackrest.models;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

public abstract class RosterIndividual {
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private LocalDate dob;
    @Getter
    @Setter
    private Role role;
    @Getter
    @Setter
    private int exp;
}
