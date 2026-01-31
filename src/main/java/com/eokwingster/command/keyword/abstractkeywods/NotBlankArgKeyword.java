package com.eokwingster.command.keyword.abstractkeywods;

import com.eokwingster.command.keyword.Keyword;
import com.eokwingster.data.ChatData;

/**
 * keyword that requires not blank argument
 */
public abstract class NotBlankArgKeyword implements Keyword {
    @Override
    public void validateStep(String alias, String argument, ChatData chatData) throws IllegalArgumentException {
        if (argument.isBlank()) {
            throw new IllegalArgumentException();
        }
    }
}
