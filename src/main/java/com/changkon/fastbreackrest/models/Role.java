package com.changkon.fastbreackrest.models;

public enum Role {
    COACH("COACH"),
    ASSISTANTCOACH("ASSISTANTCOACH"),
    STARTER("STARTER"),
    SIXTHMAN("SIXTHMAN"),
    RESERVE("RESERVE");

    private String role;

    Role(String role) {
        this.role = role;
    }

    public String getRole() {
        return this.role;
    }

    @Override
    public String toString() {
        return this.role;
    }
}
