package com.eokwingster.command.keyword;

import java.util.List;

public class AddDeadlineTaskKeyword extends AddTaskKeyword implements CommandRoot {
    @Override
    public List<String> getAliases() {
        return List.of("deadline", "ddl");
    }

    @Override
    public List<Keyword> getRequiredModifiers() {
        return List.of(Keywords.SET_TASK_END);
    }
}
