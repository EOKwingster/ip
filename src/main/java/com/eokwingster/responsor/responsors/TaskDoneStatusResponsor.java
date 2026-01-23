package com.eokwingster.responsor.responsors;

import com.eokwingster.command.Step;
import com.eokwingster.data.ChatData;
import com.eokwingster.data.task.Task;
import com.eokwingster.responsor.Response;
import com.eokwingster.responsor.Responsor;

import java.util.List;

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
    public Response.Builder response(String argument, ChatData chatData, Response.Builder builtResponse, List<Step> steps) {
        List<Task> tasks = chatData.getTasks();
        int index;
        Task task;
        try {
            index = Integer.parseInt(argument);
            task = tasks.get(index - 1);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            return builtResponse.appendMessages(String.format("Invalid task number: %s", argument));
        }
        task.setIsDone(isDone);
        return builtResponse.appendMessages(String.format("This task has been %s done.", isDone ? "marked" : "unmarked"))
                        .appendMessages("   " + task);
    }
}
