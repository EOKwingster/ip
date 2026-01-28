package com.eokwingster.responsor.responsors;

import com.eokwingster.command.Step;
import com.eokwingster.data.ChatData;
import com.eokwingster.data.task.Task;
import com.eokwingster.data.task.TaskType;
import com.eokwingster.responsor.Response;
import com.eokwingster.responsor.Responsor;

import java.util.List;

public class TaskAddResponsor implements Responsor {
    private final TaskType taskType;

    public TaskAddResponsor(TaskType taskType) {
        this.taskType = taskType;
    }

    /**
     * add a task into chat data
     * @return task added message
     */
    @Override
    public Response.Builder response(String argument, ChatData chatData, Response.Builder builtResponse, List<Step> steps) {
        Task task = taskType.createTask(argument);
        chatData.addTask(task);
        return builtResponse.appendMessage("Task added:")
                .appendMessage("  %s", task)
                .appendMessage("Now you have " + chatData.getTasks().size() + " tasks.");
    }
}
