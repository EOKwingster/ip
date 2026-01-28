package com.eokwingster.command.keyword;

import java.util.List;

public class NewChatKeyword extends NoArgumentKeyword {
    @Override
    public List<String> getAliases() {
        return List.of("new", "hi", "start");
    }
}
