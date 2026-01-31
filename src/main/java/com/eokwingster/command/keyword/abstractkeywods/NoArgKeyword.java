package com.eokwingster.command.keyword.abstractkeywods;

import com.eokwingster.command.keyword.Keyword;
import com.eokwingster.data.ChatData;

/**
 * keyword that requires no argument
 */
public abstract class NoArgKeyword implements Keyword {
    @Override
    public void validateStep(String alias, String argument, ChatData chatData) throws IllegalArgumentException {
        if (!argument.isBlank()) {
            throw new IllegalArgumentException(
                    alias
                    + "does not need an argument, the argument: "
                    + argument
                    + " should be blank");
        }
    }
}
