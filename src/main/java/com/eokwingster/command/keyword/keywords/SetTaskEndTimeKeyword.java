package com.eokwingster.command.keyword.keywords;

import java.util.List;

import com.eokwingster.command.keyword.abstractkeywods.LocalDateTimeArgKeyword;

/**
 * keyword for setting an end time for a task
 */
public class SetTaskEndTimeKeyword extends LocalDateTimeArgKeyword {
    @Override
    public List<String> getAliases() {
        return List.of("to", "by");
    }
}
