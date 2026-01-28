package com.eokwingster.command.keyword;

import java.util.List;

public class SetTaskEndTimeKeyword extends SetTaskTimeKeyword {
    @Override
    public List<String> getAliases() {
        return List.of("to", "by");
    }
}
