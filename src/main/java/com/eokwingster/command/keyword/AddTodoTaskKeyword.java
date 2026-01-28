package com.eokwingster.command.keyword;

import java.util.List;

public class AddTodoTaskKeyword extends AddTaskKeyword implements CommandRoot {
    @Override
    public List<String> getAliases() {
        return List.of("todo");
    }
}
