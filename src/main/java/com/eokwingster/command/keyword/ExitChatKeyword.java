package com.eokwingster.command.keyword;

import java.util.List;

public class ExitChatKeyword extends NoArgumentKeyword implements CommandRoot {
    @Override
    public List<String> getAliases() {
        return List.of("exit", "bye", "end");
    }
}
