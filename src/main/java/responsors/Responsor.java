package responsors;

import java.util.List;

/**
 * define the universal response behavior。
 * Any response logic should be inside a class implements this interface.
 */
public interface Responsor {
    /**
     * The response logic of this Responsor
     * @param input The String message that user inputs
     * @return Response messages as a list of String, each element is one line.
     */
    List<String> response(String input);

    /**
     * Distinguish between Responsors that make chat close and the others.
     * @return if exiting chat after response, default to false.
     */
    default boolean willExit() {
        return false;
    }
}
