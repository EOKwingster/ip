package com.eokwingster.responsor.responsors;

import com.eokwingster.command.Step;
import com.eokwingster.data.ChatData;
import com.eokwingster.data.task.Task;
import com.eokwingster.responsor.Response;
import com.eokwingster.responsor.Responsor;

import java.util.List;

public class TaskDeleteResponsor implements Responsor {
    @Override
    public Response.Builder response(String argument, ChatData chatData, Response.Builder builtResponse, List<Step> steps) {
        int index;
        Task task;
        try {
            index = Integer.parseInt(argument) - 1;
            task = chatData.getTasks().remove(index);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            return builtResponse.appendWarning(String.format("Invalid task number: %s, use /list to check available numbers", argument));
        }
        return builtResponse.appendMessages(
                "This task has been removed:",
                "  " + task,
                "Now you have " + chatData.getTasks().size() + " tasks."
        );
    }
}
