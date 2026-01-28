package com.eokwingster.command.keyword;

import java.util.List;

public class ExitChatKeyword extends NoArgumentKeyword {
    @Override
    public List<String> getAliases() {
        return List.of("exit", "bye", "end");
    }
}
