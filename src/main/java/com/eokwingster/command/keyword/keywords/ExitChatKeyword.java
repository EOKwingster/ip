package com.eokwingster.command.keyword.keywords;

import java.util.List;

import com.eokwingster.command.keyword.CommandRoot;
import com.eokwingster.command.keyword.abstractkeywods.NoArgKeyword;

/**
 * keyword for exiting chat
 */
public class ExitChatKeyword extends NoArgKeyword implements CommandRoot {
    @Override
    public List<String> getAliases() {
        return List.of("exit", "bye", "end");
    }
}
