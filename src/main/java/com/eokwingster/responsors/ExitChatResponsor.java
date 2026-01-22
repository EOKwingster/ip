package com.eokwingster.responsors;

import java.util.List;

public class ExitChatResponsor implements Responsor {

    /**
     * @return the exit message
     */
    @Override
    public List<String> response(String input) {
        return List.of("Bye. Hope to see you again soon!");
    }

    @Override
    public boolean willExit() {
        return true;
    }
}
