package com.eokwingster.command.keyword;

import java.util.List;

/**
 * Represent root keyword, one command line can only have one root
 * All root keyword class should implement this interface
 */
public interface CommandRoot {
    /**
     * Get the modifier keyword this root
     * @return List of keywords if this keyword is a root keyword, default an empty list
     */
    default List<Keyword> getRequiredModifiers() {
        return List.of();
    }
}
