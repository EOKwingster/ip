package com.eokwingster.command.keyword;

import java.util.List;

public class AddEventTaskKeyword extends AddTaskKeyword implements CommandRoot {
    @Override
    public List<String> getAliases() {
        return List.of("event");
    }

    @Override
    public List<Keyword> getRequiredModifiers() {
        return List.of(Keywords.SET_TASK_BEGIN,  Keywords.SET_TASK_END);
    }
}
