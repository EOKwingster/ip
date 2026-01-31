package com.eokwingster.responsor.responsors;

import java.util.List;

import com.eokwingster.command.Step;
import com.eokwingster.data.ChatData;
import com.eokwingster.data.task.Task;
import com.eokwingster.responsor.Response;
import com.eokwingster.responsor.Responsor;

/**
 * Responsor that change the done status of a task.
 * Can be constructed with boolean isDone to set the target status.
 */
public class TaskDoneStatusResponsor implements Responsor {
    private final boolean isDone;

    public TaskDoneStatusResponsor(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * mark/unmark the task done depends on the isDone member variable
     * @return mark/unmark message, or invalid task number warning.
     */
    @Override
    public Response.Builder response(
            String argument,
            ChatData chatData,
            Response.Builder builtResponse,
            List<Step> steps) {
        Task task = chatData.getTaskAt(Integer.parseInt(argument) - 1);
        task.setIsDone(isDone);
        builtResponse.appendMessages(String.format("This task has been %s done.", isDone ? "marked" : "unmarked"))
                .appendMessages("   " + task)
                .addTags(Response.Tag.SAVE);
        return builtResponse;
    }
}
