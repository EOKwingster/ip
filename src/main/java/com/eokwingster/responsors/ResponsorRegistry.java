package com.eokwingster.responsors;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The registry handle the connection between user input keywords and com.eokwingster.responsors by a Map.
 * All com.eokwingster.responsors should be registered in at least one ResponsorRegistry.
 */
public class ResponsorRegistry {
    private final Map<String, Responsor> registry = new HashMap<>();

    /**
     * Register a Responsor as value with a String as key.
     * @param keyword A String that will trigger corresponding Responsor to response.
     * @param responsor A Responsor that will be triggered by corresponding keyword.
     */
    public void register(String keyword, Responsor responsor) {
        registry.put(keyword, responsor);
    }

    /**
     * Register a Responsor with multiple keywords.
     * @param keywords A list of strings that will trigger corresponding Responsor to response.
     * @param responsor A Responsor that will be triggered by corresponding keywords.
     */
    public void register(List<String> keywords, Responsor responsor) {
        for (String keyword : keywords) {
            register(keyword, responsor);
        }
    }

    /**
     * Get the corresponding Responsor from a keyword.
     * @param keyword A String that will trigger corresponding Responsor to response.
     * @return The corresponding Responsor, or InputRecordResponsor if a Responsor can not be found.
     */
    public Responsor getResponsor(String keyword) {
        return registry.getOrDefault(keyword, new InputRecordResponsor());
    }
}
