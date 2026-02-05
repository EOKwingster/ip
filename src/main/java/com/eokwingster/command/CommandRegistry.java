package com.eokwingster.command;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.eokwingster.command.keyword.Keyword;
import com.eokwingster.responsor.Responsor;

/**
 * The registry handle the connection between:
 *     1. Keyword and Responsor
 *     2. String aliases and Keyword
 *
 * @see Keyword
 * @see Responsor
 */
public class CommandRegistry {
    private static final Map<Keyword, Responsor> KEYWORD_TO_RESPONSOR = new HashMap<>();
    private static final Map<String, Keyword> ALIAS_TO_KEYWORD = new HashMap<>();

    private static void registerKeywords(List<Keyword> keywords) {
        for (Keyword keyword : keywords) {
            assert !keyword.getAliases().isEmpty() : "Keywords must have at least one alias";
            for (String alias : keyword.getAliases()) {
                ALIAS_TO_KEYWORD.put(alias, keyword);
            }
        }
    }

    /**
     * Get corresponding Keyword object from an alias
     * @param alias string
     * @return Optional object contains a corresponding Keyword object if found. Otherwise, Optional.empty()
     */
    public static Keyword getKeyword(String alias) {
        return ALIAS_TO_KEYWORD.get(alias);
    }
    /**
     * Register a Responsor with multiple keywords
     * @param responsor A Responsor that will be triggered by corresponding keywords
     * @param keywords  A list of Keyword object
     */
    public static void registerResponsor(Responsor responsor, List<Keyword> keywords) {
        for (Keyword keyword : keywords) {
            registerKeywords(keywords);
            KEYWORD_TO_RESPONSOR.put(keyword, responsor);
        }
    }

    /**
     * Register a Responsor with multiple keywords
     * @param responsor A Responsor that will be triggered by corresponding keywords
     * @param keywords  one or more Keyword object
     */
    public static void registerResponsor(Responsor responsor, Keyword... keywords) {
        registerResponsor(responsor, List.of(keywords));
    }

    /**
     * Get the corresponding Responsor from a Keyword object.
     * @param keyword The Keyword object correlates with a Responsor object.
     * @return The corresponding Responsor.
     */
    public static Responsor getResponsor(Keyword keyword) {
        return KEYWORD_TO_RESPONSOR.get(keyword);
    }
}
