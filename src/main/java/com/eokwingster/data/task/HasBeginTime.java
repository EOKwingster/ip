package com.eokwingster.data.task;

import java.time.LocalDateTime;

public interface HasBeginTime {
    void setBeginTime(LocalDateTime beginTime);
    LocalDateTime getBeginTime();
}
