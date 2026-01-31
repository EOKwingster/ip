package com.eokwingster.data;

import java.time.LocalDateTime;

/**
 * Define the behavior of an object that contains an end time
 */
public interface HasEndTime {
    void setEndTime(LocalDateTime endTime);
    LocalDateTime getEndTime();
}
