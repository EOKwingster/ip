package com.eokwingster.data.task;

import java.time.LocalDateTime;

import com.eokwingster.data.ChatData;
import com.eokwingster.data.HasEndTime;

/**
 * Task with an end time
 */
public class Deadline extends Task implements HasEndTime {
    protected LocalDateTime endTime;

    /**
     * Constructor
     * @param description a short string description of this deadline
     * @param endTime a LocalDateTime object
     */
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
    public TaskType getType() {
        return TaskType.DEADLINE;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" (by: %s)", endTime.format(ChatData.DATE_TIME_DISPLAY_FORMATTER));
    }
}
