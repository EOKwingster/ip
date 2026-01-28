package com.eokwingster.data.task;

public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    @Override
    public TaskType type() {
        return TaskType.TO_DO;
    }
}
