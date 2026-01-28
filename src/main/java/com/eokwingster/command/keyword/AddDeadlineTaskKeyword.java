package com.eokwingster.command.keyword;

import java.util.List;

public class AddDeadlineTaskKeyword extends AddTaskKeyword {
    @Override
    public List<String> getAliases() {
        return List.of("deadline", "ddl");
    }
}
