package com.eokwingster.data.task;

import java.time.LocalDateTime;

public interface HasEndTime {
    void setEndTime(LocalDateTime endTime);
    LocalDateTime getEndTime();
}
