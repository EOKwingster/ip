package com.eokwingster.command.keyword.keywords;

import java.util.List;

import com.eokwingster.command.keyword.CommandRoot;
import com.eokwingster.command.keyword.abstractkeywods.AddTaskKeyword;

/**
 * keyword for adding a todo
 */
public class AddTodoTaskKeyword extends AddTaskKeyword implements CommandRoot {
    @Override
    public List<String> getAliases() {
        return List.of("todo");
    }
}
