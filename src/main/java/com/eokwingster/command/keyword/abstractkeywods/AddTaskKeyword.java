package com.eokwingster.command.keyword.abstractkeywods;

import com.eokwingster.command.keyword.CommandRoot;
import com.eokwingster.data.ChatData;

public abstract class AddTaskKeyword extends NotBlankArgumentKeyword implements CommandRoot {
    @Override
    public void validateStep(String alias, String argument, ChatData chatData) throws IllegalArgumentException {
        try {
            super.validateStep(alias, argument, chatData);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("The description of a task can not be blank!");
        }
    }
}
