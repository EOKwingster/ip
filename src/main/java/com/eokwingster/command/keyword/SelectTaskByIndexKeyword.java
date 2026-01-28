package com.eokwingster.command.keyword;

import com.eokwingster.data.ChatData;

public abstract class SelectTaskByIndexKeyword implements Keyword {
    @Override
    public void validateCommandStep(String alias, String argument, ChatData chatData) throws IllegalArgumentException {
        try {
            int i = Integer.parseInt(argument) - 1;
            if (chatData.getTasks().size() <= i || i < 0) {
                throw new IllegalArgumentException(String.format("You don't have a task with this number: %s, use /list to list your tasks", argument));
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(String.format("Invalid task number: %s", argument));
        }
    }
}
