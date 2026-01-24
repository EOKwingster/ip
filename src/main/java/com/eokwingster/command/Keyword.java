package com.eokwingster.command;

public enum Keyword {
    NEW, HI,
    EXIT, BYE,
    TODO, DEADLINE, EVENT,
    DELETE,
    LIST,
    MARK, UNMARK,
    FROM(true), TO(true), BY(true),
    UNKNOWN;
    private final boolean isModifier;

    Keyword(boolean isModifier) {
        this.isModifier = isModifier;
    }

    Keyword() {
        this.isModifier = false;
    }

    public boolean isModifier() {
        return this.isModifier;
    }


    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}
