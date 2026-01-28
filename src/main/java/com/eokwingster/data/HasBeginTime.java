package com.eokwingster.data;

import java.time.LocalDateTime;

public interface HasBeginTime {
    void setBeginTime(LocalDateTime beginTime);
    LocalDateTime getBeginTime();
}
