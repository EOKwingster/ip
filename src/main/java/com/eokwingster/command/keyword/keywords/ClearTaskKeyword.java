package com.eokwingster.command.keyword.keywords;

import java.util.List;

import com.eokwingster.command.keyword.CommandRoot;
import com.eokwingster.command.keyword.abstractkeywods.NoArgKeyword;

/**
 * keyword for clearing all tasks
 */
public class ClearTaskKeyword extends NoArgKeyword implements CommandRoot {
    @Override
    public List<String> getAliases() {
        return List.of("clear");
    }
}
