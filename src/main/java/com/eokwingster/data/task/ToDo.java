package com.eokwingster.data.task;

/**
 * Most simple task type, only contains the string description
 */
public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    @Override
    public TaskType type() {
        return TaskType.TO_DO;
    }
}
