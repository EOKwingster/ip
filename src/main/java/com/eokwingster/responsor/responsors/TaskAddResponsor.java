package com.eokwingster.responsor.responsors;

import com.eokwingster.data.ChatData;
import com.eokwingster.data.task.Task;
import com.eokwingster.data.task.TaskType;
import com.eokwingster.responsor.Response;
import com.eokwingster.responsor.Responsor;

/**
 * Responsor that adding a task.
 * Can be constructed with task type for adding different types of tasks.
 */
public class TaskAddResponsor implements Responsor {
    private final TaskType taskType;

    public TaskAddResponsor(TaskType taskType) {
        this.taskType = taskType;
    }

    @Override
    public Response.Builder response(
            String argument,
            ChatData chatData,
            Response.Builder builtResponse) {
        Task task = taskType.createTask(argument);
        chatData.addTask(task);
        return builtResponse.appendMessage("Task added:")
                .appendMessage("  %s", task)
                .appendMessage("Now you have " + chatData.getTaskCount() + " tasks.")
                .addTags(Response.Tag.SAVE);
    }
}
