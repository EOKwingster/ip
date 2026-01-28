package com.eokwingster.command.keyword;

import java.util.List;

public class SetTaskBeginTimeKeyword extends SetTaskTimeKeyword {
    @Override
    public List<String> getAliases() {
        return List.of("from");
    }

    @Override
    public List<Keyword> getRootKeywords() {
        return List.of(Keywords.ADD_EVENT_TASK);
    }
}
