package com.eokwingster.command.keyword.abstractkeywods;

import com.eokwingster.command.keyword.Keyword;
import com.eokwingster.data.ChatData;

/**
 * keyword that require a task index as argument
 */
public abstract class TaskIndexArgKeyword implements Keyword {
    @Override
    public void validateStep(String alias, String argument, ChatData chatData) throws IllegalArgumentException {
        try {
            int i = Integer.parseInt(argument) - 1;
            if (chatData.getTaskCount() <= i || i < 0) {
                throw new IllegalArgumentException(String.format(
                        "You don't have a task with this number: %s,"
                                + "use /list to list your tasks",
                        argument
                ));
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(String.format("Invalid task number: %s", argument));
        }
    }
}
