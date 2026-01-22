package com.eokwingster.responsors;

import com.eokwingster.data.ChatData;

import java.util.List;

public class ExitChatResponsor implements Responsor {

    /**
     * @return the exit message
     */
    @Override
    public List<String> response(String input, ChatData chatData) {
        return List.of("Bye. Hope to see you again soon!");
    }

    @Override
    public boolean willExit() {
        return true;
    }
}
