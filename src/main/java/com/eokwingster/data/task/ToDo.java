package com.eokwingster.data.task;

public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    @Override
    protected TaskType type() {
        return TaskType.ToDo;
    }
}
