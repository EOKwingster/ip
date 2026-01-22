package com.eokwingster.responsors;

import com.eokwingster.Wee;

import java.util.List;

public class StartChatResponsor implements Responsor {
    /**
     * @return the chat start message
     */
    @Override
    public List<String> response(String input) {
        return List.of("Hello! I'm " + Wee.NAME, "What can I do for you?");
    }
}
