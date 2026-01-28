package com.eokwingster.data.task;

import java.time.LocalDateTime;

public class Event extends Task implements HasBeginTime, HasEndTime {
    private LocalDateTime beginTime;
    private LocalDateTime endTime;

    public Event(String description, LocalDateTime beginTime, LocalDateTime endTime) {
        super(description);
        this.beginTime = beginTime;
        this.endTime = endTime;
    }

    @Override
    public void setBeginTime(LocalDateTime beginTime) {
        this.beginTime = beginTime;
    }

    @Override
    public LocalDateTime getBeginTime() {
        return beginTime;
    }

    @Override
    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    @Override
    public LocalDateTime getEndTime() {
        return endTime;
    }

    @Override
    public TaskType type() {
        return TaskType.EVENT;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" (from: %s to: %s)", beginTime, endTime);
    }
}
