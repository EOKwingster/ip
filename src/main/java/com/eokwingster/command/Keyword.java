package com.eokwingster.command;

public enum Keyword {
    NEW, HI,
    EXIT, BYE,
    TODO, DEADLINE, EVENT,
    LIST,
    MARK, UNMARK,
    FROM(true);
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
}
