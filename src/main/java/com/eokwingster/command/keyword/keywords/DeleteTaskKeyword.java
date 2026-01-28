package com.eokwingster.command.keyword.keywords;

import com.eokwingster.command.keyword.CommandRoot;
import com.eokwingster.command.keyword.abstractkeywods.SelectTaskByIndexKeyword;

import java.util.List;

public class DeleteTaskKeyword extends SelectTaskByIndexKeyword implements CommandRoot {
    @Override
    public List<String> getAliases() {
        return List.of("delete", "remove");
    }
}
