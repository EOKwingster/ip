package com.eokwingster.data.task;

public class Event extends Deadline {
    private final String beginTime;

    public Event(String description, String beginTime, String endTime) {
        super(description, endTime);
        this.beginTime = beginTime;
    }

    @Override
    protected TaskType type() {
        return TaskType.Event;
    }

    @Override
    public String toString() {
        String deadlineString = super.toString();
        return deadlineString.replace("by", String.format("from: %s to", beginTime));
    }
}
