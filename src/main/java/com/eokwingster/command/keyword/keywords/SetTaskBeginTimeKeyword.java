package com.eokwingster.command.keyword.keywords;

import java.util.List;

import com.eokwingster.command.keyword.abstractkeywods.LocalDateTimeArgKeyword;

/**
 * keyword for setting a beginning time for a task
 */
public class SetTaskBeginTimeKeyword extends LocalDateTimeArgKeyword {
    @Override
    public List<String> getAliases() {
        return List.of("from");
    }
}
