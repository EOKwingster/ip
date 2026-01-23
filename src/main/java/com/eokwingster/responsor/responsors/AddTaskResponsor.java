package com.eokwingster.responsor.responsors;

import com.eokwingster.command.Step;
import com.eokwingster.data.ChatData;
import com.eokwingster.data.task.*;
import com.eokwingster.responsor.Response;
import com.eokwingster.responsor.Responsor;

import java.util.List;

public class AddTaskResponsor implements Responsor {
    private final TaskType taskType;

    public AddTaskResponsor(TaskType taskType) {
        this.taskType = taskType;
    }

    /**
     * add a task into chat data
     * @return task added message
     */
    @Override
    public Response.Builder response(String argument, ChatData chatData, Response.Builder builtResponse, List<Step> steps) {
        Task task = switch (taskType) {
            case ToDo -> new ToDo(argument);
            case Deadline -> new Deadline(argument, null);
            case Event ->  new Event(argument, null, null);
        };
        chatData.addTask(task);
        return builtResponse.appendMessages("Task added:", "  " + task, "Now you have " + chatData.getTasks().size() + " tasks.");
    }
}
