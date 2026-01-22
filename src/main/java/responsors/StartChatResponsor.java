package responsors;

import java.util.List;

public class StartChatResponsor implements Responsor {
    @Override
    public List<String> response(String botName) {
        return List.of("Hello! I'm " + botName, "What can I do for you?");
    }
}
