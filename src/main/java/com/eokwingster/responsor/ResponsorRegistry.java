package com.eokwingster.responsor;

import com.eokwingster.command.Keyword;
import com.eokwingster.responsor.responsors.UnknownResponsor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The registry handle the connection between user input keywords and responsors by a Map.
 * All responsors should be registered in at least one ResponsorRegistry.
 */
public class ResponsorRegistry {
    private final Map<Keyword, Responsor> registry = new HashMap<>();

    /**
     * Register a Responsor as value with a String as key.
     *
     * @param responsor A Responsor that will be triggered by corresponding keyword.
     * @param keyword   A String that will trigger corresponding Responsor to response.
     */
    public void register(Responsor responsor, Keyword keyword) {
        registry.put(keyword, responsor);
    }

    /**
     * Register a Responsor with multiple keywords.
     *
     * @param responsor A Responsor that will be triggered by corresponding keywords.
     * @param keywords  A list of strings that will trigger corresponding Responsor to response.
     */
    public void register(Responsor responsor, List<Keyword> keywords) {
        for (Keyword keyword : keywords) {
            register(responsor, keyword);
        }
    }

    public void register(Responsor responsor, Keyword... keywords) {
        register(responsor, List.of(keywords));
    }

    /**
     * Get the corresponding Responsor from a keyword.
     * @param keyword A single word.
     * @return The corresponding Responsor, or UnknownResponsor if a Responsor can not be found.
     */
    public Responsor getResponsor(Keyword keyword) {
        return registry.getOrDefault(keyword, new UnknownResponsor(keyword.name().toLowerCase()));
    }
}
