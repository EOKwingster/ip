package com.eokwingster.command.keyword;

import java.util.List;

public class AddEventTaskKeyword extends AddTaskKeyword {
    @Override
    public List<String> getAliases() {
        return List.of("event");
    }
}
