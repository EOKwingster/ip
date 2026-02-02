package com.eokwingster.command.keyword.keywords;

import java.util.List;

import com.eokwingster.command.keyword.CommandRoot;
import com.eokwingster.command.keyword.abstractkeywods.NotBlankArgKeyword;

/***
 * Keyword for task searching.
 */
public class FindTasksKeyword extends NotBlankArgKeyword implements CommandRoot {
    @Override
    public List<String> getAliases() {
        return List.of("find");
    }
}
