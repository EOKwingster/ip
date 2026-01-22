package com.eokwingster.responsors;

import com.eokwingster.Wee;

import java.util.List;

public class StartChatResponsor implements Responsor {
    @Override
    public List<String> response(String botName) {
        return List.of("Hello! I'm " + Wee.NAME, "What can I do for you?");
    }
}
