package com.eokwingster.command.keyword;

import com.eokwingster.data.ChatData;

public abstract class AddTaskKeyword extends NotBlankArgumentKeyword {
    @Override
    public void validateCommandStep(String alias, String argument, ChatData chatData) throws IllegalArgumentException {
        try {
            super.validateCommandStep(alias, argument, chatData);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("The description of a task can not be blank!");
        }
    }
}
