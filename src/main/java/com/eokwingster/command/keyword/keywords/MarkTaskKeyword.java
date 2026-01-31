package com.eokwingster.command.keyword.keywords;

import java.util.List;

import com.eokwingster.command.keyword.CommandRoot;
import com.eokwingster.command.keyword.abstractkeywods.TaskIndexArgKeyword;

/**
 * keyword for marking task as done
 */
public class MarkTaskKeyword extends TaskIndexArgKeyword implements CommandRoot {
    @Override
    public List<String> getAliases() {
        return List.of("mark");
    }
}
