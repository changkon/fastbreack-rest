package com.changkon.fastbreackrest.models;

public enum BasketballLeague {
    NBA("NBA"),
    GLEAGUE("GLEAGUE");

    private String league;

    BasketballLeague(String league) {
        this.league = league;
    }

    public String getLeague() {
        return this.league;
    }
}
