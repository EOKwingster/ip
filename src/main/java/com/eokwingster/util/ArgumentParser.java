package com.eokwingster.util;

import com.eokwingster.data.ChatData;
import com.eokwingster.responsor.Response;

import java.util.Optional;

public abstract class ArgumentParser {
    public static Optional<Integer> parseTaskInt(String argument, ChatData chatData, Response.Builder builtResponse) {
        try {
            int i = Integer.parseInt(argument) - 1;
            chatData.getTasks().get(i);
            return Optional.of(i);
        } catch (NumberFormatException e) {
            builtResponse.appendWarning(String.format("Invalid task number: %s", argument));
            return Optional.empty();
        } catch (IndexOutOfBoundsException e) {
            builtResponse.appendWarning(String.format("You don't have a task with this number: %s, use /list to list your tasks", argument));
            return Optional.empty();
        }
    }
}
