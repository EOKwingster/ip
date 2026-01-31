package com.eokwingster.command.keyword;

import java.util.List;

import com.eokwingster.data.ChatData;

/**
 * Represent the keywords parse from user input
 */
public interface Keyword {
    /**
     * Validate if a string is a valid argument
     *
     * @param alias    The string alias
     * @param argument The string argument
     * @param chatData The data storage of this chat
     * @throws IllegalArgumentException Throw IllegalArgumentException when the string is not a valid argument
     */
    void validateStep(String alias, String argument, ChatData chatData) throws IllegalArgumentException;

    /**
     * Get the related aliases of this keyword
     * @return List of aliases
     */
    List<String> getAliases();
}
