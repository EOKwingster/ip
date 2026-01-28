package com.eokwingster.data.task;

import com.eokwingster.data.HasEndTime;

import java.time.LocalDateTime;

public class Deadline extends Task implements HasEndTime {
    protected LocalDateTime endTime;

    public Deadline(String description, LocalDateTime endTime) {
        super(description);
        this.endTime = endTime;
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
        return TaskType.DEADLINE;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" (by: %s)", endTime);
    }
}
