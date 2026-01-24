package com.eokwingster.data.task;

public class Deadline extends Task implements HasEndTime {
    protected String endTime;

    public Deadline(String description, String endTime) {
        super(description);
        this.endTime = endTime;
    }

    @Override
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    @Override
    public String getEndTime() {
        return endTime;
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
