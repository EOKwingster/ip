package com.eokwingster.responsors;

import com.eokwingster.data.ChatData;
import com.eokwingster.data.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskDoneStatusResponsor implements Responsor {
    private final boolean isDone;

    public TaskDoneStatusResponsor(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * mark/unmark the task done depends on the isDone member variable
     * @param taskNo number of task in list displayed
     * @param chatData the data stored in current chat
     * @return mark/unmark message, or invalid task number warning.
     */
    @Override
    public Response response(String taskNo, ChatData chatData) {
        List<Task> tasks = chatData.getTasks();
        int index;
        Task task;
        try {
            index = Integer.parseInt(taskNo);
            task = tasks.get(index - 1);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            return Response.of(String.format("Invalid task number: %s", taskNo));
        }
        task.setIsDone(isDone);
        List<String> messages = new ArrayList<>();
        messages.add(String.format("This task has been %s done.", isDone ? "marked" : "unmarked"));
        messages.add("   " + task);
        return Response.of(messages);
    }
}
