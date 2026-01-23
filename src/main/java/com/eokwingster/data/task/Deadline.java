package com.eokwingster.data.task;

public class Deadline extends Task{
    protected String endTime;

    public Deadline(String description, String endTime) {
        super(description);
        this.endTime = endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    @Override
    protected TaskType type() {
        return TaskType.DEADLINE;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" (by: %s)", endTime);
    }
}
