package com.eokwingster.command.keyword.keywords;

import java.util.List;

import com.eokwingster.command.keyword.CommandRoot;
import com.eokwingster.command.keyword.abstractkeywods.TaskIndexArgKeyword;

/**
 * keyword for unmarking a done task to undone
 */
public class UnmarkTaskKeyword extends TaskIndexArgKeyword implements CommandRoot {
    @Override
    public List<String> getAliases() {
        return List.of("unmark");
    }
}
