package com.eokwingster.data;

import com.eokwingster.data.task.Task;
import java.util.ArrayList;
import java.util.List;

/**
 * This class store all the data needed and generated in a chat.
 */
public class ChatData {
    private final List<Task> tasks = new ArrayList<>();
    private int focusingTaskIndex = -1;

    public Task getFocusingTask() {
        return tasks.get(focusingTaskIndex);
    }

    public void setFocusingTaskIndex(int focusingTaskIndex) {
        this.focusingTaskIndex = focusingTaskIndex;
    }

    public void addTask(Task task) {
        tasks.add(task);
        setFocusingTaskIndex(tasks.size() - 1);
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void reset() {
        tasks.clear();
        focusingTaskIndex = -1;
    }
}
