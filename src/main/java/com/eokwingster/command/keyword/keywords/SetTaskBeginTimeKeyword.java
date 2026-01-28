package com.eokwingster.command.keyword.keywords;

import com.eokwingster.command.keyword.abstractkeywods.SetTaskTimeKeyword;

import java.util.List;

public class SetTaskBeginTimeKeyword extends SetTaskTimeKeyword {
    @Override
    public List<String> getAliases() {
        return List.of("from");
    }
}
