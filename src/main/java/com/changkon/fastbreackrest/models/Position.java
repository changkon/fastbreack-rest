package com.changkon.fastbreackrest.models;

public enum Position {
    CENTER("C"),
    POWERFORWARD("PF"),
    SMALLFORWARD("SF"),
    SHOOTINGGUARD("SG"),
    POINTGUARD("PG");

    private String position;

    Position(String position) {
        this.position = position;
    }

    public String getPosition() {
        return this.position;
    }

    @Override
    public String toString() {
        return this.position;
    }
}
