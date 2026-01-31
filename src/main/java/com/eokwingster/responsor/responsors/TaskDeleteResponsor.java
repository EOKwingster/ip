package com.eokwingster.responsor.responsors;

import java.util.List;

import com.eokwingster.command.Step;
import com.eokwingster.data.ChatData;
import com.eokwingster.data.task.Task;
import com.eokwingster.responsor.Response;
import com.eokwingster.responsor.Responsor;

/**
 * Responsor that delete a task by index
 */
public class TaskDeleteResponsor implements Responsor {
    @Override
    public Response.Builder response(
            String argument,
            ChatData chatData,
            Response.Builder builtResponse,
            List<Step> steps) {
        Task task = chatData.removeTaskAt(Integer.parseInt(argument) - 1);
        builtResponse.appendMessages(
                "This task has been removed:",
                "  " + task,
                "Now you have " + chatData.getTaskCount() + " tasks."
        ).addTags(Response.Tag.SAVE);
        return builtResponse;
    }
}
