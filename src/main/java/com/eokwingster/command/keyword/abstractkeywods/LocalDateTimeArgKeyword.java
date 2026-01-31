package com.eokwingster.command.keyword.abstractkeywods;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import com.eokwingster.data.ChatData;

/**
 * keyword that require
 */
public abstract class LocalDateTimeArgKeyword extends NotBlankArgKeyword {
    @Override
    public void validateStep(String alias, String argument, ChatData chatData) throws IllegalArgumentException {
        try {
            super.validateStep(alias, argument, chatData);
            LocalDateTime.parse(argument, ChatData.DATE_TIME_SAVE_FORMATTER);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Time argument can not be blank!");
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
}
