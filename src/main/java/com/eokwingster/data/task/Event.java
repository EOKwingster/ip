package com.eokwingster.data.task;

public class Event extends Deadline {
    private String beginTime;

    public Event(String description, String beginTime, String endTime) {
        super(description, endTime);
        this.beginTime = beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    @Override
    protected TaskType type() {
        return TaskType.EVENT;
    }

    @Override
    public String toString() {
        String deadlineString = super.toString();
        return deadlineString.replace("by", String.format("from: %s to", beginTime));
    }
}
