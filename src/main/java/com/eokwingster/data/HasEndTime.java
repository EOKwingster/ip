package com.eokwingster.data;

import java.time.LocalDateTime;

public interface HasEndTime {
    void setEndTime(LocalDateTime endTime);
    LocalDateTime getEndTime();
}
