package com.eokwingster.command.keyword;

import java.util.List;

public class MarkTaskKeyword extends SelectTaskByIndexKeyword implements CommandRoot {
    @Override
    public List<String> getAliases() {
        return List.of("mark");
    }
}
