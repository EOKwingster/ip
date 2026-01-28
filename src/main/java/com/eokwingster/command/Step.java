package com.eokwingster.command;

import com.eokwingster.command.keyword.Keyword;

/**
 * A record class to represent a step consist of a keyword and an argument.
 * @param keyword
 * @param argument
 */
public record Step(Keyword keyword, String alias, String argument) {
    @Override
    public String toString() {
        if (argument.isEmpty()) {
            return alias;
        }
        return alias + " " + argument;
    }
}
