package com.eokwingster.command.keyword.abstractkeywods;

import com.eokwingster.command.keyword.Keyword;
import com.eokwingster.data.ChatData;

public abstract class NotBlankArgumentKeyword implements Keyword {
    @Override
    public void validateStep(String alias, String argument, ChatData chatData) throws IllegalArgumentException {
        if (argument.isBlank()) {
            throw new IllegalArgumentException();
        }
    }
}
