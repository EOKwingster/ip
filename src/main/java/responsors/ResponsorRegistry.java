package responsors;

import java.util.HashMap;
import java.util.Map;

/**
 * The registry handle the connection between user input keywords and responsors by a Map.
 * All responsors should be registered in at least one ResponsorRegistry.
 */
public class ResponsorRegistry {
    private final Map<String, Responsor> registry = new HashMap<>();

    /**
     * Register a Responsor as value with a String as key.
     * @param keyword A String that will trigger corresponding Responsor to response.
     * @param responsor A Responsor that will be triggered by corresponding keywords.
     */
    public void register(String keyword, Responsor responsor) {
        registry.put(keyword, responsor);
    }

    /**
     * Get the corresponding Responsor from a keyword.
     * @param keyword A String that will trigger corresponding Responsor to response.
     * @return The corresponding Responsor.
     */
    public Responsor getResponsor(String keyword) {
        return registry.get(keyword);
    }
}
