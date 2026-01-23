package com.eokwingster.data;

import com.eokwingster.data.task.Task;
import java.util.ArrayList;
import java.util.List;

/**
 * This class store all the data needed and generated in a chat.
 */
public class ChatData {
    private final List<Task> tasks = new ArrayList<>();

    public void addTask(Task task) {
        tasks.add(task);
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void reset() {
        tasks.clear();
    }
}
