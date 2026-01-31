package com.eokwingster.data;

import java.time.LocalDateTime;

/**
 * Define the behavior of an object that contains a beginning time
 */
public interface HasBeginTime {
    void setBeginTime(LocalDateTime beginTime);
    LocalDateTime getBeginTime();
}
