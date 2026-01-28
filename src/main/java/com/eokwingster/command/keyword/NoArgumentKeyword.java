package com.eokwingster.command.keyword;

import com.eokwingster.data.ChatData;

public abstract class NoArgumentKeyword implements Keyword {
    @Override
    public void validateCommandStep(String alias, String argument, ChatData chatData) throws IllegalArgumentException {
        if (!argument.isBlank()) {
            throw new IllegalArgumentException(alias + "does not need an argument, the argument: " + argument + " should be blank");
        }
    }
}
