package com.eokwingster.data.task;

public class Deadline extends Task{
    protected final String endTime;

    public Deadline(String description, String endTime) {
        super(description);
        this.endTime = endTime;
    }

    @Override
    protected TaskType type() {
        return TaskType.Deadline;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" (by: %s)", endTime);
    }
}
