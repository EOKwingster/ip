package com.eokwingster.data.task;

public class Event extends Task implements HasBeginTime, HasEndTime {
    private String beginTime;
    private String endTime;

    public Event(String description, String beginTime, String endTime) {
        super(description);
        this.beginTime = beginTime;
        this.endTime = endTime;
    }

    @Override
    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    @Override
    public String getBeginTime() {
        return beginTime;
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
        return TaskType.EVENT;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" (from: %s to: %s)", beginTime, endTime);
    }
}
