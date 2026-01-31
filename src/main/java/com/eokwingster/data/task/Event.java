package com.eokwingster.data.task;

import java.time.LocalDateTime;

import com.eokwingster.data.ChatData;
import com.eokwingster.data.HasBeginTime;
import com.eokwingster.data.HasEndTime;

/**
 * Task with a beginning time and an end time
 */
public class Event extends Task implements HasBeginTime, HasEndTime {
    private LocalDateTime beginTime;
    private LocalDateTime endTime;

    /**
     * Constructor
     * @param description A short string description of this event
     * @param beginTime LocalDateTime object
     * @param endTime LocalDateTime Object
     */
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
        return super.toString() + String.format(
                " (from: %s to: %s)",
                beginTime.format(ChatData.DATE_TIME_DISPLAY_FORMATTER),
                endTime.format(ChatData.DATE_TIME_DISPLAY_FORMATTER)
        );
    }
}
