package com.eokwingster.command.keyword;

import java.util.List;

public class UnmarkTaskKeyword extends SelectTaskByIndexKeyword {
    @Override
    public List<String> getAliases() {
        return List.of("unmark");
    }
}
