package com.eokwingster.command.keyword;

import com.eokwingster.data.ChatData;

public abstract class NotBlankArgumentKeyword implements Keyword {
    @Override
    public void validateStep(String alias, String argument, ChatData chatData) throws IllegalArgumentException {
        if (argument.isBlank()) {
            throw new IllegalArgumentException();
        }
    }
}
