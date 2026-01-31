package com.eokwingster.command.keyword.keywords;

import java.util.List;

import com.eokwingster.command.keyword.CommandRoot;
import com.eokwingster.command.keyword.abstractkeywods.TaskIndexArgKeyword;

/**
 * keyword for deleting a task
 */
public class DeleteTaskKeyword extends TaskIndexArgKeyword implements CommandRoot {
    @Override
    public List<String> getAliases() {
        return List.of("delete", "remove");
    }
}
