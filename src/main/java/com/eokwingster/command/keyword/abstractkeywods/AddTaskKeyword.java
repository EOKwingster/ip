package com.eokwingster.command.keyword.abstractkeywods;

import com.eokwingster.command.keyword.CommandRoot;
import com.eokwingster.data.ChatData;

/**
 * keyword of task adding
 */
public abstract class AddTaskKeyword extends NotBlankArgKeyword implements CommandRoot {
    @Override
    public void validateStep(String alias, String argument, ChatData chatData) throws IllegalArgumentException {
        try {
            super.validateStep(alias, argument, chatData);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("The description of a task can not be blank!");
        }
    }
}
