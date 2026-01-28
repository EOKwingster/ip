package com.eokwingster.command.keyword.keywords;

import com.eokwingster.command.keyword.CommandRoot;
import com.eokwingster.command.keyword.abstractkeywods.AddTaskKeyword;

import java.util.List;

public class AddTodoTaskKeyword extends AddTaskKeyword implements CommandRoot {
    @Override
    public List<String> getAliases() {
        return List.of("todo");
    }
}
