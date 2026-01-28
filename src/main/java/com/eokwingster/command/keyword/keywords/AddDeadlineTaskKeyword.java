package com.eokwingster.command.keyword.keywords;

import com.eokwingster.command.keyword.CommandRoot;
import com.eokwingster.command.keyword.Keyword;
import com.eokwingster.command.keyword.Keywords;
import com.eokwingster.command.keyword.abstractkeywods.AddTaskKeyword;

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
