package com.eokwingster.command.keyword;

import java.util.List;

public class SetTaskEndTimeKeyword extends SetTaskTimeKeyword {
    @Override
    public List<String> getAliases() {
        return List.of("to", "by");
    }

    @Override
    public List<Keyword> getRootKeywords() {
        return List.of(Keywords.ADD_DEADLINE_TASK, Keywords.ADD_EVENT_TASK);
    }
}
