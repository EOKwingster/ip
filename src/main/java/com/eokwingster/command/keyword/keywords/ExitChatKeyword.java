package com.eokwingster.command.keyword.keywords;

import com.eokwingster.command.keyword.CommandRoot;
import com.eokwingster.command.keyword.abstractkeywods.NoArgumentKeyword;

import java.util.List;

public class ExitChatKeyword extends NoArgumentKeyword implements CommandRoot {
    @Override
    public List<String> getAliases() {
        return List.of("exit", "bye", "end");
    }
}
