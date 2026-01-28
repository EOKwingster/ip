package com.eokwingster.command.keyword.abstractkeywods;

import com.eokwingster.data.ChatData;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public abstract class SetTaskTimeKeyword extends NotBlankArgumentKeyword {
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
