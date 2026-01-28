package com.eokwingster.command.keyword;

import java.util.List;

public class StartChatKeyword extends NoArgumentKeyword implements CommandRoot {
    @Override
    public List<String> getAliases() {
        return List.of("new", "hi", "start");
    }
}
