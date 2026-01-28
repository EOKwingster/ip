package com.eokwingster.command.keyword;

import java.util.List;

public class ListTasksKeyword extends NoArgumentKeyword implements CommandRoot {
    @Override
    public List<String> getAliases() {
        return List.of("list");
    }
}
