package com.eokwingster.command.keyword;

import java.util.List;

public class ListTasksKeyword extends NoArgumentKeyword {
    @Override
    public List<String> getAliases() {
        return List.of("list");
    }
}
